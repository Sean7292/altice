package com.devflowteam.domain.util.error

sealed interface DataError: Error {
    enum class Network: Error {
        NO_INTERNET,
        UNKNOWN
    }
    enum class Local: Error {
        DISK_FULL,
        UNKNOWN
    }
}