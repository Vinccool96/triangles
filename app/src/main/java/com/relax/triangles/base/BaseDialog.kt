package com.relax.triangles.base

import android.R.attr
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.relax.triangles.R
import com.relax.triangles.base.icon.IconFilter

open class BaseDialog(val context: Context, val color: Int) : DialogInterface.OnShowListener {
    var alert: AlertDialog? = null
    val builder: AlertDialog.Builder
    open val okay: String = "Ok"
    private var buttons: List<Button?>? = null

    init {
        val style = when (color) {
            else -> R.style.AlertDialogMain
        }
        builder = context.let { AlertDialog.Builder(it, style) }
    }

    override fun onShow(p0: DialogInterface?) {
        val no = alert?.getButton(DialogInterface.BUTTON_NEGATIVE)
        val neutral = alert?.getButton(DialogInterface.BUTTON_NEUTRAL)
        val yes = alert?.getButton(DialogInterface.BUTTON_POSITIVE)
        arrayListOf(no, neutral, yes).let {
            var groupWidth = 0
            for (b in it) groupWidth += b?.width ?: 0
            if (alert?.window?.decorView?.width ?: 0 < groupWidth) it[NEGATIVE]?.text = ""
            groupWidth = it[0]?.width ?: 0 + (it[1]?.width ?: 0 )+ (it[2]?.width ?: 0)
            if (alert?.window?.decorView?.width ?: 0 < groupWidth) it[POSITIVE]?.text = ""
        }
    }

    open fun exit() {
        alert?.dismiss()
        alert?.cancel()
    }

    fun progress(): ProgressBar {
        return ProgressBar(context,null, attr.progressBarStyleHorizontal).apply {
            isIndeterminate = true
            max = 100
        }
    }

    fun simple(title: String, message: String) {
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(okay) { _, _ -> /**/ }
        show(negative = false, onCancel = false, positive = true)
    }

    protected fun close(seconds: Int) {
        Handler().postDelayed({ exit() }, seconds * 1000L) // Close dialog after x seconds
    }

    protected fun inflate(layout: Int, viewGroup: ViewGroup): View {
        return LayoutInflater.from(context).inflate(layout, viewGroup, false)
    }

    protected fun large(layout: Int, group: ViewGroup): View {
        val view = inflate(layout, group)
        // negative button will add up to height, so 80% is like 90%
        view.minimumHeight = (group.measuredHeight * 0.8f).toInt()
        return view
    }

    protected fun show(negative: Boolean, onCancel: Boolean, positive: Boolean) {
        builder.apply {
            if (negative) {
                val tint = R.color.triadicComplementaryColor
                setNegativeButtonIcon(icon(R.drawable.ic_close, tint))
            }
            if (onCancel) setNegativeButton("Cancel") { _, _ -> exit() }
            if (positive) {
                val main = R.color.mainPrimaryPageColor
                val tint = if (color == PURE) R.color.triadicPrimaryPageColor else main
                setPositiveButtonIcon(icon(R.drawable.ic_check, tint)) // OK button clicked }
            }
        }
        alert = builder.create() // if !((Activity)context).isDestroyed())
        alert?.setOnShowListener(this)
        alert?.show() // if !leaked : ((Activity)context).getWindow().getDecorView() != null
    }

    protected fun show(negative: String) {
        builder.setNegativeButton(negative) { _, _ -> exit() }
        alert = builder.create()
        builder.show()
    }

    protected fun str(resource: Int): String {
        return context.resources.getString(resource)
    }

    private fun icon(resource: Int, tint: Int): Drawable? {
        return ContextCompat.getDrawable(context, resource)?.apply {
            IconFilter(context, this, tint)
        }
    }

    companion object {
        //private val TAG: String = BaseDialog::class.java.simpleName
        const val MAIN = 0
        const val PURE = 1
        const val WRAP = 2
        private const val NEGATIVE = 0
        //private const val NEUTRAL = 1
        private const val POSITIVE = 2
    }
}