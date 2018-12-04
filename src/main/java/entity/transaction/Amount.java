package entity.transaction;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Amount {
    private String currency;
    private String value;
    private String issuer;
}
