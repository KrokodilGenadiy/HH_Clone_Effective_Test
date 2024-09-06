package com.example.hh_clone_test.search.data.entities

import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapterItem

data class ConfirmButton(
    val listSize: Int
) : DelegateAdapterItem {
    override fun id() = listSize

    override fun content() = listSize
}
