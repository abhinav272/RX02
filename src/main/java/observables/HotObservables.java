package observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Abhinav on 13/08/17.
 */
public class HotObservables {


    /**
     * also try other terminators for interval method like
     * takeUntil(), take() and takeLast()
     */
    public ConnectableObservable<Long> getHotObservableFromCold() {
        return Observable.interval(200, TimeUnit.MILLISECONDS).takeWhile(aLong -> {
            System.out.println(aLong);
            return aLong < 5;
        }).publish();
    }
}
