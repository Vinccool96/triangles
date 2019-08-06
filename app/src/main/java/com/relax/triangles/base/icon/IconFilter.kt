package com.relax.triangles.base.icon

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build.*
import android.os.Build.VERSION.*
import androidx.core.content.ContextCompat

class IconFilter(context: Context, drawable: Drawable, color: Int) {

    init {
        drawable.setColorFilter(ContextCompat.getColor(context, color))
    }

    fun Drawable.setColorFilter(color: Int) {
        if (SDK_INT < VERSION_CODES.Q)
        this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        else this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    }
}