package pl.droidsonroids.architectureapp.fizzbuzz

/**
 * Created by Karol on 2017-09-19.
 */
class FizzBuzz {

    fun generateAnswer(number: Int): String {
        var answer: String = ""
        if (number % 3 == 0) {
            answer = "fizz"
        } else {
            answer = number.toString()
        }
        return answer
    }

}