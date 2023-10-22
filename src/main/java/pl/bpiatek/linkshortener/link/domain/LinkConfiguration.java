package pl.bpiatek.linkshortener.link.domain;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@Configuration
class LinkConfiguration {

    @Bean
    LinkFacade linkFacade(LinkRepository linkRepository) {
        var linkCreator = new LinkCreator();
        return new LinkFacade(linkRepository, linkCreator);
    }

    @Bean
    LinkRepository linkRepository(DSLContext dslContext, Clock clock) {
        return new LinkRepository(dslContext, clock);
    }
}
