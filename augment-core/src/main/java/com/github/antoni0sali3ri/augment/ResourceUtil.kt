package com.github.antoni0sali3ri.augment

import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

fun Context.drawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null) =
    ResourcesCompat.getDrawable(resources, drawableRes, theme)

fun Context.string(@StringRes stringRes: Int) = resources.getString(stringRes)

fun Context.format(@StringRes template: Int, vararg args: String): String =
    String.format(resources.getString(template), *args)


fun Context.format(@StringRes template: Int, @StringRes vararg args: Int): String {
    val argStrings = args.map { string(it) }.toTypedArray()
    return format(template, *argStrings)
}

fun Fragment.drawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null) =
    requireContext().drawable(drawableRes, theme)

fun Fragment.string(@StringRes stringRes: Int) = requireContext().string(stringRes)

fun Fragment.format(@StringRes template: Int, vararg args: String) =
    requireContext().format(template, *args)

fun Fragment.format(@StringRes template: Int, @StringRes vararg args: Int) =
    requireContext().format(template, *args)

