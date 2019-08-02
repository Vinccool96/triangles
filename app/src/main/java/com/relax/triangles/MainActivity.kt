package com.relax.triangles

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), TrianglesGridFragment.OnFragmentInteractionListener {

    lateinit var trianglesFragment: TrianglesGridFragment

    @Suppress("PLUGIN_WARNING")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trianglesFragment = TrianglesGridFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_layout, trianglesFragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
