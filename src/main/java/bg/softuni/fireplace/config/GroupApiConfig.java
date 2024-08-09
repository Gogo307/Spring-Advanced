package bg.softuni.fireplace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "group.api")
public class GroupApiConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public GroupApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
