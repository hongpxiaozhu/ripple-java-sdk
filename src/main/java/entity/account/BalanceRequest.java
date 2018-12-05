package entity.account;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class BalanceRequest {

    private String account;

    private Boolean strict;

    private Boolean queue;
    @JSONField(name = "signer_lists")
    private Boolean signer_lists;
    @JSONField(name = "ledger_hash")
    private String ledger_hash;
    @JSONField(name = "ledger_index")
    private Long ledger_index;

}
