package entity.ledger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Ledger {
    @JSONField(name="account_hash")
    private String accountHash;

    private JSONArray accountState;
    @JSONField(name="close_flags")
    private String closeFlags;
    @JSONField(name="close_time")
    private String closeTime;
    @JSONField(name="close_time_human")
    private String closeTimeHuman;
    @JSONField(name="close_time_resolution")
    private String closeTimeResolution;
    @JSONField(name="closed")
    private String closed;
    @JSONField(name="ledger_hash")
    private String ledgerHash;
    @JSONField(name="ledger_index")
    private String ledgerIndex;
    @JSONField(name="parent_close_time")
    private Long parentCloseTime;
    @JSONField(name="parent_hash")
    private String parentHash;
    @JSONField(name="total_coins")
    private String totalCoins;
    @JSONField(name="transaction_hash")
    private String transactionHash;
    @JSONField(name="transactions")
    private List<String> transactions;
}
