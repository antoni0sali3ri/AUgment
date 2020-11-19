package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ListAdapter
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat

class AlertDialogContract(private val context: Context) {

    lateinit var title: TitleContract.() -> Unit
    var message: String? = null
    var content: ContentContract? = null
    var buttons: ButtonsContract.() -> Unit = {}


    interface BaseContract {
        fun build(builder: AlertDialog.Builder)
    }

    class TitleContract : BaseContract {
        var text: String? = null
        var icon: Drawable? = null
        var customView: View? = null

        override fun build(builder: AlertDialog.Builder) {
            if (customView != null) {
                builder.setCustomTitle(customView!!)
            } else {
                if (text != null) builder.setTitle(text)
                if (icon != null) builder.setIcon(icon)
            }
        }
    }

    class ButtonsContract : BaseContract {
        var positive: (ButtonContract.Positive.() -> Unit)? = null
        var negative: (ButtonContract.Negative.() -> Unit)? = null
        var neutral: (ButtonContract.Neutral.() -> Unit)? = null

        override fun build(builder: AlertDialog.Builder) {
            if (positive != null)
                ButtonContract.Positive().apply(positive!!).build(builder)
            if (negative != null)
                ButtonContract.Negative().apply(negative!!).build(builder)
            if (neutral != null)
                ButtonContract.Neutral().apply(neutral!!).build(builder)
        }
    }

    sealed class ButtonContract : BaseContract {
        var title: String? = null
        var action: (DialogInterface) -> Unit = {}

        class Positive : ButtonContract() {
            override fun build(builder: AlertDialog.Builder) {
                builder.setPositiveButton(title) { di, _ -> action(di) }
            }
        }

        class Negative : ButtonContract() {
            override fun build(builder: AlertDialog.Builder) {
                builder.setNegativeButton(title) { di, _ -> action(di) }
            }
        }

        class Neutral : ButtonContract() {
            override fun build(builder: AlertDialog.Builder) {
                builder.setNeutralButton(title) { di, _ -> action(di) }
            }
        }
    }

    sealed class ContentContract : BaseContract {

        class ViewContract : ContentContract() {
            var view: View? = null

            override fun build(builder: AlertDialog.Builder) {
                if (view != null) builder.setView(view)
            }
        }

        abstract class BaseItemsContract : ContentContract() {
            var values: List<String> = emptyList()
        }

        class ItemsContract : BaseItemsContract() {
            var action: (DialogInterface, Int) -> Unit = { _, _ -> }

            override fun build(builder: AlertDialog.Builder) {
                builder.setItems(values.toTypedArray()) { di, i -> action(di, i) }
            }
        }

        class SingleChoiceItemsContract : BaseItemsContract() {
            var checked: Int? = null
            var adapter: ListAdapter? = null
            var action: (DialogInterface, Int) -> Unit = { _, _ -> }
            var cursor: Cursor? = null
            var labelColumn: String? = null

            override fun build(builder: AlertDialog.Builder) {
                val checked = checked ?: -1
                when {
                    adapter != null -> builder.setSingleChoiceItems(
                        adapter!!,
                        checked
                    ) { di, i -> action(di, i) }
                    cursor != null -> builder.setSingleChoiceItems(
                        cursor,
                        checked,
                        labelColumn
                    ) { di, i -> action(di, i) }
                    else -> builder.setSingleChoiceItems(
                        values.toTypedArray(),
                        checked
                    ) { di, i -> action(di, i) }
                }
            }
        }

        class MultiChoiceItemsContract : BaseItemsContract() {
            var action: (DialogInterface, Int, Boolean) -> Unit = { _, _, _ -> }
            var checked: List<Int> = emptyList()
            var cursor: Cursor? = null
            var labelColumn: String? = null
            var checkedColumn: String? = null

            override fun build(builder: AlertDialog.Builder) {
                when {
                    cursor != null -> builder.setMultiChoiceItems(
                        cursor,
                        checkedColumn,
                        labelColumn
                    ) { di, i, c -> action(di, i, c) }
                    else -> builder.setMultiChoiceItems(
                        values.toTypedArray(),
                        values.mapIndexed { i, _ -> i in checked }.toBooleanArray()
                    ) { di, i, c -> action(di, i, c) }
                }
            }

        }
    }

    fun build(builder: AlertDialog.Builder) {
        TitleContract().apply(title).build(builder)
        if (message != null) builder.setMessage(message)
        ButtonsContract().apply(buttons).build(builder)
        content?.build(builder)
    }

    fun string(@StringRes stringRes: Int?) =
        if (stringRes == null) null else context.resources.getString(stringRes)

    fun array(@ArrayRes arrayRes: Int?) =
        if (arrayRes == null) null else context.resources.getStringArray(arrayRes)

    fun drawable(@DrawableRes drawableRes: Int?, theme: Resources.Theme? = null): Drawable? =
        if (drawableRes == null)
            null
        else ResourcesCompat.getDrawable(
            context.resources,
            drawableRes,
            theme
        )


    companion object {
        fun view(builder: ContentContract.ViewContract.() -> Unit): ContentContract =
            ContentContract.ViewContract().apply(builder)

        fun items(builder: ContentContract.ItemsContract.() -> Unit): ContentContract =
            ContentContract.ItemsContract().apply(builder)

        fun itemsSingle(builder: ContentContract.SingleChoiceItemsContract.() -> Unit): ContentContract =
            ContentContract.SingleChoiceItemsContract()
                .apply(builder)

        fun itemsMultiple(builder: ContentContract.MultiChoiceItemsContract.() -> Unit): ContentContract =
            ContentContract.MultiChoiceItemsContract()
                .apply(builder)
    }


}

fun Context.alertDialog(builder: AlertDialogContract.() -> Unit): AlertDialog {
    val alertDialogBuilder = AlertDialog.Builder(this)
    AlertDialogContract(this)
        .apply(builder)
        .build(alertDialogBuilder)
    return alertDialogBuilder.create()
}