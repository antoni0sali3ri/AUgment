package com.github.antoni0sali3ri.augment

import android.util.Log

/**
 * Make sure a string is short enough to be a log tag (<=20 chars).
 */
fun String.logTag(): String = take(20)

/**
 * Send a debug message using the android logging utility.
 *
 * The call to the logger will only be executed if <code>AugmentGlobalOptions</code> is set to true.
 *
 * @param tag the log tag
 * @param msg the log message
 * @see AugmentGlobalOptions.isDebug
 * @see android.util.Log
 */
fun Any.debug(tag: String, msg: String) {
    if (AugmentGlobalOptions.isDebug)
        Log.d(tag.logTag(), msg)
}

/**
 * Send a debug log message using the class name as a log tag.
 */
fun Any.debug(msg: String) {
    debug(this::class.java.simpleName, msg)
}

/**
 * Send an error log message.
 *
 * @param tag the log tag
 * @param msg the log message
 */
fun Any.logError(tag: String, msg: String) {
    Log.e(tag.logTag(), msg)
}

/**
 * Send an error log message using the class name as a log tag.
 */
fun Any.logError(msg: String) {
    logError(this::class.java.simpleName, msg)
}

/**
 * Send an error log message using an exception.
 *
 * @param tag the log tag
 * @param exn the exception to log
 */
fun Any.logError(tag: String, exn: Exception) {
    logError(tag, "${exn::class.java.simpleName}: ${exn.message}")
}

/**
 * Send an error log message from an exception using the class name as a log tag.
 */
fun Any.logError(exn: Exception) {
    logError(this::class.java.simpleName, exn)
}