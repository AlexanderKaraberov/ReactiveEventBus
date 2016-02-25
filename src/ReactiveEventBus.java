package com.alexander.android.rx.events;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class ReactiveEventBus  {

    private static volatile ReactiveEventBus defaultInstance;
    private final Subject<Object, Object> subject;


    /** Obtain singleton instance of the event bus */
    public static ReactiveEventBus getDefaultInstance()  {
        if (defaultInstance == null) {
            synchronized (ReactiveEventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new ReactiveEventBus(new SerializedSubject<>(BehaviorSubject.create()));
                }
            }
        }
        return defaultInstance;
    }

    /** Post event of type E to the bus */
    public <E> void post(E event) {
        subject.onNext(event);
    }

    /** Subscribe to the event of type E */
    public <E> Observable<E> observeEvent(Class<E> eventClass) {
        return subject.ofType(eventClass);
    }


    private ReactiveEventBus(Subject<Object, Object> subject) {
        this.subject = subject;
    }

}