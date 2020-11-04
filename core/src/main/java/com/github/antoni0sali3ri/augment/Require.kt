package com.github.antoni0sali3ri.augment

fun requireArg(arg: String, requirement: Boolean, msg: () -> String) = require(requirement) {
    val param = if (arg.isNotBlank()) " for argument $arg" else ""
    "Requirement failed${param}! ${msg()}"
}

fun <T: Comparable<T>> requireInRange(arg: String, value: T, range: ClosedRange<T>) = requireArg(arg, value in range) {
    "$value is not in range [${range.start},${range.endInclusive}]."
}

fun <T: Comparable<T>> requireInRange(value: T, range: ClosedRange<T>) = requireInRange("", value, range)
