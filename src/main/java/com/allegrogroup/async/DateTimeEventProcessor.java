package com.allegrogroup.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class DateTimeEventProcessor extends Subscriber<DateTimeEvent> {

    private final Observable<DateTimeEvent> observable;

    @Autowired
    public DateTimeEventProcessor(Observable<DateTimeEvent> observable) {
        this.observable = observable;
    }

    @PostConstruct
    public void subscribe() {
        observable.observeOn(Schedulers.io()).subscribe(this);
    }

    @Override
    public void onCompleted() {
        System.out.println("DateTimeEventProcessor - Completed");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("DateTimeEventProcessor - Error");
        e.printStackTrace();
    }

    @Override
    public void onNext(DateTimeEvent event) {
        System.out.println("DateTimeEventProcessor - Event: " + Thread.currentThread().getName());
        event.complete(LocalDateTime.now().toString());
    }
}
