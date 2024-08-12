package pl.dicedev.simplyauth.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CorsFilterProperties.class)
@RequiredArgsConstructor
public class CorsFilterConfiguration {

    private final CorsFilterProperties properties;

    @Bean("corsFilter")
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        final CorsConfiguration config = buildCorsConfiguration();
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new FilterRegistrationBean<>(new CorsFilter(source));
    }

    private CorsConfiguration buildCorsConfiguration() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        return configuration;
    }
}