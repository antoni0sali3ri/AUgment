package com.github.antoni0sali3ri.augment.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import java.util.*

class DatePickerDialog(val title: String, override var listener: ResultListener<Date>) : TypedDialog<Date>() {

    private lateinit var datePicker: DatePicker

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        datePicker = DatePicker(requireContext())
        return requireActivity().alertDialog {
            title = {
                text = this@DatePickerDialog.title
            }
            content = view(datePicker)
        }
    }
}