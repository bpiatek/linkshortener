package pl.bpiatek.linkshortener.link.domain;

import lombok.RequiredArgsConstructor;
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest;
import pl.bpiatek.linkshortener.link.dto.LinkShortenResponse;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@RequiredArgsConstructor
public class LinkFacade {

    private final LinkRepository linkRepository;
    private final LinkCreator linkCreator;

    public LinkShortenResponse createLink(LinkShortenRequest request) {
        var link = linkCreator.form(request);
        linkRepository.saveLink(link);

        return new LinkShortenResponse(link.getOriginalLink(), link.getShortLink());
    }
}
