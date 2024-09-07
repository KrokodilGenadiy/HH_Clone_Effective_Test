package com.example.hh_clone_effective_test.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.hh_clone_effective_test.core.ui.MainActivity
import com.example.hh_clone_effective_test.databinding.FragmentCodeBinding


class CodeFragment : Fragment() {

    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = arguments?.getString("email") ?: "example@mail.ru"
        with(binding) {
            title.text = "Отправили код на $email"
            password1.requestFocus()
            password1.setCursorVisible(true)
            setUpTextWatcher(password1, password2)
            setUpTextWatcher(password2, password3)
            setUpTextWatcher(password3, password4)
            setUpLastPasswordWatcher()
            setUpContinueBtn()
        }
    }

    private fun continueBtnState() {
        with(binding) {
            if (password1.text.isNotEmpty() && password2.text.isNotEmpty() && password3.text.isNotEmpty() && password4.text.isNotEmpty())
                continueBtn.isEnabled = true
            else
                continueBtn.isEnabled = false
        }

    }

    private fun setUpTextWatcher(editText1: EditText, editText2: EditText) {
        val sb = StringBuilder()
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((sb.isEmpty()) and (editText1.text.length == 1)) {
                    sb.append(s)
                    editText1.clearFocus()
                    editText2.requestFocus()
                    editText2.setCursorVisible(true)

                }
            }

            override fun afterTextChanged(s: Editable?) {
                continueBtnState()
            }
        })
    }

    private fun setUpContinueBtn() {
        binding.continueBtn.setOnClickListener {
            (requireActivity() as MainActivity).launchSearchFragment()
        }
    }

    private fun setUpLastPasswordWatcher() {
        binding.password4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                continueBtnState()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}