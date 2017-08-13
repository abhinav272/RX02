package observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Created by Abhinav on 13/08/17.
 */
public class ColdObservables {

    public Observable<Integer> getColdObservableFromCreate(int limit) {
        return Observable.create(subscriber -> {
            for (int i = 0; i <= limit; i++) {
                System.out.println("Source Emit " + i);
                subscriber.onNext(i);
                Thread.sleep(100);
            }
        });
    }

    public ConnectableObservable<Integer> getHotObservableFromCold() {
        return getColdObservableFromCreate(2).publish();
    }
}
