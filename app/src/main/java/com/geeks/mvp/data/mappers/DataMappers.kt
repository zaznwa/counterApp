package com.geeks.mvp.data.mappers

import com.geeks.mvp.data.model.CounterDto
import com.geeks.mvp.domain.model.CounterEntity
import com.geeks.mvp.domain.model.OperationType

fun CounterDto.toDomain(): CounterEntity {
    return CounterEntity(
        count = count,
        operationType = OperationType.fromString(operationType)
    )
}
fun counterDtoToDomain (dto: CounterDto): CounterEntity {
    return CounterEntity(
        count = dto.count,
        operationType = OperationType.fromString(dto.operationType)
    )
}
