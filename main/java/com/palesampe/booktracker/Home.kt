package com.palesampe.booktracker

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerLayout)
        var navView: NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val signOut = AlertDialog.Builder(this)
        signOut.setTitle("Are you sure?")
        signOut.setMessage("Are you sure you would like to Log Out")
        signOut.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        signOut.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int -> })
        replaceFragment(HomeFragment(),"HOME")
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                it.isChecked = true
                when (it.itemId) {
                    R.id.nav_home -> //startActivity(Intent(this, testing::class.java))
                        replaceFragment(HomeFragment(), "HOME")
                    R.id.nav_favourite -> Toast.makeText(
                        applicationContext,
                        "Clicked Favourite",
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.nav_share -> Toast.makeText(
                        applicationContext,
                        "Clicked Share",
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.nav_setting -> replaceFragment(SettingsFragment(), "SETTINGS")
                    R.id.nav_sign_out -> signOut.show()
                }
                true
            }
        }

    private fun replaceFragment(fragment: Fragment, title: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransfer = fragmentManager.beginTransaction()
        fragmentTransfer.replace(R.id.frameLayout, fragment)
        fragmentTransfer.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}