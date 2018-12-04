package entity.transaction;

import com.alibaba.fastjson.annotation.JSONField;
import entity.RippleResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class SubmitTransactionResponse extends RippleResponse<SubmitTansactionRequest> {

    @JSONField(name="engine_result")
    private String engineResult;
    @JSONField(name="engine_result_code")
    private String engineResultCode;
    @JSONField(name="engine_result_message")
    private String engineResultMessage;
    @JSONField(name="tx_blob")
    private String txBlob;
    @JSONField(name="tx_json")
    private Transaction txJson;
}
