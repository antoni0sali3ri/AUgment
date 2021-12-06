package com.github.antoni0sali3ri.augment.ui.widget.numberpicker

import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.github.antoni0sali3ri.augment.ui.dialog.DatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

fun TextView.setUpAsDatePicker(fragmentManager: FragmentManager) = TextViewDateTimePicker(this, fragmentManager)

class TextViewDateTimePicker(val textView: TextView, val fragmentManager: FragmentManager, val dateFormatString: String = "yyyy-MM-dd HH:mm") {
    private val datePickerDialog by lazy { DatePickerDialog("", update) }
    private val dateFormat = SimpleDateFormat(dateFormatString)

    init {
        textView.setOnClickListener {
            datePickerDialog.show(fragmentManager, "datePickerDialog")
        }
    }

    private val update: (Date) -> Unit = { date ->
        textView.text = date.format()
    }

    private fun Date.format() : String = dateFormat.format(this)
}