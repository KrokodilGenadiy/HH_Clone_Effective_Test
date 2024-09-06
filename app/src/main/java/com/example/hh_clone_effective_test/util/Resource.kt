package com.example.hh_clone_test.util

import com.example.hh_clone_test.util.error_handling.Error

typealias RootError = Error

sealed interface Resource<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Resource<D, E>
    data class Error<out D, out E: RootError>(val error: E): Resource<D, E>
}