package entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class RippleResponse<T> {

    private String status;
    private String error;
    @JSONField(name = "error_code")
    private String errorCode;
    @JSONField(name = "error_message")
    private String errorMessage;
    private T request;
}
