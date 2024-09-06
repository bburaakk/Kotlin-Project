package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.CountDownTimer
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast

class SplashScreenActivity : AppCompatActivity() {

    private var backPressedTime=0L
    override fun onBackPressed() {
        if(backPressedTime+3000>System.currentTimeMillis()){
            super.onBackPressed()
        }
        backPressedTime=System.currentTimeMillis()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

           override fun onFinish() {
                val intent=Intent(this@SplashScreenActivity,SecondActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        timer.start()
        setFinishOnTouchOutside(true)

    }
}

