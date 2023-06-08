package pl.com.web.shop.domain

import org.spockframework.spring.EnableSharedInjection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.spock.Testcontainers
import pl.com.bit.common.helper.utils.UriHelper
import pl.com.bit.user.domain.api.model.UserLite
import pl.com.bit.user.starter.UserInternalService
import pl.com.web.shop.domain.config.ServerPortSetup

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@Testcontainers
@Import([UriHelper])
@ActiveProfiles("test")
@EnableSharedInjection
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BaseITSpecification extends ShopRestSpecIT {

    @LocalServerPort
    int port

    @Autowired
    UserInternalService userService

    def setup() {
        ServerPortSetup.localPort = port

        ACCOUNT_ID = UUID.randomUUID()

        userService.getLite(_ as UUID) >> {
            UUID id -> UserLite.builder()
                .accountId(id)
                .name("User ${id}")
                .login("user-${id}")
                .build()
        }
    }
}
