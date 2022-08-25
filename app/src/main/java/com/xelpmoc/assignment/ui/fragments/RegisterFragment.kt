package com.xelpmoc.assignment.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.xelpmoc.assignment.R
import com.xelpmoc.assignment.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var mBinding: FragmentRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBackButton()
        setHasOptionsMenu(true)
    }
    private fun showBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            "Later Transaction",
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(mBinding!!.toolbar.toolbar)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            (activity as AppCompatActivity).title = "Register"
        }
        val username = mBinding!!.username
        val password = mBinding!!.password
        val login = mBinding!!.login
        val loading = mBinding!!.loading
        username.afterTextChanged {
            if (!isUserNameValid(username.text.toString())) {
                username.error = "Please enter Correct Email"
            }
            login.isEnabled = isUserNameValid(username.text.toString()) &&
                    isPasswordValid(password.text.toString())
        }

        password.apply {
            afterTextChanged {
                if (!isPasswordValid(password.text.toString())) {
                    password.error = "Enter 6 digit password"
                }
                login.isEnabled = isUserNameValid(username.text.toString()) &&
                        isPasswordValid(password.text.toString())
            }
        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
           val auth =  FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("register fragment", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUiWithUser(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("register fragment", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
              //          updateUI(null)
                    }
                }

        }
        mBinding!!.tvLogin.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "Later Transaction",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun updateUiWithUser(user: FirebaseUser?) {
        val welcome = getString(R.string.welcome)
 //       val displayName = user?.displayName
        Toast.makeText(
            requireContext(),
            "$welcome",
            Toast.LENGTH_LONG
        ).show()
        val fm: FragmentManager = requireActivity().supportFragmentManager
        val fragment = HomeFragment()
        fm.beginTransaction()
            //      .add(R.id.main_contenier, fragment).commit()
            .replace(R.id.main_contenier, fragment).commit()
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}