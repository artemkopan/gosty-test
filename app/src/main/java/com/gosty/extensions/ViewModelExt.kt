@file:Suppress("UNCHECKED_CAST")

package com.gosty.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

inline fun <reified T : ViewModel> Fragment.createFactory(
    crossinline createViewModel: () -> T
): AbstractSavedStateViewModelFactory {
    return createFactory(this, arguments ?: Bundle.EMPTY, createViewModel)
}

inline fun <reified T : ViewModel> createFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle,
    crossinline createViewModel: () -> T
): AbstractSavedStateViewModelFactory {
    return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T = createViewModel() as T
    }
}