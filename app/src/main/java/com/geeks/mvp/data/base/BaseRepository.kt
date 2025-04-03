package com.geeks.mvp.data.base

import com.geeks.mvp.data.mappers.toDomain
import com.geeks.mvp.domain.model.Example
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

abstract class BaseRepository {

    protected fun <T> makeRequest(
        request: suspend () -> T,
    ): Flow<Either<Throwable, T>> {
        return flow {
            try {
                val response = request()
                if (response != null) {
                    emit(Either.Right(response))
                } else {
                    emit(Either.Left(Exception("Ошибка")))
                }
            } catch (e: Exception) {
                emit(Either.Left(error = e))
            }
        }
    }


//    override suspend fun exampleRequest(): Flow<Either<Throwable, Example>> {

//                if (response.isSuccessful && response.body() != null) {
//                    response.body()?.let { data ->
//                        emit(Either.Right(data.toDomain()))
//                    }
//                } else {
//                    emit(Either.Left(Exception("Ошибка: ${response.code()}")))
//                }
//    }
}
