package pl.bpiatek.linkshortener.link.domain;

import lombok.RequiredArgsConstructor;
import pl.bpiatek.linkshortener.link.dto.LinkShortenResponse;
import pl.bpiatek.linkshortener.link.dto.OriginalLink;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@RequiredArgsConstructor
public class LinkFacade {

    private final LinkRepository linkRepository;
    private final LinkCreator linkCreator;

    public LinkShortenResponse createLink(OriginalLink originalLink) {
        var link = linkCreator.form(originalLink);
        linkRepository.saveLink(link);

        return new LinkShortenResponse(link.getOriginalLink(), link.getShortLink());
    }
}
