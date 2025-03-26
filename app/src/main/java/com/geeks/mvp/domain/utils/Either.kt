package com.geeks.mvp.domain.utils

sealed class Either<out L, out R> {
    data class Right<R>(val data: R) : Either<Nothing, R>()
    data class Left<L>(val error: L) : Either<L, Nothing>()
}