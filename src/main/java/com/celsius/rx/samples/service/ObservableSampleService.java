package com.celsius.rx.samples.service;

import rx.Observable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by marc on 12.10.16.
 */
public interface ObservableSampleService {
    Observable<Integer> runSimpleFilter();
    Observable<Integer> runSimpleCount();
    Observable<AtomicInteger> runSimpleCollect();
    Observable<Integer> runCheckedException();
    Observable<Integer> runUncheckedException();
}
