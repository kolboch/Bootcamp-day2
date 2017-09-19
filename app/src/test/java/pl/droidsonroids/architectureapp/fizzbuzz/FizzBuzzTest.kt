package pl.droidsonroids.architectureapp.fizzbuzz

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Karol on 2017-09-19.
 */
class FizzBuzzTest {

    lateinit var fizzBuzz: FizzBuzz

    @Before
    fun setUp() {
        fizzBuzz = FizzBuzz()
    }

    @Test
    fun fizzBuzzGeneratesNumber() {
        assertEquals("1", fizzBuzz.generateAnswer(1))
    }

    @Test
    fun fizzBuzzWhenTwoReturnTwo() {
        assertEquals("2", fizzBuzz.generateAnswer(2))
    }

    
}