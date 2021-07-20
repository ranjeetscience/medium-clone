package com.example.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.conduit.R
import com.example.conduit.databinding.FragmentLoginBinding
import com.example.conduit.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.login.btSubmit.setOnClickListener {
            if (_binding.login.etEmail.text.toString().isEmpty()
                || _binding.login.etPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(context, "Fill all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loginViewModel.login(
                email = _binding.login.etEmail.text.toString(),
                password = _binding.login.etPassword.text.toString()
            )
        }

        loginViewModel.userResponse.observe(viewLifecycleOwner){
            Navigation.findNavController(view).navigateUp()
        }

        _binding.login.tvSubtitle.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_nav_login_to_signUpFragment)
        }

    }


}