package pl.bpiatek.linkshortener.link.api;

import org.springframework.stereotype.Component;
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest;
import pl.bpiatek.linkshortener.link.dto.OriginalLink;

@Component
class LinkApiMapper {

    public OriginalLink map(LinkShortenRequest request) {
        return new OriginalLink(request.originalLink());
    }
}
