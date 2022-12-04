package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Support {
        private String url;
        private String text;
    }

