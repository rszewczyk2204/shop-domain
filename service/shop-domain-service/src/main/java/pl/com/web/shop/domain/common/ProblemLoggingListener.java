package pl.com.web.shop.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@Slf4j
public class ProblemLoggingListener {

    @EventListener
    public void handleProblemEvent(ProblemOccurredEvent event) {
        ProblemLogLevel problemLogLevel = Optional.ofNullable(event.getProblem().getLogLevel()).orElse(ProblemLogLevel.WARN);
        String logMessage = String.format("Problem: {problem: %s}", event.getProblem());
        problemLogLevel.logger(log).accept(logMessage);
    }
}
