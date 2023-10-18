package pl.bpiatek.linkshortener.config;

import lombok.RequiredArgsConstructor;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import static org.jooq.conf.RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED;

/**
 * Created by Bartosz Piatek on 18/10/2023
 */
@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
class JooqConfig {

    private final DataSourceProperties properties;

    @EventListener(ApplicationReadyEvent.class)
    public void configuration() throws Exception {
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver(properties.determineDriverClassName())
                        .withUrl(properties.determineUrl())
                        .withUser(properties.determineUsername())
                        .withPassword(properties.determinePassword())
                )
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withDirectory("target/generated-sources/jooq")
                                .withPackageName("pl.bpiatek.linkshortener.jooq")
                        ));

        GenerationTool.generate(configuration);
    }

    @Bean
    DefaultConfigurationCustomizer customizer() {
        return config ->  config.settings()
                .withRenderQuotedNames(EXPLICIT_DEFAULT_UNQUOTED);
    }
}
