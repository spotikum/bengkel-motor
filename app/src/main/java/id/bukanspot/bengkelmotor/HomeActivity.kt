package id.bukanspot.bengkelmotor

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.badge.BadgeUtils
import id.bukanspot.bengkelmotor.adapter.ViewPagerAdapter
import id.bukanspot.bengkelmotor.databinding.ActivityHomeBinding
import id.bukanspot.bengkelmotor.databinding.ActivityMainBinding
import id.bukanspot.bengkelmotor.fragment.HomeFragment
import id.bukanspot.bengkelmotor.fragment.UserFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"

        setupTab()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        // Get the notification icon view
//        val notification = menu.findItem(R.id.notification)
//        val icon = notification.icon

//        // Set the badge count
//        BadgeUtils.setBadgeCount(this, notification, icon, 2)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notification -> Toast.makeText(this, "Notification", Toast.LENGTH_LONG).show()
        }
        return true
    }
}