package pl.bpiatek.linkshortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.jooq.conf.RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED;

/**
 * Created by Bartosz Piatek on 18/10/2023
 */
@Configuration
@RequiredArgsConstructor
class JooqConfig {

    @Bean
    DefaultConfigurationCustomizer customizer() {
        return config ->  config.settings()
                .withRenderQuotedNames(EXPLICIT_DEFAULT_UNQUOTED);
    }
}
