package com.github.antoni0sali3ri.augment

import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

/**
 * Get a drawable resource within the current context.
 *
 * @param drawableRes id of a Drawable resource
 * @param theme the theme to use
 * @return the Drawable object for the given resource id
 */
fun Context.drawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null) =
    ResourcesCompat.getDrawable(resources, drawableRes, theme)

/**
 * Get a string resource within the current context.
 *
 * @param stringRes id of a String resource
 * @return the String for the given resource id
 */
fun Context.string(@StringRes stringRes: Int) = resources.getString(stringRes)

/**
 * Format a parameterized string from a resource.
 *
 * @param template id of a String resource
 * @param args the values to insert
 * @return a formatted string
 */
fun Context.format(@StringRes template: Int, vararg args: Any): String =
    String.format(resources.getString(template), *args)


/**
 * Format a parametrized string from a resource using string resources.
 *
 * @param template id of a String resource
 * @param args resource ids of the values to insert
 * @return a formatted string
 */
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

