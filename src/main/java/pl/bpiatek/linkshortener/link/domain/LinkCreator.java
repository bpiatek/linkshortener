package pl.bpiatek.linkshortener.link.domain;

import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
class LinkCreator {

    Link form(LinkShortenRequest request) {
        return Link.builder()
                .originalLink(request.originalLink())
                .build();
    }
}
