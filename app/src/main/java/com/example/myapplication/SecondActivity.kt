package com.example.myapplication

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.Toast
import java.io.*
import java.util.*

class SecondActivity : AppCompatActivity() {
    private var backPressedTime=0L
    override fun onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(applicationContext,"Çıkmak için tekrar geri tuşuna basınız!", Toast.LENGTH_SHORT).show()
        }
        backPressedTime=System.currentTimeMillis()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()

        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)//animasyon
        val basla: Button = findViewById(R.id.basla)
        val settings: Button = findViewById(R.id.settings)

        settings.setOnClickListener {
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    val intent = Intent(this@SecondActivity, SettingsActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            settings.startAnimation(scaleAnimation)

        }


        basla.setOnClickListener {
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    val intent = Intent(this@SecondActivity, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            basla.startAnimation(scaleAnimation) }



        val nasiloynanir: Button = findViewById(R.id.nasiloynanir)

        nasiloynanir.setOnClickListener {
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            nasiloynanir.startAnimation(scaleAnimation) }

        val cikis: Button = findViewById(R.id.cikis)
        cikis.setOnClickListener {
            cikis.startAnimation(scaleAnimation)
            finishAffinity() // uygulamayı kapat }

        }

    }
}

