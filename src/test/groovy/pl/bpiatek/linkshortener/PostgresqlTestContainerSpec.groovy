package pl.bpiatek.linkshortener

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

/**
 * Created by Bartosz Piatek on 22/10/2023
 */
@Testcontainers
class PostgresqlTestContainerSpec extends Specification{

    static PostgreSQLContainer postgres

    static PostgreSQLContainer<?> getContainer() {
        if (postgres == null) {
            postgres =
                    new PostgreSQLContainer("postgres:15.2-alpine")
                            .withDatabaseName("linkshortener")
                            .withUsername("postgres")
                            .withPassword("postgres")
        }

        return postgres
    }

    def setupSpec() {
        postgres = getContainer()
    }

    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
        postgres.start()
        registry.add("spring.datasource.url", postgres::getJdbcUrl)
        registry.add("spring.datasource.username", postgres::getUsername)
        registry.add("spring.datasource.password", postgres::getPassword)
    }

}
