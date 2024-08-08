package bg.softuni.fireplace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "article.api")
public class ArticleApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public ArticleApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
