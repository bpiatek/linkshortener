package pl.bpiatek.linkshortener.link.dto;

public record OriginalLink(String url) {

    public OriginalLink(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            this.url = "http://" + url; // Default scheme
        } else {
            this.url = url;
        }
    }
}
