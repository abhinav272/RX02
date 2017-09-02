import observables.ColdObservables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import observables.HotObservables;

/**
 * Created by Abhinav on 13/08/17.
 */
public class DemoObservables {

    public static void main(String[] args) {

        ColdObservables coldObservables = new ColdObservables();
        HotObservables hotObservables = new HotObservables();

        System.out.println("\n\n>>>Cold Observable Example 1:\n\n");
        /**
         * In case of cold observable, all subscriber will get all the emissions guaranteed
         * */

        Observable<Integer> coldObservableFromCreate1 = coldObservables.getColdObservableFromCreate(2);

        coldObservableFromCreate1.subscribe(integer -> System.out.println("DemoObservables.accept 1 " + integer));
        coldObservableFromCreate1.subscribe(integer -> System.out.println("DemoObservables.accept 2 " + integer));

        System.out.println("\n\n>>>Cold Observable Example 2:\n\n");

        /**
         * Cold Observable example 2, created from Just operator
         * */

        Observable<Integer> coldObservableFromJust = coldObservables.getColdObservableFromJust();
        coldObservableFromJust.subscribe(integer -> System.out.println("coldObservable1 " + integer));
        coldObservableFromJust.subscribe(integer -> System.out.println("coldObservable2 " + integer));


        System.out.println("\n\n>>>Hot Observable Example 1:\n\n");

        /**
         * In case of hot observable only the observable subscribed before connect will get all emissions,
         * Even if there are no subscribers emissions will continue
         * */

        ConnectableObservable<Integer> hotObservableFromCold1 = coldObservables.getHotObservableFromCold();
        hotObservableFromCold1.subscribe(integer -> System.out.println("DemoObservables.accept 1 " + integer));
        hotObservableFromCold1.connect();
        hotObservableFromCold1.subscribe(integer -> System.out.println("DemoObservables.accept 2 " + integer));


        System.out.println("\n\n>>>Hot Observable Example 2:\n\n");

        /**
         * More precise example of Hot Observable, emissions starts and as Subscribers subscribes they
         * start receiving emissions from there only
         * */

        ConnectableObservable<Long> hotObservableFromCold2 = hotObservables.getHotObservableFromCold();
        hotObservableFromCold2.connect();

        hotObservableFromCold2.subscribe(aLong -> System.out.println("first : " + aLong));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hotObservableFromCold2.subscribe(aLong -> System.out.println("second : " + aLong));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
