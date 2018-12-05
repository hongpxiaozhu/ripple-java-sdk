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
    public void testGenerateAddress()throws Exception{
        Address address = proRippleClient.generateAddress();
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
        testRippleClient.getTransaction("4F947E0F649662388EAF133E8FE45BF7FBC7C790AD679F529F5E5C00B2CE8E3F");
    }

    @Test
    public void testGetBalance()throws Exception{
        testRippleClient.getBalance("rJ6r7jTD1XtTvQ8bsubvaZNsinoc1S2czw");
    }

    @Test
    public void testSendTransaction()throws Exception{
        TxJson txJson = new TxJson();
        txJson.setAccount("rJ6r7jTD1XtTvQ8bsubvaZNsinoc1S2czw");
        txJson.setAmount("6000000");
        txJson.setDestination("rhX8EVZKaCH4ea256wMpyas8yxYVEnfGbX");
        //txJson.setFee("10000");
//        txJson.setSequence(8L);
        txJson.setTransactionType("Payment");
        System.out.println(JSON.toJSONString(testRippleClient.sendTransaction(txJson,"ssxHxG6V4yfS2ink1dh8LCQbiboxe")));

    }
}
