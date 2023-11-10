package pl.bpiatek.linkshortener.link.domain;

import lombok.RequiredArgsConstructor;
import pl.bpiatek.linkshortener.link.dto.OriginalLink;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@RequiredArgsConstructor
class LinkCreator {

    private final OriginalLinkValidator validator;
    private final ShortenedLinkGenerator linkGenerator;

    Link form(OriginalLink originalLink) {
        if (!validator.isValid(originalLink)) {
            throw new IllegalArgumentException("Email is not valid");
        }

        return Link.builder()
                .originalLink(originalLink.url())
                .shortLink(linkGenerator.generateRandomUrl())
                .build();
    }
}
