import com.alibaba.fastjson.JSON;
import entity.account.BalanceResponse;
import entity.address.Address;
import entity.transaction.Transaction;
import entity.transaction.TxJson;
import org.junit.BeforeClass;
import org.junit.Test;

public class RippleClientTest {

    public static RippleClient proRippleClient;

    public static RippleClient testRippleClient;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testRippleClient = new RippleClient("https://s.altnet.rippletest.net:51234");
        proRippleClient = new RippleClient("https://s2.ripple.com:51234");
    }

    @Test
    public void testGenerateAddress()throws Exception{
        Address address = testRippleClient.generateAddress();
        System.out.println(JSON.toJSONString(address));
    }

    @Test
    public void testgetBlockInfo()throws Exception{
        proRippleClient.getBlockInfo(43446438L);
    }

    @Test
    public void testGetBlockHeight()throws Exception{
        proRippleClient.getBlockHeight();
    }

    @Test
    public void testGetTransaction()throws Exception{
        Transaction transaction = testRippleClient.getTransaction("3633FD86E7350DEEBFD1A63DA67A537944ABB91D0C6032E77ECE2F7EA916CA05");
        System.out.println(transaction.getValidated());
        System.out.println(transaction.getStatus());
    }

    @Test
    public void testGetBalance()throws Exception{
        BalanceResponse balanceResponse = testRippleClient.getBalance("rhX8EVZKaCH4ea256wMpyas8yxYVEnfGbX");
        System.out.println(balanceResponse.getAccountData().getBalance());
    }

    @Test
    public void testBatchSendTransaction()throws Exception{
        for (int i = 0; i <10 ; i++) {
            TxJson txJson = new TxJson();
            txJson.setAccount("rJ6r7jTD1XtTvQ8bsubvaZNsinoc1S2czw");
            txJson.setAmount(String.valueOf(1000000+i));
            txJson.setDestination("rhX8EVZKaCH4ea256wMpyas8yxYVEnfGbX");
            //txJson.setFee("10000");
//        txJson.setSequence(8L);
            txJson.setTransactionType("Payment");
            System.out.println(JSON.toJSONString(testRippleClient.sendTransaction(txJson,"ssxHxG6V4yfS2ink1dh8LCQbiboxe")));
        }
    }

    @Test
    public void testSendTransaction()throws Exception{
            TxJson txJson = new TxJson();
            txJson.setAccount("rhX8EVZKaCH4ea256wMpyas8yxYVEnfGbX");
            txJson.setAmount("9985000");
            txJson.setDestination("rJ6r7jTD1XtTvQ8bsubvaZNsinoc1S2czw");
            //txJson.setFee("10000");
//        txJson.setSequence(8L);
            txJson.setTransactionType("Payment");
            System.out.println(JSON.toJSONString(testRippleClient.sendTransaction(txJson,"shY7mdw4FiQbUBxGzPLWefmJ71yz9")));
    }
}
