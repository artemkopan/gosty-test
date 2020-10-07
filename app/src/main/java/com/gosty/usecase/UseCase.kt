package com.gosty.usecase

fun interface UseCase<Params, Result> {

    fun execute(params: Params): Result

}