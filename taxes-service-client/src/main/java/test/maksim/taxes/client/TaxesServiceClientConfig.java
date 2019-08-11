package test.maksim.taxes.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxesServiceClientConfig {

    private final TaxesServiceClientFactory factory = new TaxesServiceClientFactory();

    @Bean
    public TaxesServiceClient contentClient(@Value("${service.base.url:http://localhost:8080}") String serviceUrl) {
        return factory.defaultClient(serviceUrl);
    }
}
