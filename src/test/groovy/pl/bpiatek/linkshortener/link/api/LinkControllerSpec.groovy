package pl.bpiatek.linkshortener.link.api

import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import pl.bpiatek.linkshortener.PostgresqlTestContainerSpec
import pl.bpiatek.linkshortener.link.dto.LinkShortenRequest

import static io.restassured.RestAssured.given
import static io.restassured.http.ContentType.JSON
import static org.hamcrest.Matchers.equalTo
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED

/**
 * Created by Bartosz Piatek on 23/10/2023
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkControllerSpec extends PostgresqlTestContainerSpec {

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
                    .statusCode(statusCode.value())
                    .body('originalLink', equalTo(originalLink))
                    .body('shortLink', equalTo(null))

        where: "parameters are"
            statusCode  || originalLink         || shortLink
            CREATED     || "https://google.com" || null
    }

    def "should return bad request when validation fails"() {
        given: "invalid original link is passed"
            def requestBody = new LinkShortenRequest(originalLink)

        when: "request is sent"
            def response = given()
                .contentType(JSON)
                .body(requestBody)
                .with()
                .post("/api/v1/link")

        then: "error response is expected"
            response.then().log().all()
                .statusCode(statusCode.value())
                .body("defaultMessage", equalTo(defaultMessage))

        where: "parameters are"
            statusCode  || originalLink || defaultMessage
            BAD_REQUEST || ""           || ["Link cannot be empty or null"]
            BAD_REQUEST || null         || ["Link cannot be empty or null"]
    }
}
