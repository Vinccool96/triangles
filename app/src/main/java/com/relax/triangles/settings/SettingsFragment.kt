package com.relax.triangles.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.relax.triangles.MainActivity
import com.relax.triangles.R

class SettingsFragment(private val activity: MainActivity) : Fragment() {

    lateinit var trianglesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trianglesButton = view.findViewById(R.id.triangles_button)
        setTrianglesButtonColor()
    }

    fun setTrianglesButtonColor() {
        trianglesButton.setBackgroundColor(activity.triangleColor.toArgb())
    }

    companion object {
        private val TAG = SettingsFragment::class.java.simpleName
    }
}
