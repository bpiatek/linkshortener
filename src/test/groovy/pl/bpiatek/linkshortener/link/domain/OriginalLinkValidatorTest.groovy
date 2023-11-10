package pl.bpiatek.linkshortener.link.domain

import pl.bpiatek.linkshortener.link.dto.OriginalLink
import spock.lang.Specification

class OriginalLinkValidatorTest extends Specification {

    def validator = new OriginalLinkValidator()

    def "should validate links"() {
        when:
        def isValid = validator.isValid(new OriginalLink(url))

        then:
        isValid == expected

        where:
        url                                                           | expected
        "www.wp.pl"                                                   | true
        "www.google.com"                                              | true
        "bpiatek.pl"                                                  | true
        "http://www.example.com"                                      | true
        "https://subdomain.example.org"                               | true
        "http://www.example.com/page"                                 | true
        "https://www.example.com:8080/page"                           | true
        "http://www.example.co.uk/path/page.html"                     | true
        "https://255.255.255.255"                                     | true
        "http://example.com?param=value"                              | true
        "https://example.com/#anchor"                                 | true
        "https://255.255.255.255/path"                                | true
        "http://example.com/?query=param"                             | true
        "https://example.com/#section"                                | true
        "http://subdomain.example.co.uk"                              | true
        "https://example.com/path/page.html"                          | true
        "http://[2001:db8::1]"                                        | true
        "https://example.com:10000"                                   | true
        "http://www .example.com" /* (Space in domain) */             | false
        "htt://example.com" /* (Invalid scheme) */                    | false
        "https://256.256.256.256" /* (Invalid IP address) */          | false
        "https://example.com:abc" /* (Non-numeric port) */            | false
        "http:///example.com" /* (Extra slash in scheme) */           | false
        "http://example..com" /* (Double dot in domain) */            | false
        "http://.example.com" /* (Leading dot in domain) */           | false
        "http://exa mple.com" /* (Space in domain) */                 | false
        "http://example.com/path<>" /*(Invalid characters in path) */ | false
        "http:/www.example.com" /* (Scheme typo)" */                  | false

    }
}
