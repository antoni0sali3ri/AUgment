package com.github.antoni0sali3ri.augment

import android.util.Log

fun String.logTag(): String = take(20)

fun Any.debug(tag: String, msg: String) {
    if (AugmentGlobalOptions.isDebug)
        Log.d(tag.logTag(), msg)
}

fun Any.debug(msg: String) {
    debug(this::class.java.simpleName, msg)
}

fun Any.logError(tag: String, msg: String) {
    Log.e(tag.logTag(), msg)
}

fun Any.logError(msg: String) {
    logError(this::class.java.simpleName, msg)
}

fun Any.logError(tag: String, exn: Exception) {
    logError(tag, "${exn::class.java.simpleName}: ${exn.message}")
}

fun Any.logError(exn: Exception) {
    logError(this::class.java.simpleName, exn)
}