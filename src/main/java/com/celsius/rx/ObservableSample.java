package com.celsius.rx;

import com.celsius.rx.samples.service.ObservableSampleService;
import com.celsius.rx.samples.service.ObservableSampleServiceImplementation;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ObservableSample {

    public static void main( String[] args ) {
        ObservableSampleService observableSampleService = new ObservableSampleServiceImplementation();

        observableSampleService.runSimpleFilter()
                .subscribe(integer -> log.info("Got Integer " + integer),
                        throwable -> log.error(throwable.getMessage()),
                        () -> log.info("Filter should complete."));

        observableSampleService.runSimpleCount()
                .subscribe(integer -> log.info("Got Integer count " + integer),
                        throwable -> log.error(throwable.getMessage()),
                        () -> log.info("Count should complete."));

        observableSampleService.runSimpleCollect()
                .subscribe(integer -> log.info("Got Integer sum " + integer),
                        throwable -> log.error(throwable.getMessage()),
                        () -> log.info("Sum should complete."));

        observableSampleService.runUncheckedException()
                .subscribe(integer -> log.error("I shouldn't emit anything."),
                        throwable -> log.info("Non Checked exceptions will be caught in onError: " + throwable.getMessage()),
                        () -> log.error("I shouldn't complete."));

        observableSampleService.runCheckedException()
                .subscribe(integer -> log.error("I shouldn't emit anything."),
                        throwable -> log.info("Checked Exceptions need to be propagated to appear in onError: " + throwable.getMessage()),
                        () -> log.error("I shouldn't complete."));
    }
}
