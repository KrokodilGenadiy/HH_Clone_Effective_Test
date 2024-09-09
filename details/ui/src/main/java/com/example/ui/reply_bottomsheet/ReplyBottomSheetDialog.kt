package com.example.ui.reply_bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ui.databinding.ReplyBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReplyBottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: ReplyBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReplyBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reply = arguments?.getString("reply")
        val title = arguments?.getString("job_title")
        with (binding) {
            replyDialogJobTitle.text = title
            if (reply != null) {
                showEt()
                replyEt.setText(reply)
            }
            addReply.setOnClickListener {
                showEt()
            }
            replyDialogBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun showEt() {
        with (binding) {
            replyEt.visibility = VISIBLE
            addReply.visibility = GONE
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}