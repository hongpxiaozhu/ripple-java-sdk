import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ripple.core.coretypes.AccountID;
import com.ripple.core.coretypes.STObject;
import com.ripple.core.types.known.tx.signed.SignedTransaction;
import com.ripple.core.types.known.tx.txns.Payment;
import com.ripple.crypto.Seed;
import com.ripple.crypto.keys.IKeyPair;
import entity.RippleRequest;
import entity.account.BalanceRequest;
import entity.account.BalanceResponse;
import entity.address.Address;
import entity.ledger.LedgerRequest;
import entity.ledger.LegerCurrentResponse;
import entity.ledger.LegerResponse;
import entity.transaction.*;
import jsonrpc.RippleRpcClient;
import jsonrpc.RippleRpcClientImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RippleClient {

    private RippleRpcClient jsonRpcClient;

    public RippleClient(String url){
        this.jsonRpcClient = new RippleRpcClientImpl(url);
    }

    public Address generateAddress()throws Exception{
        SecureRandom random = new SecureRandom();
        byte[] seedBytes = new byte[16];
        random.nextBytes(seedBytes);
        Seed seed = new Seed(seedBytes);
        IKeyPair keyPair = seed.keyPair();
        AccountID accountID = AccountID.fromKeyPair(keyPair);
        String secretOrSeedBase58 = seed.toString();
        String addressOrAccountID = accountID.toString();
        return Address.builder().address(addressOrAccountID).secret(secretOrSeedBase58).build();
    }

    public BalanceResponse getBalance(String address)throws Exception{
        List<BalanceRequest> balanceRequests = new ArrayList<>();
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setAccount(address);
        balanceRequests.add(balanceRequest);
        RippleRequest<BalanceRequest> rippleRequest = new RippleRequest("account_info",balanceRequests);
        String json = this.jsonRpcClient.execute(rippleRequest);
        BalanceResponse balanceResponse = JSON.parseObject(json, BalanceResponse.class);
        return balanceResponse;
    }

    public LegerResponse getBlockInfo(Long ledgerIndex)throws Exception{
        LedgerRequest ledgerRequest = LedgerRequest.builder().ledgerIndex(ledgerIndex).transactions(true).expand(false).build();
        List<LedgerRequest> ledgerRequests = new ArrayList<>();
        ledgerRequests.add(ledgerRequest);
        RippleRequest<LedgerRequest> rippleRequest = new RippleRequest("ledger",ledgerRequests);
        String json = this.jsonRpcClient.execute(rippleRequest);
        LegerResponse legerResponse = JSON.parseObject(json, LegerResponse.class);
        return legerResponse;
    }

    public LegerCurrentResponse getBlockHeight()throws Exception{
        RippleRequest<LedgerRequest> rippleRequest = new RippleRequest("ledger_current",new ArrayList());
        String json = this.jsonRpcClient.execute(rippleRequest);
        LegerCurrentResponse legerCurrentResponse = JSON.parseObject(json, LegerCurrentResponse.class);
        return legerCurrentResponse;
    }

    public Transaction getTransaction(String transactionHash)throws Exception{
        List<TransactionRequest> transactionRequests = new ArrayList<>();
        TransactionRequest transactionRequest = TransactionRequest.builder().transaction(transactionHash).binary(false).build();
        transactionRequests.add(transactionRequest);
        RippleRequest<TransactionRequest> rippleRequest = new RippleRequest("tx",transactionRequests);
        String json = this.jsonRpcClient.execute(rippleRequest);
        Transaction transactionResponse = JSON.parseObject(json, Transaction.class);
        return transactionResponse;
    }

    public SubmitTransactionResponse sendTransaction(TxJson txJson,String secret)throws Exception{
        if(StringUtils.isBlank(txJson.getFee())) {
            RippleRequest<String> feeRippleRequest = new RippleRequest("fee", new ArrayList());
            String feeJson = this.jsonRpcClient.execute(feeRippleRequest);
            JSONObject feeObj = JSON.parseObject(feeJson);
            JSONObject drops = feeObj.getJSONObject("drops");
            String fee = drops.getString("median_fee");
            txJson.setFee(fee);
        }

        if(txJson.getSequence() == null){
            BalanceResponse balanceResponse = getBalance(txJson.getAccount());
            txJson.setSequence(balanceResponse.getAccountData().getSequence());
        }

        Payment txn = (Payment) STObject.fromJSON(JSON.toJSONString(txJson,true));
        SignedTransaction signedAgain = txn.sign(secret);

        List<SubmitTansactionRequest> submitTansactionRequests = new ArrayList<>();
        SubmitTansactionRequest submitTansactionRequest = new SubmitTansactionRequest();
        submitTansactionRequest.setTxBlob(signedAgain.tx_blob);
        submitTansactionRequests.add(submitTansactionRequest);
        RippleRequest<SubmitTansactionRequest> submitRippleRequest = new RippleRequest("submit",submitTansactionRequests);
        String submitJson = this.jsonRpcClient.execute(submitRippleRequest);
        SubmitTransactionResponse submitTransactionResponse = JSON.parseObject(submitJson, SubmitTransactionResponse.class);
        return submitTransactionResponse;
    }
}
