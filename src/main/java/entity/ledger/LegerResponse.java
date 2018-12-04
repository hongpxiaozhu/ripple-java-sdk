package entity.ledger;

import com.alibaba.fastjson.annotation.JSONField;
import entity.RippleRequest;
import entity.RippleResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LegerResponse extends RippleResponse<LedgerRequest> {
    private Ledger ledger;
    @JSONField(name="ledger_hash")
    private String ledgerHash;
    @JSONField(name="ledger_index")
    private Long ledgerIndex;
    @JSONField(name="queue_data")
    private String queueData;
}
