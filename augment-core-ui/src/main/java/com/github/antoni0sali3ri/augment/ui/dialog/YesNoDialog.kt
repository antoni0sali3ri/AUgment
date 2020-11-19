package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.antoni0sali3ri.augment.ui.dialog.TypedDialog.ResultListener

class YesNoDialog(
    @StringRes val titleRes: Int,
    override var listener: ResultListener<Boolean> = ResultListener {  },
    @StringRes val messageRes: Int? = null,
    @DrawableRes val iconRes: Int? = null,
    @StringRes val yesRes: Int = android.R.string.yes,
    @StringRes val noRes: Int = android.R.string.no
) : TypedDialog<Boolean>() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().alertDialog {
            title = {
                text = string(titleRes)
                icon = drawable(iconRes)
            }
            message = string(messageRes)
            buttons = {
                positive = {
                    title = string(yesRes)
                    action = { dialog ->
                        listener.onDialogResult(true)
                        dialog.dismiss()
                    }
                }
                negative = {
                    title = string(noRes)
                    action = { dialog ->
                        listener.onDialogResult(false)
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}