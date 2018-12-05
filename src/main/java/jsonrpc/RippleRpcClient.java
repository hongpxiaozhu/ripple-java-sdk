package jsonrpc;

import entity.RippleRequest;

import java.util.List;


public interface RippleRpcClient {
    <T> String execute(RippleRequest<T> rippleRequest) throws Exception;
}
