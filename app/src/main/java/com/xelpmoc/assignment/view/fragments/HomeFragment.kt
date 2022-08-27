package com.xelpmoc.assignment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xelpmoc.assignment.databinding.FragmentHomeBinding
import com.xelpmoc.assignment.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import java.math.BigInteger


class HomeFragment : Fragment() {

    private lateinit var mBinding:FragmentHomeBinding
    private  val homeViewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(mBinding!!.toolbar.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            (activity as AppCompatActivity).title = "Home"
        }

        homeViewModel.fibonacciNumbers1.observeForever {
            if(it.isNotEmpty()){
                var string = ""
                for (i in it){
                   // if(i != 0)
                    string += "${i}, "
                }
                mBinding.tvprint1.setText(string)
            }else{

            }
        }

        homeViewModel.fibonacciNumbers2.observeForever {
            if(it.isNotEmpty()){
                var string = ""
                for (i in it){
                    string += "${i}, "
                }
                mBinding.tvprint2.setText(string)
            }else{

            }
        }

        mBinding.btnCalculate.setOnClickListener {
            val num1  = mBinding.tvNum1.text.toString().trim();
            val num2 = mBinding.tvNum2.text.toString().trim();
            try {
                if(num1.isNotEmpty()) {
                       homeViewModel.calculateFibonacci1(num1.toLong())
                }
                if(num2.isNotEmpty())
                    homeViewModel.calculateFibonacci2(num2.toLong())
            } catch (e: NumberFormatException) {
               Toast.makeText(requireContext(),"input must be between ${Int.MIN_VALUE} to ${Int.MAX_VALUE}",Toast.LENGTH_LONG).show()
            }

        }

    }
}