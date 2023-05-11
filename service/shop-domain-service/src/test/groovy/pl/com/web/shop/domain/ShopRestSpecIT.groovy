package pl.com.web.shop.domain

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import pl.com.bit.common.helper.RestSpecIT
import pl.com.bit.common.security.api.AccountIdentity

import static pl.com.web.shop.domain.ShopRestSpecIT.TestHttpHeadersBuilder.testHttpHeadersBuilder

class ShopRestSpecIT extends RestSpecIT {
    protected static String ACCOUNT_HEADER_NAME = "x-account-id"

    def <T> ResponseEntity<T> httpPost(String url, Object command, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpPost(String url, Object command, ParameterizedTypeReference<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpPut(String url, Object command, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpPatch(String url, Object command, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpGet(String url, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Void>(null, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpGet(String url, Object queryObject, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        String uri = uriHelper.buildQuery(url, queryObject).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Void>(null, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpDelete(String url, Object command, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<?>(command, headers), responseType, urlVariables)
    }

    def <T> ResponseEntity<T> httpDelete(String url, Class<T> responseType, HttpHeaders headers = defaultHeaders(), Object... urlVariables) {
        restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<?>(null, headers), responseType, urlVariables)
    }

    private static HttpHeaders defaultHeaders() {
        testHttpHeadersBuilder().build()
    }

    static class TestHttpHeadersBuilder {
        private UUID accountId = ACCOUNT_ID
        private String accountRole = 'CLIENT'

        private TestHttpHeadersBuilder() {
        }

        static TestHttpHeadersBuilder testHttpHeadersBuilder() {
            new TestHttpHeadersBuilder()
        }

        TestHttpHeadersBuilder accountId(UUID accountId) {
            this.accountId = accountId
            this
        }

        HttpHeaders build() {
            HttpHeaders headers = new HttpHeaders()
            headers.setAccept([MediaType.APPLICATION_JSON])

            if (accountId) {
                headers.set(ACCOUNT_HEADER_NAME, assembleAccountContextHeader())
            }

            headers
        }

        private String assembleAccountContextHeader() {
            AccountIdentity.builder()
                    .id(accountId)
                    .authority(new SimpleGrantedAuthority(accountRole))
                    .build()
                    .toHeaderString()
        }
    }
}
