package pl.bpiatek.linkshortener.link.api

import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import pl.bpiatek.linkshortener.PostgresqlTestcontainerSpec
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest

import static io.restassured.RestAssured.given
import static io.restassured.http.ContentType.JSON
import static org.hamcrest.Matchers.equalTo
/**
 * Created by Bartosz Piatek on 23/10/2023
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkControllerSpec extends PostgresqlTestcontainerSpec {

    @LocalServerPort
    int port

    def setup() {
        RestAssured.port = port
    }

    def "should shorten link"() {
        given: "original link is passed"
            def requestBody = new LinkShortenRequest(originalLink)

        when: "request is sent"
            def response = given()
                .contentType(JSON)
                .body(requestBody)
                .with()
                .post("/api/v1/link")

        then: "response is expected"
            response.then().log().all()
                    .statusCode(201)
                    .body('originalLink', equalTo(originalLink))
                    .body('shortLink', equalTo(null))

        where: "parameters are"
            statusCode || originalLink          || shortLink
            201        || "https://google.com"  || null
    }
}
