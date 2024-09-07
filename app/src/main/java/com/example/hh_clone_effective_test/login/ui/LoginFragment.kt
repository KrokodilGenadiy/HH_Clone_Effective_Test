package com.example.hh_clone_effective_test.login.ui

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
import androidx.appcompat.content.res.AppCompatResources
import com.example.hh_clone_effective_test.R
import com.example.hh_clone_effective_test.core.ui.MainActivity
import com.example.hh_clone_effective_test.databinding.FragmentLoginBinding
import com.example.hh_clone_test.util.isValidEmail

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
                    (requireActivity() as MainActivity).launchCodeFragment(loginEt.text.toString())
                } else {
                    isError = true
                    changeBackground(R.drawable.error_edit_text_background)
                }
            }
            clearButton.setOnClickListener {
                isError = false
                loginEt.setText("")
                changeBackground(R.drawable.black_rounded_corners_background)
            }
            loginEt.apply {
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        continueBtn.isEnabled = !s.isNullOrEmpty()
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (isError) {
                            isError = false
                            changeBackground(R.drawable.black_rounded_corners_background)
                        }
                        continueBtn.isEnabled = !s.isNullOrEmpty()
                        if (!s.isNullOrEmpty()) {
                            clearButton.visibility = VISIBLE
                            loginEt.setCompoundDrawables(null, null, null, null)
                        } else {
                            clearButton.visibility = GONE
                            loginEt.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_responses,
                                0,
                                0,
                                0
                            )
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                        // detekt
                    }
                })
                onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        //  loginEt.setCompoundDrawables(null, null, null, null);
                    } else {
                        loginEt.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_responses,
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
}