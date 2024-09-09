package com.example.data.entities

import com.example.data.adapter_interface.DelegateAdapterItem


data class ConfirmButton(
    val listSize: Int
) : DelegateAdapterItem {
    override fun id() = listSize

    override fun content() = listSize
}
