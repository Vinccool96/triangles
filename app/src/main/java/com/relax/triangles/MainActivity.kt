package com.relax.triangles

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.relax.triangles.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var trianglesFragment: TrianglesGridFragment
    lateinit var settingsFragment: SettingsFragment
    lateinit var triangleColor: Color
    lateinit var backgroundColor: Color
    lateinit var optionsMenu: Menu

    @Suppress("PLUGIN_WARNING")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setColorsAtStart()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        trianglesFragment = TrianglesGridFragment(this, windowManager)
        settingsFragment = SettingsFragment(this)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_layout, trianglesFragment)
        ft.replace(R.id.settings_layout, settingsFragment)
        ft.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            menuInflater.inflate(R.menu.menu_main, menu)
            optionsMenu = menu
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "menu item clicked")
        return when (item.itemId) {
            R.id.menu -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                item.isVisible = false
                true
            }
            android.R.id.home -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setColorsAtStart() {
        val trColStr = getPrefStr(R.string.settings_file_triangles_color) ?: "#FF8F00FF"
        val trColLong = trColStr.toLong(16)
        triangleColor = Color.valueOf(trColLong)
    }

    private fun getPrefStr(id: Int): String? {
        val sharedPref = getSharedPreferences(getString(R.string.settings_file),
                Context.MODE_PRIVATE)
        return sharedPref.getString(getString(id), null)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
