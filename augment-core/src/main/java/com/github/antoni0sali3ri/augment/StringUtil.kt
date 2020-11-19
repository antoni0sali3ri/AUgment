package com.github.antoni0sali3ri.augment

import android.content.Context
import androidx.annotation.StringRes

fun Context.format(@StringRes template: Int, vararg args: String): String {
    return String.format(resources.getString(template), *args)
}

fun Context.format(@StringRes template: Int, @StringRes vararg args: Int): String {
    val argStrings = args.map { resources.getString(it) }.toTypedArray()
    return format(template, *argStrings)
}

