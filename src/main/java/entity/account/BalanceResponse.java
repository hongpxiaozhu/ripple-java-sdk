package entity.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import entity.RippleResponse;
import entity.ledger.Ledger;
import entity.ledger.LedgerRequest;
import lombok.*;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BalanceResponse extends RippleResponse<BalanceRequest> {
    @JSONField(name = "account_data")
    private Account accountData;
    @JSONField(name = "signer_lists")
    private JSONArray signerLists;
    @JSONField(name = "ledger_current_index")
    private Long ledgerCurrentIndex;
    @JSONField(name = "ledger_index")
    private Long ledgerIndex;
    @JSONField(name = "queue_data")
    private JSONObject queueData;
    private Boolean validated;

    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>(10000000);
        for (int w = 0; w < 10000000; w++) {
            String uuid = UUID.randomUUID().toString().replace("-","");
            String code = Rc4util.toSerialCode(w);
//            System.out.println(code);
            stringSet.add(code);
        }
        System.out.println(stringSet.size());
    }
}
