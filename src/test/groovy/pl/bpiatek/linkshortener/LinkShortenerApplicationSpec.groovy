package pl.bpiatek.linkshortener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.testcontainers.spock.Testcontainers
/**
 * Created by Bartosz Piatek on 16/10/2023
 */
@SpringBootTest
@Testcontainers
class LinkShortenerApplicationSpec extends PostgresqlTestcontainerSpec {

    @Autowired
    ApplicationContext context

    def "should start the application"() {
        expect: "context is present"
        context
    }
}
