package com.relax.triangles.utils

import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.ceil


object DisplaySizeConversion {

    fun toDP(windowManager: WindowManager, px: Int): Int {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val logicalDensity = metrics.density
        return ceil(px / logicalDensity).toInt()
    }

}