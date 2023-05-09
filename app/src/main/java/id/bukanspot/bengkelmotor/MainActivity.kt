package id.bukanspot.bengkelmotor

import androidx.appcompat.app.AppCompatActivity
import id.bukanspot.bengkelmotor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupTab()
//    }
//
//    private fun setupTab() {
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(HomeFragment(),"Home")
//        adapter.addFragment(UserFragment(),"Profile")
//
//        binding.viewPager.adapter = adapter
//        binding.tabs.setupWithViewPager(binding.viewPager)
//
//        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
//        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_user)
//    }
}