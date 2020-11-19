package com.github.antoni0sali3ri.augment.ui.widget.numberpicker

import android.widget.NumberPicker
import com.github.antoni0sali3ri.auc.Transform

typealias IntTransform = Transform<Int, Any>

class NumberPickerExt(private val picker: NumberPicker) {

    /**
     * Delegate for [NumberPicker].value.
     *
     * @see NumberPicker.getValue
     * @see NumberPicker.setValue
     */
    var value: Int
        get() = picker.value
        set(value) {
            picker.value = value
        }

    /**
     * Delegate for [NumberPicker].displayedValue (extension property).
     */
    var displayedValue: String
        get() = picker.displayedValue
        set(value) {
            picker.displayedValue = value
        }

    /**
     * Delegate for [NumberPicker].minValue.
     *
     * Automatically updates the displayed values according to [valueTransform].
     *
     * @see NumberPicker.getMinValue
     * @see NumberPicker.setMinValue
     * @see NumberPicker.setDisplayedValues
     */
    var minValue: Int
        get() = picker.minValue
        set(value) {
            picker.updateValues(min = value, valueTransform = valueTransform)
        }

    /**
     * Delegate for [NumberPicker].maxValue.
     *
     * Automatically updates the displayed values according to [valueTransform].
     *
     * @see NumberPicker.getMinValue
     * @see NumberPicker.setMinValue
     * @see NumberPicker.setDisplayedValues
     */
    var maxValue: Int
        get() = picker.maxValue
        set(value) {
            picker.updateValues(max = value, valueTransform = valueTransform)
        }

    /**
     * Function that defines how the [NumberPicker]'s displayedValues are generated.
     *
     * @see NumberPicker.setDisplayedValues
     */
    var valueTransform: IntTransform = identity
        set(value) {
            field = value
            picker.updateValues(valueTransform = value)
        }

    companion object {
        val identity: IntTransform = { it }
    }
}

/**
 * The current value as it is displayed.
 */
var NumberPicker.displayedValue: String
    get() = displayedValues[value]
    set(value) {
        val v = displayedValues.indexOf(value)
        require (v >= 0) {
            "$value must be one of displayedValues!"
        }
        this.value = v
    }

/**
 * Set up this [NumberPicker] to display the values of an enum class.
 */
inline fun <reified T: Enum<T>> NumberPicker.setUpWithEnum(valueTransform: (T) -> Any = { it }) {
    val values = enumValues<T>()
    minValue = 0
    maxValue = values.size - 1
    displayedValues = values.map { valueTransform(it).toString() }.toTypedArray()
}

/**
 * Increment the current value by [delta].
 */
fun NumberPicker.increment(delta: Int = 1) {
    require(delta > 0)
    value += delta
}

/**
 * Decrement the current value by [delta].
 */
fun NumberPicker.decrement(delta: Int = 1) {
    require(delta > 0)
    value -= delta
}

/**
 * Update the values of this NumberPicker.
 *
 * @param min the minimum value. Defaults to [NumberPicker.getMinValue].
 * @param max the maximum value. Defaults to [NumberPicker.getMaxValue].
 * @param valueTransform the function to generate the displayed values. Defaults to the identity function.
 */
fun NumberPicker.updateValues(min: Int = minValue, max: Int = maxValue, valueTransform: IntTransform = {it}) {
    displayedValues = null
    minValue = min
    maxValue = max
    displayedValues = (min..max).map { valueTransform(it).toString() }.toTypedArray()
}
