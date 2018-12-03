package jsonrpc;

import com.alibaba.fastjson.JSON;
import io.mycred.btcworker.utils.JsonUtils;
import io.mycred.btcworker.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JsonRpcClientImpl implements JsonRpcClient {

    private OkHttpClient okHttpClient;

    private JsonRpcConfig jsonRpcConfig;

    public JsonRpcClientImpl(JsonRpcConfig jsonRpcConfig) {
        this.jsonRpcConfig = jsonRpcConfig;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(jsonRpcConfig.getTimeoutSeconds(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public String execute(String method) {
        return execute(method, null);
    }

    @Override
    public <T> String execute(String method, T param) {
        List<T> params = new ArrayList<>();
        params.add(param);
        return execute(method, params);
    }

    @Override
    public <T> String execute(String method, List<T> params) {
        return execute(jsonRpcConfig.getServerUrl(), method, params);
    }

    @Override
    public <T> String multiExecute(String method, List<T> params, int retry) {
        for (int i = 0; i < retry + 1; i++) {
            try {
                if (i != 0) {
                    int waitSecond = 5 * i;
                    log.warn("(method:{},params:{})rpc请求失败，等待{}s后,开始第{}次重试:", method, params, waitSecond, i);
                    ThreadUtils.sleep(waitSecond, TimeUnit.SECONDS);
                }
                return execute(method, params);
            } catch (JsonRpcException e) {
                if (i == retry) {
                    log.error("(method:{},params:{})重试{}次后,依旧失败", method, params, retry);
                    throw e;
                }
            }
        }
        return null;
    }

    @Override
    public <T> String execute(String url, String method, List<T> params) {
        JsonRequest<T> jsonRequest = new JsonRequest<>(method, params);
        String requestId = getNewUuid();
        String requestStr = JSON.toJSONString(jsonRequest);
        if (!method.contains("signrawtransaction")) { //不包含签名则打印日志
            log.debug("[JsonRpcClient request] method:{},requestId:{},request_json:{}", method, requestId, requestStr);
        }

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                requestStr
        );
        String authorizationStr = generateAuthorization();
        Headers headers = Headers.of("Authorization", authorizationStr);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("requestId:{},jsonrpc返回失败,失败原因:{}", requestId, response);
                return null;
            }
            String resp = response.body().string();
            log.debug("[JsonRpcClient response] method:{},requestId:{},response_json:{}", method, requestId, resp);
            JsonResponse jsonResponse = JsonUtils.toBean(resp, JsonResponse.class);
            return jsonResponse.getResult();
        } catch (IOException e) {
            log.error("requestMethod:{},requestParams:{},jsonrpc请求失败,失败原因", method, params, e);
            throw new JsonRpcException(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private String getNewUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    private String generateAuthorization() {
        String authString = jsonRpcConfig.getUser() + ":" + jsonRpcConfig.getPassword();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return "Basic " + authStringEnc;
    }
}
