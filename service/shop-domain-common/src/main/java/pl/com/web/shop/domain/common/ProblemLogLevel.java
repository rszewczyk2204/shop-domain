package pl.com.web.shop.domain.common;

import java.util.function.Consumer;
import org.slf4j.Logger;

public enum ProblemLogLevel {
    TRACE {
        public Consumer<String> logger(Logger logger) {
            return logger::trace;
        }
    },
    DEBUG {
        public Consumer<String> logger(Logger logger) {
            return logger::debug;
        }
    },
    ERROR {
        public Consumer<String> logger(Logger logger) {
            return logger::error;
        }
    };
}
