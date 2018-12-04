package entity.transaction;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class TxJson {
    @JSONField(name="TransactionType")
    private String transactionType;
    @JSONField(name="Account")
    private String account;
    @JSONField(name="Destination")
    private String destination;
    @JSONField(name="Amount")
    private String amount;
    @JSONField(name="Fee")
    private String fee;
    @JSONField(name="Sequence")
    private Long sequence;
}
