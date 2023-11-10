package pl.bpiatek.linkshortener.link.domain;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static pl.bpiatek.linkshortener.jooq.Tables.LINKS;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@RequiredArgsConstructor
class LinkRepository {

    private final DSLContext dslContext;
    private final Clock clock;

    void saveLink(Link link) {
        dslContext.insertInto(LINKS)
                .set(LINKS.ORIGINAL_LINK, link.getOriginalLink())
                .set(LINKS.SHORT_LINK, "unknown")
                .set(LINKS.CREATED_AT, LocalDateTime.now(clock))
                .set(LINKS.EXPIRATION_DATE, calculateExpirationDate(link))
                .execute();
    }

    private LocalDate calculateExpirationDate(Link link) {
        if(link.getExpirationDate() == null) {
            return LocalDate.now(clock).plusDays(7);
        }
        return link.getExpirationDate();
    }
}
