package dev.alexfossa204.starbank.authorization.config.web;

import dev.alexfossa204.starbank.authorization.service.exception.UriSyntaxServiceException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebApplicationBeanConfiguration {

    @Value("${verification.code.check.url}")
    private String verificationCodeCheckUrl;

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true)
                .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature(), true)
                .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
    }

    @Bean
    public URI verificationCodeValidationPostRequestUri() throws UriSyntaxServiceException {
        Optional<URI> uriOptional = Optional.empty();
        try {
            URI uri = new URI(verificationCodeCheckUrl);
            uriOptional = Optional.of(uri);
        } catch (URISyntaxException uriSyntaxException) {
            throw new UriSyntaxServiceException(uriSyntaxException.getMessage());
        }
        return uriOptional.get();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoadingCache<String, Integer> loginAttemptCache() {
        return CacheBuilder.newBuilder()
                .expireAfterAccess(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

}
