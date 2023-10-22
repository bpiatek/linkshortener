package pl.bpiatek.linkshortener.link.api;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@Component
class LinkApiValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LinkShortenRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var request = (LinkShortenRequest) target;

        if (request.originalLink() == null || request.originalLink().isEmpty()) {
            errors.rejectValue("originalLink", "empty.link", "Link cannot be empty or null");
        }
    }
}
