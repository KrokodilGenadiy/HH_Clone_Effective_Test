package com.example.hh_clone_test.util.error_handling

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        SERVER_ERROR,
        PAYLOAD_TOO_LARGE,
        NOT_FOUND,
        UNKNOWN
    }
    enum class Local: DataError {
        DISK_FULL
    }
}