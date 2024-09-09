package com.example.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.OnFocusChangeListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.core.ui.R as coreR
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.utils.isValidEmail
import com.login.ui.R
import com.login.ui.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isError = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.emailLayout) {
            continueBtn.setOnClickListener {
                if (loginEt.text.isValidEmail()) {
                    findNavController().navigate(
                        R.id.action_loginFragment_to_codeFragment,
                        bundleOf(ARGS_EMAIL to loginEt.text.toString())
                    )
                } else {
                    isError = true
                    changeBackground(coreR.drawable.error_edit_text_background)
                }
            }
            clearButton.setOnClickListener {
                isError = false
                loginEt.setText("")
                changeBackground(coreR.drawable.black_rounded_corners_background)
            }
            loginEt.apply {
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (isError) {
                            isError = false
                            changeBackground(coreR.drawable.black_rounded_corners_background)
                        }
                        continueBtn.isEnabled = !s.isNullOrEmpty()
                        if (!s.isNullOrEmpty()) {
                            clearButton.visibility = VISIBLE
                            loginEt.setCompoundDrawables(null, null, null, null)
                        } else {
                            clearButton.visibility = GONE
                            loginEt.setCompoundDrawablesWithIntrinsicBounds(
                                coreR.drawable.ic_responses,
                                0,
                                0,
                                0
                            )
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
                onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus) {
                        loginEt.setCompoundDrawablesWithIntrinsicBounds(
                            coreR.drawable.ic_responses,
                            0,
                            0,
                            0
                        );
                    }
                }
            }

        }
    }

    private fun changeBackground(backgroundId: Int) {
        binding.emailLayout.errorText.visibility = if (isError)
            VISIBLE
        else
            GONE
        binding.emailLayout.emailInputContainer.background = AppCompatResources.getDrawable(
            requireContext(),
            backgroundId
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ARGS_EMAIL = "email"
    }
}