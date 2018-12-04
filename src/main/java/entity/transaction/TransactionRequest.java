package entity.transaction;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class TransactionRequest {
    private String transaction;

    private Boolean binary;
}
