package pl.bpiatek.linkshortener.link.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.spock.Testcontainers
import pl.bpiatek.linkshortener.PostgresqlTestContainerSpec
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest
import pl.bpiatek.linkshortener.link.dto.OriginalLink

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@SpringBootTest
@Testcontainers
class LinkFacadeSpec extends PostgresqlTestContainerSpec {

    @Autowired
    LinkFacade linkFacade

    def "should shorten link"() {
        given: "request data is passed"
            def originalLink = new OriginalLink("https://google.com")

        when: "method is called"
            def link = linkFacade.createLink(originalLink)

        then: "result is expected"
            link.originalLink() == "https://google.com"
            link.shortLink() == null
    }
}
