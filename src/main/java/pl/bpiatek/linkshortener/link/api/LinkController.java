package pl.bpiatek.linkshortener.link.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bpiatek.linkshortener.link.domain.LinkFacade;
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@RestController
@RequestMapping("/api/v1/link")
@RequiredArgsConstructor
class LinkController {

    private final LinkFacade linkFacade;
    private final LinkApiValidator validator;
    private final LinkApiMapper linkApiMapper;

    @PostMapping
    ResponseEntity<?> shortenLink(@RequestBody LinkShortenRequest request, BindingResult result) {
        validator.validate(request, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return new ResponseEntity<>(linkFacade.createLink(linkApiMapper.map(request)), CREATED);
    }
}
