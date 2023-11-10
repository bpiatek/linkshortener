package pl.bpiatek.linkshortener.link.domain;

import org.apache.commons.validator.routines.UrlValidator;
import pl.bpiatek.linkshortener.link.dto.OriginalLink;

class OriginalLinkValidator {

    boolean isValid(OriginalLink originalLink) {
        String[] schemes = {"http", "https", "ftp"};
        long options = UrlValidator.ALLOW_ALL_SCHEMES;
        UrlValidator urlValidator = new UrlValidator(schemes, options);
        return urlValidator.isValid(originalLink.url());
    }
}
