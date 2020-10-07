package com.gosty.presentation.support

import com.gosty.model.Question

data class SupportState(
    val questions: List<Question> = emptyList(),
    val throwable: Throwable? = null
)