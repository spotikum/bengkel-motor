package id.bukanspot.bengkelmotor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.navigation.NavigationView
import id.bukanspot.bengkelmotor.adapter.ViewPagerAdapter
import id.bukanspot.bengkelmotor.databinding.ActivityHomeBinding
import id.bukanspot.bengkelmotor.databinding.ActivityMainBinding
import id.bukanspot.bengkelmotor.fragment.HomeFragment
import id.bukanspot.bengkelmotor.fragment.UserFragment

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding : ActivityHomeBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"

        val navView = binding.root.findViewById<NavigationView>(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        setupTab()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notification -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
//        return when (item.itemId) {
//            R.id.notification -> true
//            else -> super.onOptionsItemSelected(item)
//        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment = when (item.itemId) {
            R.id.nav_fragment_1 -> FirstFragment()
            R.id.nav_fragment_2 -> FirstFragment()
            else -> FirstFragment()
        }

        var fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.viewPager, fragment)
            .commit()
        item.isChecked = true
        item.title
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"History")
        adapter.addFragment(UserFragment(),"Profile")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_user)
    }
}

//    lateinit var binding : ActivityHomeBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.title = "Home"
//
//        setupTab()
//    }
//
//    private fun setupTab() {
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(HomeFragment(),"History")
//        adapter.addFragment(UserFragment(),"Profile")
//
//        binding.viewPager.adapter = adapter
//        binding.tabs.setupWithViewPager(binding.viewPager)
//
//        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
//        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_user)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//
//        // Get the notification icon view
////        val notification = menu.findItem(R.id.notification)
////        val icon = notification.icon
//
////        // Set the badge count
////        BadgeUtils.setBadgeCount(this, notification, icon, 2)
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.notification -> {
//                val intent = Intent(this, NotificationActivity::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//        return true
//    }
//}