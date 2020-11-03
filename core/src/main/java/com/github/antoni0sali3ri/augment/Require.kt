package com.github.antoni0sali3ri.augment

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi

fun requireArg(arg: String, requirement: Boolean, msg: () -> String) = require(requirement) {
    val param = if (arg.isNotBlank()) " for argument $arg" else ""
    "Requirement failed${param}! ${msg()}"
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun <T: Comparable<T>> requireInRange(arg: String, value: T, range: Range<T>) = requireArg(arg, value in range) {
    "$value is not in range [${range.lower},${range.upper}]."
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun <T: Comparable<T>> requireInRange(value: T, range: Range<T>) = requireInRange("", value, range)
