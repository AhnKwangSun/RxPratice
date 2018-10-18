import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class create2Java {
    public static void main(String[] args)
    {
        Observable.just("Hello world").subscribe(System.out::println);
        Observable<Integer> ints = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> subscriber) throws Exception {
                System.out.println("Create");
                subscriber.onNext(5);
                subscriber.onNext(6);
                subscriber.onNext(7);
                subscriber.onComplete();
                System.out.println("Completed");
            }
        });

        /* Example Create, Never, Empty, Range */

        Observable<String> exNever = Observable.never();

        Observable<String> exEmpty = Observable.empty();

        Observable<Integer> exRange = Observable.range(1, 5);


        System.out.println("Starting");
        ints.subscribe(integer -> System.out.println("Element: " + integer));
        System.out.println("Exit");

        exNever.subscribe(
                v -> System.out.println("This should never be printed!"),
                error -> System.out.println("Or this!"),
                () -> System.out.println("This neither!"));

        exEmpty.subscribe(
                v -> System.out.println("This should never be printed!"),
                error -> System.out.println("Or this!"),
                () -> System.out.println("Done will be printed."));

        exRange.subscribe(
                i ->System.out.println(i),
                e -> e.printStackTrace(),
                () -> System.out.println());
    }
}
