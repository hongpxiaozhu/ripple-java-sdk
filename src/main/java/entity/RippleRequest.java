package entity;


import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class RippleRequest<T>{

    private String method;
    private List<T> params;
}
