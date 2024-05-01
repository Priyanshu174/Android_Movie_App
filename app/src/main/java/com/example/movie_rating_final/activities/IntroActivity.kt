package com.example.movie_rating_final.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movie_rating_final.R
class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val getinBtn: Button = findViewById(R.id.getInBtn)
        getinBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
        }
    }
}