package com.choice.core.base

import kotlinx.coroutines.flow.Flow

interface IBaseUseCase<in I, out R: Any> {
    suspend operator fun invoke(input: I): Flow<R>
}