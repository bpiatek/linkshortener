package pl.bpiatek.linkshortener.link.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@Getter
@Builder
class Link {
    private String originalLink;
    private String shortLink;
    private LocalDateTime createdAt;
    private LocalDate expirationDate;
    private boolean isCustom;
}
