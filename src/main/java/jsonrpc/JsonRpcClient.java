package jsonrpc;

import java.util.List;


public interface JsonRpcClient {

    String execute(String method);

    <T> String execute(String method, T param);

    <T> String execute(String method, List<T> params);

    <T> String execute(String url, String method, List<T> params);

    <T> String multiExecute(String method, List<T> params, int retry);

}
