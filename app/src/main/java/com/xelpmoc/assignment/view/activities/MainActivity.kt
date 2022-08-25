package com.xelpmoc.assignment.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.xelpmoc.assignment.R
import com.xelpmoc.assignment.databinding.ActivityMainBinding
import com.xelpmoc.assignment.view.fragments.HomeFragment
import com.xelpmoc.assignment.view.fragments.LoginFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        val fm: FragmentManager = this.supportFragmentManager
        if(currentUser == null){
            val fragment = LoginFragment()
            fm.beginTransaction()
                //      .add(R.id.main_contenier, fragment).commit()
                .replace(R.id.main_contenier,fragment).commit()
        }else{
            val fragment = HomeFragment()
            fm.beginTransaction()
                .replace(R.id.main_contenier,fragment).commit()
        }
    }
}