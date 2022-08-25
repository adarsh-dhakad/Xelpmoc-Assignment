package com.xelpmoc.assignment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xelpmoc.assignment.databinding.FragmentHomeBinding
import com.xelpmoc.assignment.viewmodel.HomeViewModel


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
            if (mBinding.tvNum1.text.toString().isNotEmpty())
                  homeViewModel.calculateFibonacci1(mBinding.tvNum1.text.toString().trim().toInt())
            if(mBinding.tvNum2.text.toString().trim().isNotEmpty())
                  homeViewModel.calculateFibonacci2(mBinding.tvNum2.text.toString().trim().toInt())

        }

    }
}