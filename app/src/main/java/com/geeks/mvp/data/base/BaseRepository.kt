package com.geeks.mvp.data.base

import com.geeks.mvp.presentation.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository() {

    protected fun <T> makeRequest(
        request: suspend () -> T,
    ): Flow<Either<Throwable, T>> = flow {
        try {
            val response = request()
            if (response != null) {
                emit(Either.Success(response))
            } else {
                emit(Either.Error(Exception("Ошибка")))
            }
        } catch (e: Exception) {
            emit(Either.Error(error = e))
        }
}


}
