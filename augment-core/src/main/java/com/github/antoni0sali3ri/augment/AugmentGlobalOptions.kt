package com.github.antoni0sali3ri.augment

object AugmentGlobalOptions {
    /**
     * Control whether or not to use debug settings globally.
     *
     * <p>This variable will influence various things throughout the library, such as</p>
     * <ul>
     *     <li>Logging</li>
     * </ul>
     *
     * It is probably a easiest to set this to the value of
     * <code>your.project.package.BuildConfig.DEBUG</code>.
     */
    var isDebug: Boolean = true
}