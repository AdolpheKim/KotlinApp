package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_app.add_friend.AddFriendActivity
import com.example.kotlin_app.fragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity (), View.OnClickListener,
    TabLayoutMediator.TabConfigurationStrategy {

    private lateinit var toolbar : Toolbar
    private lateinit var viewPager2: ViewPager2
    private lateinit var tab : TabLayout

    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val currentUser : FirebaseUser? = auth.currentUser
        viewPager2 = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tab)

        val pagerAdapter = FragmentAdapter(this)
        viewPager2.adapter = pagerAdapter

        TabLayoutMediator(tab,viewPager2,this).attach()

        if(currentUser == null){
            val newIntent = Intent(this, SignInActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_friend_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val newIntent = Intent(this, AddFriendActivity::class.java)
        startActivity(newIntent)

        return super.onOptionsItemSelected(item)
    }


    override fun onClick(v: View?) {

    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = arrayListOf("Friends","Chatting","Profile")[position]
    }
}
