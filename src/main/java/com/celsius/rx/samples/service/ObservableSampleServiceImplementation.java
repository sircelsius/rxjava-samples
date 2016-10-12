package com.celsius.rx.samples.service;

import lombok.NoArgsConstructor;
import rx.Observable;
import rx.exceptions.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class ObservableSampleServiceImplementation implements ObservableSampleService {

    private static final int SMALL_COUNT = 100;

    public Observable<Integer> runSimpleFilter() {
        return Observable.from(getSmallIntegerList())
                .filter(j -> j % 2 == 0);
    }

    public Observable<Integer> runSimpleCount() {
        return Observable.from(getSmallIntegerList())
                .count();
    }

    public Observable<AtomicInteger> runSimpleCollect() {
        return Observable.from(getSmallIntegerList())
                .collect(AtomicInteger::new, AtomicInteger::addAndGet);
    }

    public Observable<Integer> runCheckedException() {
        return Observable.just(getSmallIntegerList())
                .map(integers -> {
                    try {
                        return doSomethingStupid();
                    } catch (Exception e) {
                        // Checked Exceptions need to be propagated
                        throw Exceptions.propagate(e);
                    }
                });
    }

    private Integer doSomethingStupid() throws Exception {
        throw new Exception("I did something stupid.");
    }

    @Override
    public Observable<Integer> runUncheckedException() {
        return Observable.error(new Exception("I am an Exception"));
    }


    private List<Integer> getSmallIntegerList() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < SMALL_COUNT; i++) {
            list.add(i);
        }
        return list;
    }
}
