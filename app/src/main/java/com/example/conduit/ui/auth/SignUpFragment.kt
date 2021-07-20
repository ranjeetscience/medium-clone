package com.example.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.api.model.entity.RegisterUser
import com.example.api.model.request.RegisterRequest
import com.example.conduit.databinding.FragmentSignupBinding
import com.example.conduit.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var _binding: FragmentSignupBinding
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btSubmit.setOnClickListener {
            if (_binding.etEmail.text.isNullOrEmpty()
                || _binding.etPassword.text.isNullOrEmpty()
                || _binding.etUsername.text.isNullOrEmpty()
            ) {
                return@setOnClickListener
            }

            signUpViewModel.signUp(
                RegisterRequest(
                    RegisterUser(
                        email = _binding.etEmail.text.toString(),
                        password = _binding.etPassword.text.toString(),
                        username = _binding.etUsername.text.toString()
                    )
                )
            )
        }

        _binding.tvSubtitle.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }
}