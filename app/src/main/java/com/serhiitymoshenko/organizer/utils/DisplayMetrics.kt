package com.serhiitymoshenko.organizer.utils

import android.content.Context

fun getScreenWidthDp(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    val density = displayMetrics.density
    val screenWidthPx = displayMetrics.widthPixels
    val screenWidthDp = screenWidthPx.toDp(density)

    return screenWidthDp
}