package id.bukanspot.bengkelmotor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import id.bukanspot.bengkelmotor.adapter.ViewPagerAdapter
import id.bukanspot.bengkelmotor.databinding.ActivityHomeBinding
import id.bukanspot.bengkelmotor.databinding.NavHeaderBinding
import id.bukanspot.bengkelmotor.fragment.FirstFragment
import id.bukanspot.bengkelmotor.fragment.HistoryFragment
import id.bukanspot.bengkelmotor.fragment.HomeFragment

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding : ActivityHomeBinding
    lateinit var auth : FirebaseAuth

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

        lateinit var binding: NavHeaderBinding

        val headerView = navView.getHeaderView(0)
        binding = NavHeaderBinding.bind(headerView)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        //kondisi user sedang login atau tidak
        if (user != null){
            binding.email.text = user.email
        }
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
        var fragment: Any = when (item.itemId) {
            R.id.nav_fragment_1 -> {
                btnLogout()
            }
            else -> FirstFragment()
        }

        var fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
//            .replace(R.id.viewPager, fragment as Fragment)
            .commit()
        item.isChecked = true
        item.title
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Dashboard")
        adapter.addFragment(HistoryFragment(),"History")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_history)
    }

    private fun btnLogout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
