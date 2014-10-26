package com.allegrogroup.async;

import org.springframework.web.context.request.async.DeferredResult;

public class DateTimeEvent {
    private final DeferredResult<String> deferredResult;

    public DateTimeEvent(DeferredResult<String> deferredResult) {
        this.deferredResult = deferredResult;
    }

    public void complete(String result) {
        if (deferredResult.isSetOrExpired()) {
        } else {
            deferredResult.setResult(result);
        }
    }
}
