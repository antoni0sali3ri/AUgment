package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.antoni0sali3ri.auc.Transform
import com.github.antoni0sali3ri.augment.ui.dialog.AlertDialogContract.Companion.itemsSingle
import com.github.antoni0sali3ri.augment.ui.dialog.TypedDialog.ResultListener

/**
 * Display a list of items to the user and wait for selection.
 *
 * This dialog will return the first value the user clicks on. If the dialog is cancelled the
 * listener will <i>not</i> be called.
 *
 * @param T the type of values
 * @property titleRes string resource for the dialog title
 * @property items the items from which to select one
 * @property listener the result listener (optional)
 * @property iconRes drawable resource for the icon to display next to the dialog title (optional)
 * @property negativeButtonTitle string resource for the dialog's "cancel" button (optional, defaults to {android.R.string.cancel})
 * @param valueTransform transformation method from which to derive the displayed values (optional, defaults to identity function)
 */
class ValuePickerDialog<T>(
    @StringRes val titleRes: Int,
    val items: List<T>,
    override var listener: ResultListener<T> = ResultListener {},
    @DrawableRes val iconRes: Int? = null,
    private val negativeButtonTitle: Int = DEFAULT_NEGATIVE_TITLE,
    valueTransform: Transform<T, Any> = { it as Any }
) : TypedDialog<T>() {

    private val displayedValues = items.map(valueTransform).map { it.toString() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireContext().alertDialog {
            title = {
                text = string(titleRes)
                icon = drawable(iconRes)
            }
            buttons = {
                negative = {
                    title = string(negativeButtonTitle)
                    action = { it.cancel() }
                }
            }
            content = itemsSingle {
                values = displayedValues
                action = { dialog, index ->
                    listener.onDialogResult(items[index])
                    dialog.dismiss()
                }
            }
        }
    }

    companion object {
        const val DEFAULT_NEGATIVE_TITLE = android.R.string.cancel
    }
}

