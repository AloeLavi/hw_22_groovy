package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponse {
    @JsonProperty("data")
    private User user;
    @JsonProperty("support")
    private Support support;
}
