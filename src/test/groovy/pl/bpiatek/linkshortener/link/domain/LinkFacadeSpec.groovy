package pl.bpiatek.linkshortener.link.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.spock.Testcontainers
import pl.bpiatek.linkshortener.PostgresqlTestcointainerSpec
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest
/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@SpringBootTest
@Testcontainers
class LinkFacadeSpec extends PostgresqlTestcointainerSpec {

    @Autowired
    LinkFacade linkFacade

    def "should create a link"() {
        given:
        var request = new LinkShortenRequest("https://google.com")
        when:
        var link = linkFacade.createLink(request)
        then:
        link.originalLink() == "https://google.com"
        link.shortLink() == null

    }
}
