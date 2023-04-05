package pl.com.web.shop.domain.common;


import org.springframework.context.ApplicationEvent;

public class ProblemOccurredEvent extends ApplicationEvent {

    public ProblemOccurredEvent(Problem source) {
        super(source);
    }

    public Problem getProblem() {
        return (Problem) getSource();
    }
}
