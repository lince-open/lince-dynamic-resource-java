package work.lince.dynamic

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get Success #user"() {

        when:
            def result = client.get(path: "health", headers: ["lince.user.name": user])

        then:
            result != null
            result.data.status == "ok"
            result.data.now != null
            result.data.user == expectedUser

        where:
            user       | expectedUser
            null       | "anonymous"
            "zzz"      | "zzz"
            "asdf1234" | "asdf1234"

    }

}