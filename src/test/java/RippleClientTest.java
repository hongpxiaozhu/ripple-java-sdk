import com.alibaba.fastjson.JSON;
import entity.address.Address;
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
    public void testGenerateAddress(){
        Address address = proRippleClient.generateAddress();
        System.out.println(JSON.toJSONString(address));
    }

    @Test
    public void testgetBlockInfo(){
        proRippleClient.getBlockInfo(43446438L);
    }

    @Test
    public void testGetBlockHeight(){
        proRippleClient.getBlockHeight();
    }

    @Test
    public void testGetTransaction(){
        proRippleClient.getTransaction("43A34C86E4270FA959AB7E842FE2DE9387F8EC787B9A46927EE8BF0C8EC71BA9");
    }

    @Test
    public void testSendTransaction(){
        TxJson txJson = new TxJson();
        txJson.setAccount("rJ6r7jTD1XtTvQ8bsubvaZNsinoc1S2czw");
        txJson.setAmount("5000000");
        txJson.setDestination("rhX8EVZKaCH4ea256wMpyas8yxYVEnfGbX");
        txJson.setFee("10000");
        txJson.setSequence(7L);
        txJson.setTransactionType("Payment");
        System.out.println(JSON.toJSONString(testRippleClient.sendTransaction(txJson,"ssxHxG6V4yfS2ink1dh8LCQbiboxe")));

    }
}
