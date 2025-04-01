package com.geeks.mvp.domain.usecases.networkUseCases

import com.geeks.mvp.domain.model.Example
import com.geeks.mvp.domain.repository.ApiRepository
import com.geeks.mvp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

class GetExampleUseCase(
    private val apiRepository: ApiRepository,
) {
    suspend operator fun invoke(

    ): Flow<Either<Throwable, Example>> {
        return apiRepository.exampleRequest()
    }
}