package pl.com.web.shop.domain.config

import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.AbstractServerList
import com.netflix.loadbalancer.Server
import com.netflix.loadbalancer.ServerList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServerPortSetup {
    public static int localPort

    @Bean
    ServerList<Server> ribbonServerList() {
        new CustomServerList()
    }

    static class CustomServerList extends AbstractServerList<Server> {
        protected IClientConfig clientConfig

        @Override
        List<Server> getInitialListOfServers() {
            return getUpdatedListOfServers()
        }

        @Override
        List<Server> getUpdatedListOfServers() {
            [new Server("localhost:" + localPort)]
        }

        @Override
        void initWithNiwsConfig(IClientConfig clientConfig) {
            this.clientConfig = clientConfig
        }
    }
}
