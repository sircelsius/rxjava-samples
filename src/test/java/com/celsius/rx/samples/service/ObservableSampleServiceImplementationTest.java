package com.celsius.rx.samples.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ObservableSampleServiceImplementationTest {
    private ObservableSampleService observableSampleService = new ObservableSampleServiceImplementation();

    @Test
    public void testSimpleFilter() throws Exception {
        Observable<Integer> obs = observableSampleService.runSimpleFilter();

        TestSubscriber<Integer> subscriber = new TestSubscriber<>();

        obs.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(50);

        subscriber.getOnNextEvents()
                .forEach(integer -> assertEquals(integer % 2, 0));
    }

    @Test
    public void testSimpleCount() throws Exception {
        Observable<Integer> obs = observableSampleService.runSimpleCount();

        TestSubscriber<Integer> subscriber = new TestSubscriber<>();

        obs.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);

        subscriber.getOnNextEvents()
                .forEach(integer -> assertEquals(integer, Integer.valueOf(100)));
    }

    @Test
    public void testSimpleCollect() throws Exception {
        Observable<AtomicInteger> obs = observableSampleService.runSimpleCollect();

        TestSubscriber<AtomicInteger> subscriber = new TestSubscriber<>();

        obs.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);

        subscriber.getOnNextEvents()
                .forEach(atomicInteger -> assertEquals(atomicInteger.get(), 4950));
    }
}