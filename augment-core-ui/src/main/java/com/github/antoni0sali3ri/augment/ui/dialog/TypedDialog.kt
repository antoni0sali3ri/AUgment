package com.github.antoni0sali3ri.augment.ui.dialog

import androidx.fragment.app.DialogFragment

abstract class TypedDialog<T>: DialogFragment() {

    abstract var listener: ResultListener<T>

    fun interface ResultListener<R> {
        fun onDialogResult(result: R)
    }
}