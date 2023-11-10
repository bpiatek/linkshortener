package pl.bpiatek.linkshortener.link.domain;

import java.util.Random;

class ShortenedLinkGenerator {

    private static final Random random = new Random();
    private static final String CHARACTER_SET = "ABCDEFGHJKLMNPQRSTUVWXYZ123456789abcdefghijkmnopqrstuvxyz";
    private static final int LINK_LENGTH = 6;

    String generateRandomUrl() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < LINK_LENGTH; i++) {
            var randomIndex = random.nextInt(CHARACTER_SET.length());
            builder.append(CHARACTER_SET.charAt(randomIndex));
        }

        return builder.toString();
    }
}
