package com.gosty.presentation.support.question

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.gosty.model.Question
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionDetailsDialogParams(
    val question: Question,
    @DrawableRes val descriptionImage: Int
) : Parcelable