package com.xelpmoc.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val fibonacciNumbers1 = MutableLiveData<List<Long>>()
    val fibonacciNumbers2 = MutableLiveData<List<Long>>()
    fun calculateFibonacci1(num1: Int) {
        viewModelScope.launch {
                val list = ArrayList<Long>()
                val result = LongArray(num1 + 12)
                /* first two Fibonacci numbers */
                result[0] = 0
                result[1] = 1
                /* generate the fibonacci sequence and store in the array */
                for (i in 2..num1 + 11) {
                    result[i] = result[i - 1] + result[i - 2]
                }
                for (i in num1 until num1 + 10) {
                    list.add(result[i])
                }
                fibonacciNumbers1.value = list
        }
    }

    fun calculateFibonacci2(num2: Int) {
        viewModelScope.launch {

            val list = ArrayList<Long>()
            val result = LongArray(num2 + 12)

            /* first two Fibonacci numbers */
            result[0] = 0
            result[1] = 1
            /* generate the fibonacci sequence and store in the array */
            for (i in 2..num2 + 11) {
                result[i] = result[i - 1] + result[i - 2]
            }
            for (i in num2 until num2 + 10) {
                list.add(result[i])
            }
            fibonacciNumbers2.value = list
        }
    }
}