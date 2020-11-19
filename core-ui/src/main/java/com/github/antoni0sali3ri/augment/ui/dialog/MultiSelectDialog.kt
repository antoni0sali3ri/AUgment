package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.Dialog
import android.os.Bundle
import com.github.antoni0sali3ri.auc.Transform
import com.github.antoni0sali3ri.augment.ui.dialog.AlertDialogContract.Companion.itemsMultiple
import com.github.antoni0sali3ri.augment.ui.dialog.TypedDialog.ResultListener

class MultiSelectDialog<T>(
    val titleRes: Int,
    val items: List<T>,
    override var listener: ResultListener<List<T>> = ResultListener {  },
    selected: List<T> = emptyList(),
    valueTransform: Transform<T, Any> = { it as Any },
    val iconRes: Int? = null,
    private val positiveButtonTitle: Int = DEFAULT_POSITIVE_TITLE,
    private val negativeButtonTitle: Int = DEFAULT_NEGATIVE_TITLE
): TypedDialog<List<T>>() {

    private val selectedValues = mutableListOf<T>().apply {
        addAll(selected)
    }

    private val displayedValues = items.map(valueTransform).map { it.toString() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireContext().alertDialog {
            title = {
                text = string(titleRes)
                icon = drawable(iconRes)
            }
            buttons = {
                positive = {
                    title = string(positiveButtonTitle)
                    action = {
                        listener.onDialogResult(selectedValues)
                        it.dismiss()
                    }
                }
                negative = {
                    title = string(negativeButtonTitle)
                    action = { it.cancel() }
                }
            }
            content = itemsMultiple {
                values = displayedValues
                action = { _, index, checked ->
                    if (checked)
                        selectedValues.add(items[index])
                    else
                        selectedValues.remove(items[index])
                }
            }
        }
    }

    companion object {
        const val DEFAULT_NEGATIVE_TITLE = android.R.string.cancel
        const val DEFAULT_POSITIVE_TITLE = android.R.string.ok
    }
}