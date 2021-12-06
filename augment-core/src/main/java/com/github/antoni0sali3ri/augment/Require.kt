package com.github.antoni0sali3ri.augment

/**
 * Require that the elements of a collection are distinct.
 */
fun <T> requireDistinct(arg: String, collection: Collection<T>?): Collection<T> {
    val size = requireNotNull(collection).size
    requireArg(
        arg,
        size == collection.toSet().size,
        {"The collection must not contain duplicate values!"},
        { DuplicateElementException(it) }
    )
    return collection
}

fun <T> requireDistinct(collection: Collection<T>?) = requireDistinct("", collection)

fun <T> requireMinSize(arg: String, collection: Collection<T>?, size: Int): Collection<T> {
    requireArg(
        arg,
        requireNotNull(collection).size >= size,
        {"The collection needs to have at least $size elements, found ${collection.size}!"},
        { NotEnoughElementsException(it) }
    )
    return collection
}

fun requireMinSize(collection: Collection<*>, size: Int) = requireMinSize("", collection, size)

fun requireMatch(arg: String, value: String?, regex: String): String {
    requireArg(
        arg,
        requireNotNull(value).matches(regex.toRegex()),
        {"String '$value' does not match regular expression '$regex'"},
        { PatternMismatchException(it) }
    )
    return value
}

fun requireMatch(value: String, regex: String) = requireMatch("", value, regex)

fun <T: Comparable<T>> requireInRange(arg: String, value: T?, range: ClosedRange<T>): T {
    requireArg(
        arg,
        requireNotNull(value) in range,
        {"$value is not in range [${range.start},${range.endInclusive}]."},
        { OutOfRangeException(it) }
    )
    return value
}

fun <T: Comparable<T>> requireInRange(value: T, range: ClosedRange<T>) = requireInRange("", value, range)

internal fun requireInternal(requirement: Boolean, msg: () -> String, exn: (String) -> Throwable) {
    if (!requirement) {
        throw exn(msg())
    }
}

internal fun requireArg(
    arg: String,
    requirement: Boolean,
    msg: () -> String,
    exn: (String) -> Throwable = { RequirementFailedException(it) }
) = requireInternal(
    requirement,
    {
        val param = if (arg.isNotBlank()) " for argument $arg" else ""
        "Requirement failed${param}! ${msg()}"
    },
    exn
)
