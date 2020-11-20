package com.github.antoni0sali3ri.augment.ui.widget.numberpicker

import android.widget.NumberPicker
import com.github.antoni0sali3ri.augment.UnstableApi

object NumberPickers {

    /**
     * Set up a set of NumberPickers as ordered.
     *
     * <p>Link the specified NumberPickers in such a way that</p>
     * <ul>
     *     <li>each NumberPicker has a range of <code>previousPicker.maxValue + 1</code> to
     *         <code>nextPicker.minValue - 1</code></li>
     *     <li>the first NumberPicker has a minimum value of minValue</li>
     *     <li>the last NumberPicker has a maximum value of maxValue</li>
     * </ul>
     *
     * <p><b>NOTE</b>: Making changes to the individual NumberPicker's minValue/maxValue properties can
     * (and most likely will) result in undefined behaviour!</b></p>
     *
     * @param minValue the minimum value for the first NumberPicker
     * @param maxValue the maximum value for the last NumberPicker
     * @param pickers the NumberPickers to link to each other
     * @throws IllegalArgumentException when the specified range of values is smaller than the number
     *                                  of NumberPickers or when any NumberPicker is passed more than once
     */
    @UnstableApi
    fun setUpAsOrdered(minValue: Int, maxValue: Int, vararg pickersWithValues: Pair<NumberPicker, Int>) {
        val pickers = pickersWithValues.map { it.first }
        val values = listOf(minValue) + pickersWithValues.map {it.second} + listOf(maxValue + 1)
        val pickerCount = pickers.size
        val rangeSize = maxValue - minValue + 1

        require(pickerCount == pickers.toSet().size) {
            "Do not pass a NumberPicker reference more than once to this function!"
        }
        require(rangeSize >= pickers.size) {
            "Defined Range [$minValue..$maxValue] is too small to fit $pickerCount NumberPickers!"
        }

        pickers.forEachIndexed { index, picker ->
            picker.minValue = values[index]
            picker.maxValue = values[index + 2] - 1
            picker.value = values[index + 1]
            picker.setOnValueChangedListener { _, _, newVal ->
                if (index > 0) {
                    pickers[index - 1].maxValue = newVal - 1
                }
                if (index < pickerCount - 1) {
                    pickers[index + 1].minValue = newVal + 1
                }
            }
        }
    }

    /**
     * Set up a set of NumberPickers as overflowing.
     *
     * <p>Link the specified NumberPickers in such a way that</p>
     *
     * <ul>
     *     <li>when pickers[i] wraps around past its maxValue (overflow), increment value of pickers[i-1] by 1</li>
     *     <li>when pickers[i] wraps around past its minValue (underflow), decrement value of pickers[i-1] by 1</li>
     * </ul>
     */
    fun setUpAsOverflowing(pickers: List<NumberPicker>) {
        val size = pickers.size
        for (i in size - 1 downTo 1) {
            pickers[i].setOnValueChangedListener { picker, oldValue, newValue ->
                if (oldValue == picker.maxValue && newValue == 0) {
                    pickers[i-1].increment()
                }
                if (newValue == picker.maxValue && oldValue == 0) {
                    pickers[i-1].decrement()
                }
            }
        }
    }

    fun setUpAsOverflowing(vararg pickers: NumberPicker) {
        setUpAsOverflowing(pickers.toList())
    }

    fun setUpAsTimePicker(minutePicker: NumberPicker, secondPicker: NumberPicker) {
        minutePicker.minValue = 0
        minutePicker.maxValue = Int.MAX_VALUE
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        setUpTimePicker(secondPicker, minutePicker)
    }

    fun setUpAsTimePicker(hourPicker: NumberPicker, minutePicker: NumberPicker, secondPicker: NumberPicker) {
        hourPicker.minValue = 0
        hourPicker.maxValue = Int.MAX_VALUE
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        setUpTimePicker(secondPicker, minutePicker, hourPicker)
    }

    fun setUpAsTimePicker(
        dayPicker: NumberPicker,
        hourPicker: NumberPicker,
        minutePicker: NumberPicker,
        secondPicker: NumberPicker
    ) {
        dayPicker.minValue = 0
        dayPicker.maxValue = Int.MAX_VALUE
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        setUpTimePicker(secondPicker, minutePicker, hourPicker, dayPicker)
    }

    fun setUpAsTimePicker(
        weekPicker: NumberPicker,
        dayPicker: NumberPicker,
        hourPicker: NumberPicker,
        minutePicker: NumberPicker,
        secondPicker: NumberPicker
    ) {
        weekPicker.minValue = 0
        weekPicker.maxValue = Int.MAX_VALUE
        dayPicker.minValue = 0
        dayPicker.maxValue = 6
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        setUpTimePicker(secondPicker, minutePicker, hourPicker, dayPicker)
    }

    private fun setUpTimePicker(
        secondPicker: NumberPicker,
        minutePicker: NumberPicker,
        hourPicker: NumberPicker? = null,
        dayPicker: NumberPicker? = null,
        weekPicker: NumberPicker? = null
    ) {
        val pickers = mutableListOf(minutePicker, secondPicker)
        if (hourPicker != null) pickers.add(0, hourPicker)
        if (dayPicker != null) pickers.add(0, dayPicker)
        if (weekPicker != null) pickers.add(0, weekPicker)
        setUpAsOverflowing(pickers)
    }

}
