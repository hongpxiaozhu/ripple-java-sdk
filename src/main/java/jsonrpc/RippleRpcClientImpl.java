package jsonrpc;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import entity.RippleRequest;
import okhttp3.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RippleRpcClientImpl implements RippleRpcClient {
    private String url;

    private OkHttpClient okHttpClient;

    public RippleRpcClientImpl(String url) {
        this.url = url;
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public <T> String execute(RippleRequest<T> rippleRequest) {
        String requestStr = JSON.toJSONString(rippleRequest);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                requestStr
        );
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("jsonrpc返回失败,失败原因:{"+response+"}");
                return null;
            }
            String resp = response.body().string();
            JSONObject jsonObject = JSON.parseObject(resp);
            return jsonObject.getString("result");
        } catch (IOException e) {
            e.printStackTrace();
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
}
