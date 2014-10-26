package com.allegrogroup.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.subjects.PublishSubject;

@RestController
@RequestMapping("/date-time")
public class DateTimeController {

    private final PublishSubject<DateTimeEvent> subject;

    @Autowired
    public DateTimeController(PublishSubject<DateTimeEvent> subject) {
        this.subject = subject;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public DeferredResult<String> dateTime() {
        DeferredResult<String> result = new DeferredResult<>();
        subject.onNext(new DateTimeEvent(result));

        return result;
    }
}
