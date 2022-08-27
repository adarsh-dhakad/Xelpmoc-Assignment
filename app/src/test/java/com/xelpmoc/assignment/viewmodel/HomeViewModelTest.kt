package com.xelpmoc.assignment.viewmodel


import com.google.common.truth.Truth
import org.junit.Test
import java.math.BigInteger

class HomeViewModelTest{

    @Test
    fun `test valid`(){

        fun fib(n: Int): ArrayList<BigInteger> {
            var a: BigInteger = BigInteger.valueOf(0)
            var b: BigInteger = BigInteger.valueOf(1)
            var c: BigInteger
            for (j in 2..n) {
                c = a.add(b)
                a = b
                b = c
            }
            val series = arrayListOf<BigInteger>()
            series.add(a)
            series.add(b)
            /* generate the fibonacci sequence and store in the array */
            for (i in 2..10) series.add(series[i - 1].add(series[i - 2]))
            return series
        }

        Truth.assertThat(fib(10))
    }

}