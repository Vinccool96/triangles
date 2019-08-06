package com.relax.triangles

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.gridlayout.widget.GridLayout
import com.relax.triangles.models.Triangle
import com.relax.triangles.models.TriangleView
import com.relax.triangles.utils.DisplaySizeConversion

/**
 * A simple [Fragment] subclass.
 * Use the [TrianglesGridFragment] factory method to
 * create an instance of this fragment.
 *
 */
class TrianglesGridFragment(private val activity: MainActivity,
                            private val windowManager: WindowManager) : Fragment() {

    private lateinit var triangleGrid: GridLayout
    private val triangleViews = ArrayList<TriangleView>()
    private lateinit var inflater: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        return inflater.inflate(R.layout.fragment_triangles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triangleGrid = view.findViewById(R.id.triangles_frame)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            set()
        }, 500)
    }

    private fun set() {
        reset()
        setMargin()
        resize()
        addTrianglesViews()
        setColors()
    }

    private fun reset() {
        triangleGrid.removeAllViewsInLayout()
        triangleViews.clear()
    }

    private fun setMargin() {
        val params = triangleGrid.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = getNavigationBarHeight()
        triangleGrid.layoutParams = params
    }

    private fun resize() {
        val width = DisplaySizeConversion.toDP(windowManager, triangleGrid.width)
        val height = DisplaySizeConversion.toDP(windowManager, triangleGrid.height)
        val size = 52
        val columnCount = width / size
        val rowCount = height / size
        triangleGrid.columnCount = columnCount
        triangleGrid.rowCount = rowCount
        Log.d(TAG, "columnCount: $columnCount, rowCount: $rowCount")
    }

    private fun getNavigationBarHeight(): Int {
        val name = "navigation_bar_height"
        val defType = "dimen"
        val defPackage = "android"
        val resources = context!!.resources
        val resourceId = resources.getIdentifier(name, defType, defPackage)
        return resources.getDimensionPixelSize(resourceId)
    }

    @SuppressLint("InflateParams")
    private fun addTrianglesViews() {
        val columnCount = triangleGrid.columnCount
        val rowCount = triangleGrid.rowCount
        for (i in 1..columnCount) {
            for (j in 1..rowCount) {
                val root = null
                val imageView = inflater.inflate(R.layout.triangle_layout, root) as ImageView
                triangleGrid.addView(imageView)
                val triangleView = TriangleView(Triangle(), imageView)
                triangleViews.add(triangleView)
            }
        }
    }

    private fun setColors() {
        for (triangleView in triangleViews){
            val imageView = triangleView.view
            imageView.setColorFilter(activity.triangleColor.toArgb())
        }
    }

    companion object {
        private val TAG = TrianglesGridFragment::class.java.simpleName
    }
}
