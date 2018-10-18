import io.reactivex.Observable;

public class manageMultipleSubscribeToJava {
    public static void main(String args[])
    {
      Observable<Integer> multiple = Observable.create(subscriber -> {
          System.out.println("Create");
          subscriber.onNext(42);
          subscriber.onComplete();
      });

      Observable<Object> multipleCache = Observable.create(subscriber -> {
            System.out.println("Create Cache");
            subscriber.onNext(42);
            subscriber.onComplete();
      }).cache();

        System.out.println("Starting");
        multiple.subscribe(i -> System.out.println("Element A: " + i)); // 기본적으로 구독 초기화에 사용된 스레드 안에서
        multiple.subscribe(i -> System.out.println("Element B: " + i)); // create()에 집어넣은 람다식은 서로 독립적으로 실행됨.
        System.out.println("Exit");                                 // 이미 계산된 결과를 사용하고 싶다면 cache() 연산자를 사용하면됨.


        System.out.println("Starting cache");
        multipleCache.subscribe(i -> System.out.println("Element A: " + i)); // 실행결과 Create()를 한번만 호출함.
        multipleCache.subscribe(i -> System.out.println("Element B: " + i)); // 하지만 cache()와 무한스트림을 같이사용하면 OOME(OutOfMemoryError)가 발생가능
        System.out.println("Exit cache");

    }
}
