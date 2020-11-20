package com.github.antoni0sali3ri.augment.examples.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.antoni0sali3ri.augment.examples.R
import com.github.antoni0sali3ri.augment.format
import com.github.antoni0sali3ri.augment.ui.dialog.TypedDialog
import com.github.antoni0sali3ri.augment.ui.dialog.ValuePickerDialog
import com.github.antoni0sali3ri.augment.ui.dialog.YesNoDialog
import kotlinx.android.synthetic.main.fragment_dialogs.*
import kotlin.random.Random

class DialogExamplesFragment : Fragment() {

    private lateinit var examplesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        examplesList = dialogExamplesList.apply {
            adapter = ExamplesAdapter(childFragmentManager, examples)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    data class DialogExample<T>(
        @StringRes val titleRes: Int,
        @StringRes val descRes: Int,
        @StringRes val buttonCaptionRes: Int = R.string.btnTitleShowDialog,
    ) {
        var supplyTextView: () -> TextView? = { null }
        var resultHandler: (T) -> Unit = {
            val textView = supplyTextView()
            if (textView != null) {
                textView.text = textView.context.format(R.string.resultTextTemplate, it.toString())
            }
        }
        var dialog: TypedDialog<T>? = null
    }

    private val rnd = Random(System.currentTimeMillis())

    val examples: List<DialogExample<*>> by lazy { listOf(
        DialogExample<Boolean>(
            R.string.dialogExamleYesNoTitle, R.string.dialogYesNoDescription,
        ).apply {
            resultHandler = { res ->
                val di = dialog!! as YesNoDialog
                val resultString = if (res) di.yesRes else di.noRes
                supplyTextView()?.text = format(R.string.resultTextTemplate, resultString)
            }
            dialog = YesNoDialog(
                R.string.dialogYesNoTitle,
                listener = { resultHandler(it) },
                noRes = R.string.no,
                yesRes = R.string.yes,
                messageRes = R.string.dialogYesNoMessage
            )
        },
        DialogExample<Int>(
            R.string.dialogExampleSingleSelectIntTitle,
            R.string.dialogExampleSingleSelectIntDescription
        ).apply {
            dialog = ValuePickerDialog(
                R.string.dialogSingleSelectIntTitle,
                (1..10).map { rnd.nextInt() },
                listener = { resultHandler(it) },
            )
        }
    )}

    class ExamplesAdapter(val fm: FragmentManager, val examples: List<DialogExample<*>>) : RecyclerView.Adapter<ExamplesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txtTitle: TextView = view.findViewById(R.id.txtExampleTitle)
            val txtDesc: TextView = view.findViewById(R.id.txtExampleDescription)
            val txtResult: TextView = view.findViewById(R.id.txtDialogResult)
            val btnShow: Button = view.findViewById(R.id.btnShowDialog)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_dialog_example_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val ex = examples[position]
            holder.apply {
                val r = txtTitle.context.resources
                txtTitle.text = "${position + 1}. ${r.getString(ex.titleRes)}"
                txtDesc.text = r.getString(ex.descRes)
                btnShow.text = r.getString(ex.buttonCaptionRes)
                btnShow.setOnClickListener {
                    ex.supplyTextView = { txtResult }
                    ex.dialog?.show(fm, "DialogExample$position")
                }
            }
        }

        override fun getItemCount(): Int {
            return examples.size
        }
    }
}