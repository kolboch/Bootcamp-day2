package pl.droidsonroids.architectureapp.fizzbuzz

/**
 * Created by Karol on 2017-09-19.
 */
class FizzBuzz {

    fun generateAnswer(number: Int): String {
        return when {
            number % 3 == 0 -> "fizz"
            else -> number.toString()
        }
    }

}