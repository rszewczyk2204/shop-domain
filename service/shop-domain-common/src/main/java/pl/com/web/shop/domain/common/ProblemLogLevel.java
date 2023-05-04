package pl.com.web.shop.domain.common;

import org.slf4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

public enum ProblemLogLevel {
    TRACE {
        @Override
        public Consumer<String> logger(Logger logger) {
            return logger::trace;
        }
    },
    DEBUG {
        @Override
        public Consumer<String> logger(Logger logger) {
            return logger::debug;
        }
    },
    ERROR {
        @Override
        public Consumer<String> logger(Logger logger) {
            return logger::error;
        }
    },
    WARN {
        @Override
        public Consumer<String> logger(Logger logger) {
            return logger::warn;
        }
    };

    public abstract Consumer<String> logger(@NotNull Logger logger);
}
