package pl.com.web.shop.domain.common;

import java.util.function.Consumer;
import org.slf4j.Logger;

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

    public abstract Consumer<String> logger(Logger logger);
}
