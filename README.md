# ReactiveEventBus

Working and straightforward implementation of the Event Bus pattern. Event Bus allows publish-subscribe-style communication between components without requiring the components to explicitly register with one another and thus be aware of each other. 
I used [RxJava](https://github.com/ReactiveX/RxJava) for it.


#How to use
E.g. in Android with [RxAndroid](https://github.com/ReactiveX/RxAndroid):
* First create an Event object, which will be a representation of the fact that something has happened:

```java
public class ItemSelectedEvent {

    private final int seletedItemPosition;
    
    public ItemSelectedEvent(int seletedItemPosition) {
        this.seletedItemPosition = seletedItemPosition;
    }
    public int getSeletedItemPosition() {
        return seletedItemPosition;
    }
}
```
* Post event somewhere in your application like this:
```java
 ReactiveEventBus.getDefaultInstance().post(new ItemSelectedEvent(getAdapterPosition()));
 ```
* Subscribe and react to this event somewhere else:
```java
   final Subscription subscription = ReactiveEventBus.getDefaultInstance()
                .observeEvent(ItemSelectedEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ItemSelectedEvent>() {
                    @Override
                    public void call(ItemSelectedEvent event) {
                        Log.d("TAG", "Receive event: " + event.getSeletedItemPosition());
                    }
                });
```
   
              
<p>**Note:** Don't forget to unsubcribe in `onPause()/onStop()`. 
<p>**Tip:** For handy managing a bunch of events use [CompositeSubscription](http://reactivex.io/RxJava/javadoc/rx/subscriptions/CompositeSubscription.html)
