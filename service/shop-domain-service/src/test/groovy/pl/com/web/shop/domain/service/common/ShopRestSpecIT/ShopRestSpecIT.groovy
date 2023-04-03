package pl.com.web.shop.domain.service.common.ShopRestSpecIT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ShopRestSpecIT extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def <T> ResponseEntity<T> httpPost(String url, Object command, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    private static HttpHeaders defaultHeaders() {
        HttpHeaders header = new HttpHeaders()
        header.setAccept([MediaType.APPLICATION_JSON])
        header
    }
}
