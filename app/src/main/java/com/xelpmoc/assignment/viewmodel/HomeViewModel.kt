package com.xelpmoc.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val fibonacciNumbers1 = MutableLiveData<List<Long>>()
    val fibonacciNumbers2 = MutableLiveData<List<Long>>()
    fun calculateFibonacci1(num1: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val list = fib(num1)
                withContext(Dispatchers.Main) {
                    fibonacciNumbers1.value = list
                }
            }
        }
    }

    private  fun fib(n: Long): ArrayList<Long> {
        var a: Long = 0
        var b: Long = 1
        var c: Long
        var j = 1
        for (i in 1..n) {
            //        delay(1)
            j++
            c = a + b
            a = b
            b = c
        }
        val series = arrayListOf<Long>()
        series.add(a)
        series.add(b)
        /* generate the fibonacci sequence and store in the array */
        for (i in 2..9) series.add(series[i - 1] + series[i - 2])
        return series
    }

    fun calculateFibonacci2(num2: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val list = fib(num2)
                withContext(Dispatchers.Main) {
                    fibonacciNumbers2.value = list
                }
            }

        }
    }
}