package com.example.singsangsung

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    //private lateinit var prefs : PlaylistPreferenceManager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply splash screen
        installSplashScreen()

        setContentView(R.layout.tab_layout_main)
        //clearSharedPreferences(this)

        // 초기화
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Songs"
                1 -> "Playlist"
                2 -> "Export"
                else -> "Default Tab"
            }
        }.attach()
    }
//    fun clearSharedPreferences(context: Context) {
//        val prefs = context.getSharedPreferences("playlist_prefs", Context.MODE_PRIVATE)
//        prefs.edit().clear().apply()
//    }
        fun clearSharedPreferences(context: Context) {
        val prefs = context.getSharedPreferences("song_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    fun switchToTab(tabIndex: Int) {
        if (::viewPager.isInitialized && tabIndex in 0 until adapter.itemCount) {
            viewPager.currentItem = tabIndex
        }
    }
}