package entity.ledger;

import com.alibaba.fastjson.annotation.JSONField;
import entity.RippleResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LegerCurrentResponse extends RippleResponse<String> {

    @JSONField(name="ledger_current_index")
    private Long ledgerCurrentIndex;
}
