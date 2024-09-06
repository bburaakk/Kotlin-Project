package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        supportActionBar?.hide()

        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)//animasyon
        val textView13 = findViewById<TextView>(R.id.textView26)

        textView13.setOnClickListener {
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            textView13.startAnimation(scaleAnimation)
        }


    }
}

