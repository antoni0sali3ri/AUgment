package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.github.antoni0sali3ri.auc.Transform
import com.github.antoni0sali3ri.augment.ui.dialog.AlertDialogContract.Companion.itemsMultiple
import com.github.antoni0sali3ri.augment.ui.dialog.AlertDialogContract.Companion.itemsSingle

abstract class BaseValuePickerDialog<T>(
    @StringRes val titleRes: Int,
    val values: List<T>,
    val valueTransform: Transform<T, Any> = { it as Any },
    @StringRes val messageRes: Int? = null,
    @DrawableRes val iconRes: Int? = null
): DialogFragment() {

    protected val displayedValues = values.map { valueTransform(it).toString() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().alertDialog {
            this.title = {
                text = string(titleRes)
                icon = drawable(iconRes)
            }
            this.message = string(messageRes)
            content = dialogContent
        }

    }

    abstract val dialogContent : AlertDialogContract.ContentContract

    protected open val additionalDialogSetup: AlertDialogContract.() -> Unit = {}

    protected open fun onValueSelected(index: Int) {}

    protected open fun onNegativeButtonClicked() {}

    protected open fun onPositiveButtonClicked() {}

    companion object {
        val DEFAULT_POSITIVE_TITLE = android.R.string.ok
        val DEFAULT_NEGATIVE_TITLE = android.R.string.cancel
    }
}

class MultiValuePickerDialog<T>(
    titleRes: Int,
    values: List<T>,
    private val listener: ResultListener<T>,
    selected: List<T> = emptyList(),
    valueTransform: Transform<T, Any> = { it as Any },
    iconRes: Int? = null,
    messageRes: Int? = null,
    private val positiveButtonTitle: Int = DEFAULT_POSITIVE_TITLE,
    private val negativeButtonTitle: Int = DEFAULT_NEGATIVE_TITLE
): BaseValuePickerDialog<T>(
    titleRes,
    values,
    iconRes = iconRes,
    messageRes = messageRes,
    valueTransform = valueTransform
) {
    private val selectedValues = mutableListOf<T>().apply {
        addAll(selected)
    }

    fun interface ResultListener<T> {
        fun onItemsSelected(values: List<T>)
    }

    override val dialogContent: AlertDialogContract.ContentContract = itemsMultiple {
        this.values = displayedValues
        checked = selected.map { this@MultiValuePickerDialog.values.indexOf(it) }.filter { it >= 0 }
        action = { _, index, checked ->
            if (checked) {
                selectedValues += values[index]
            } else {
                selectedValues -= values[index]
            }
        }
    }

    override val additionalDialogSetup: AlertDialogContract.() -> Unit = {
        buttons = {
            positive = {
                title = string(positiveButtonTitle)!!
                action = { di -> listener.onItemsSelected(selectedValues) }
            }
            negative = {
                title = string(negativeButtonTitle)!!
                action = { di -> di.dismiss() }
            }
        }
    }

    override fun onPositiveButtonClicked() {
        listener.onItemsSelected(selectedValues)
    }

    companion object {
    }
}

class ValuePickerDialog<T>(
    @StringRes titleRes: Int,
    values: List<T>,
    private val listener: ResultListener<T>,
    @DrawableRes iconRes: Int? = null,
    @StringRes messageRes: Int? = null,
    private val negativeButtonTitle: Int = DEFAULT_NEGATIVE_TITLE,
    valueTransform: Transform<T, Any> = { it as Any }
) : BaseValuePickerDialog<T>(
    titleRes,
    values,
    iconRes = iconRes,
    messageRes = messageRes,
    valueTransform = valueTransform
) {
    fun interface ResultListener<T> {
        fun onItemSelected(value: T)
    }

    override val dialogContent: AlertDialogContract.ContentContract = itemsSingle {
        this.values = displayedValues
        action = { dialog, index ->
            listener.onItemSelected(values[index])
            dialog.dismiss()
        }
    }

    override val additionalDialogSetup: AlertDialogContract.() -> Unit = {
        buttons = {
            negative = {
                title = string(negativeButtonTitle)!!
                action = { dialog -> dialog.cancel() }
            }
        }
    }

}