import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

object create {
    @JvmStatic
    fun main(args: Array<String>) {
        Observable.just("Hello world").subscribe { println(it) }
        val exCreate = Observable.create(ObservableOnSubscribe<Int> { subscriber ->
            println("Create")
            subscriber.onNext(5)
            subscriber.onNext(6)
            subscriber.onNext(7)
            subscriber.onComplete() // onComplete를 실행하면 밑에 선언한 exCreate의 onComplete 부분의 문구가 실행됨.
            println("Completed")
        })

        val exNever = Observable.never<Int>()

        val exEmpty = Observable.empty<Int>()

        val exRange = Observable.range(1,5)

        /* Example Create, Never, Empty, Range */

        println("Starting")
        exCreate.subscribe (
                {integer -> println("Element: " + integer!!)  },
                {error -> println("ah! Error!$error")},
                {println("Complete!")})
        println("Exit")

        exNever.subscribe(
                { println("This should never be printed!") },
                { println("Or this!") },
                { println("This neither!") })

        exEmpty.subscribe(
                { println("This should never be printed!") },
                { println("Or this!") },
                { println("Done will be printed.") })

        exRange.subscribe(
                { i ->println(i)},
                { e -> e.printStackTrace()},
                { println()}
        )
    }
}
