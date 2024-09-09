package com.example.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.login.ui.R
import com.login.ui.databinding.FragmentCodeBinding


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
        val email = requireArguments().getString("email") ?: "example@mail.ru"
        with(binding) {
            codeHeaderTitle.text = "Отправили код на $email"
            codePassword1.requestFocus()
            codePassword1.setCursorVisible(true)
            setUpTextWatcher(codePassword1, codePassword2)
            setUpTextWatcher(codePassword2, codePassword3)
            setUpTextWatcher(codePassword3, codePassword4)
            setUpLastPasswordWatcher()
            setUpContinueBtn()
        }
    }

    private fun continueBtnState() {
        with(binding) {
            codeContinueBtn.isEnabled = codePassword1.text.isNotEmpty() && codePassword2.text.isNotEmpty() && codePassword3.text.isNotEmpty() && codePassword4.text.isNotEmpty()
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
        binding.codeContinueBtn.setOnClickListener {
            findNavController().setGraph(com.core.ui.R.navigation.main_navigation_graph)
            findNavController().navigate(com.core.ui.R.id.searchFragment)
        }
    }

    private fun setUpLastPasswordWatcher() {
        binding.codePassword4.addTextChangedListener(object : TextWatcher {
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

    companion object {
        private const val SEARCH_DEEP_LINK = "android-app://example.google.app/SearchFragment"
    }
}