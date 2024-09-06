package com.example.hh_clone_effective_test.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
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
        with(binding) {
            password1.requestFocus()
            password1.setCursorVisible(true)
            setUpTextWatcher(password1,password2)
            setUpTextWatcher(password2,password3)
            setUpTextWatcher(password3,password4)
        }
    }


    private fun setUpTextWatcher(editText1: EditText, editText2: EditText) {
        val sb = StringBuilder()
        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(sb.length ==1)
                {
                    sb.deleteCharAt(0);
                }
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
                if(sb.isEmpty())
                {
                    editText1.requestFocus();
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}