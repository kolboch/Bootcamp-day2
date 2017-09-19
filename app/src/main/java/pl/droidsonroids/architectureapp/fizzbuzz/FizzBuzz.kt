package pl.droidsonroids.architectureapp.fizzbuzz

/**
 * Created by Karol on 2017-09-19.
 */
class FizzBuzz {

    fun generateAnswer(number: Int): String {
        return when {
            number % 3 == 0 && number % 5 == 0 -> "FizzBuzz"
            number % 3 == 0 -> "Fizz"
            number % 5 == 0 -> "Buzz"
            else -> number.toString()
        }
    }

}