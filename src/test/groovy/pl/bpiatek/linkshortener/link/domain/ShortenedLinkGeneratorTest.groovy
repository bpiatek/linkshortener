package pl.bpiatek.linkshortener.link.domain

import spock.lang.Specification

class ShortenedLinkGeneratorTest extends Specification {

    def generator = new ShortenedLinkGenerator()

    def "should generate short URL"() {
        when:
            def url = generator.generateRandomUrl()
        then:
            url.size() == 6
    }
}
