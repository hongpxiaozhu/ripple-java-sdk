package jsonrpc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "omni.rpc")
public class JsonRpcConfig {

    private Integer timeoutSeconds;
    private String user;
    private String password;
    private String serverUrl;

}
