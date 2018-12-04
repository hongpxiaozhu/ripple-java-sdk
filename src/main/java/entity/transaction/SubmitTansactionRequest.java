package entity.transaction;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class SubmitTansactionRequest {
    @JSONField(name = "tx_blob")
    private String txBlob;
}
