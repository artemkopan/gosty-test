package com.gosty.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(val id: Int, val title: String, val description: String) : Parcelable