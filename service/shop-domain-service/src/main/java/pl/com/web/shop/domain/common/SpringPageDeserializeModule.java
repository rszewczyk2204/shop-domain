package pl.com.web.shop.domain.common;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
public class SpringPageDeserializeModule extends SimpleModule {

    public void setupModule(Module.SetupContext context) {
        context.setMixInAnnotations(Page.class, PageMixIn.class);
    }

    @JsonDeserialize(as = PageWrapper.class)
    private interface PageMixIn {}
}
