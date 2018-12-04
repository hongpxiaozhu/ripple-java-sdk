package entity.ledger;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LedgerRequest {
    @JSONField(name = "ledger_hash")
    private String ledgerHash;
    @JSONField(name = "ledger_index")
    private Long ledgerIndex;

    private Boolean accounts;

    private Boolean full;

    private Boolean transactions;

    private Boolean expand;
    @JSONField(name = "owner_funds")
    private String ownerFunds;

    private Boolean binary;

    private Boolean queue;
}
