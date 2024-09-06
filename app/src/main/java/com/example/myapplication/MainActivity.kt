package com.example.myapplication

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onBackPressed() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
    // deneme
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)


        val letter1 = findViewById<TextView>(R.id.letter1)
        val letter2 = findViewById<TextView>(R.id.letter2)
        val letter3 = findViewById<TextView>(R.id.letter3)
        val letter4 = findViewById<TextView>(R.id.letter4)
        val letter5 = findViewById<TextView>(R.id.letter5)
        val letter6 = findViewById<TextView>(R.id.letter6)
        val letter7 = findViewById<TextView>(R.id.letter7)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val t = findViewById<TextView>(R.id.t)

        val button16 = findViewById<ImageView>(R.id.button16)
        val button22 = findViewById<ImageView>(R.id.button22)
        val button24 = findViewById<ImageView>(R.id.button24)
        val button19 = findViewById<ImageView>(R.id.button19)
        val button20 = findViewById<ImageView>(R.id.button20)
        val button21 = findViewById<ImageView>(R.id.button21)
        val button23 = findViewById<ImageView>(R.id.button23)
        val button2: Button = findViewById(R.id.button2)


        fun replaceTurkishIWithCapitalI(word: String): String {
            val builder = StringBuilder(word)
            for (i in builder.indices) {
                if (builder[i] == 'i') {
                    builder[i] = 'İ'
                } else if (builder[i] == 'ı') {
                    builder[i] = 'I'
                }
            }
            return builder.toString()
        }

        button2.setOnClickListener {
            //Metin dosyasından kelimeleri alıp 4 harfli ve en fazla tekrar eden harfleri belirleme

            val kelimeListesi = mutableListOf<String>()
            val maxTekrarEdenHarfListesi = mutableListOf<Char>()
            val geriyeKalanHarfListesi = mutableListOf<Char>()

            val dosyaSatir = assets.open("deneme.txt").bufferedReader().readLines()


            for (satir in dosyaSatir) {
                val kelime = satir.trim() //trim() baştaki ve sondaki tüm boşlukları kaldırır.
                if (kelime.length >= 4) {
                    kelimeListesi.add(kelime)

                    // Tekrar eden harf sayısını hesapla

                    val tekrarEdenHarf = mutableMapOf<Char, Int>()
                    for (harf in kelime) {
                        val count = tekrarEdenHarf.getOrDefault(harf, 0)
                        tekrarEdenHarf[harf] = count + 1
                    }

                    // En fazla tekrar eden harfleri bul

                    val maxSayi = tekrarEdenHarf.values.maxOrNull()
                    if (maxSayi != null) {
                        val maxTekrarEdenHarfler = tekrarEdenHarf.filterValues { it == maxSayi }.keys
                        maxTekrarEdenHarfListesi.addAll(maxTekrarEdenHarfler)
                    }
                }
            }

            // Geriye kalan harfleri bulma

            for (kelime in kelimeListesi) {
                for (harf in kelime) {
                    if (!maxTekrarEdenHarfListesi.contains(harf)) {
                        geriyeKalanHarfListesi.add(harf)
                    }
                }
            }

            //Geriye kalan harfler içerisinden rastgele altı harfi altı TextView'a rastgele yerleştirme

            val rastgeleGeriyeKalanHarfler = geriyeKalanHarfListesi.shuffled().take(6)
            val textViewIdleri = listOf(R.id.letter1, R.id.letter2, R.id.letter7, R.id.letter4, R.id.letter5, R.id.letter6)
            for (i in 0 until minOf(rastgeleGeriyeKalanHarfler.size, textViewIdleri.size)) {
                val digertextview = findViewById<TextView>(textViewIdleri[i])
                digertextview.text = rastgeleGeriyeKalanHarfler[i].toString()
            }

            //En fazla tekrar eden harfi rastgele bir TextView'e yazdırma

            val rastgeleMaxTekrarEdenHarfler = maxTekrarEdenHarfListesi.random()
            letter3.text = rastgeleMaxTekrarEdenHarfler.toString()

        }
            fun applyAnimation(view: View) {
                val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                    view,
                    PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.8f)
                )
                scaleDown.duration = 150
                scaleDown.start()
            }

            val onTouchListener = View.OnTouchListener { view, motionEvent ->
                val x = motionEvent.rawX.toInt()
                val y = motionEvent.rawY.toInt()
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(view)
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            view,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }

                true
            }//animasyon kısmı



            letter6.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button16)
                        textView10.append("${letter6.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button16,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter4.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button19)
                        textView10.append("${letter4.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button19,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter3.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button24)
                        textView10.append("${letter3.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button24,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter5.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button22)
                        textView10.append("${letter5.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button22,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter2.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button23)
                        textView10.append("${letter2.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button23,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter1.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button21)
                        textView10.append("${letter1.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button21,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            letter7.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        applyAnimation(button20)
                        textView10.append("${letter7.text}")
                    }

                    MotionEvent.ACTION_UP -> {
                        val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                            button20,
                            PropertyValuesHolder.ofFloat("scaleX", 1.0f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.0f)
                        )
                        scaleUp.duration = 150
                        scaleUp.start()
                    }
                }
                true
            }

            // ImageView'a onTouchListener ekleme
            button16.setOnTouchListener(onTouchListener)
            button19.setOnTouchListener(onTouchListener)
            button20.setOnTouchListener(onTouchListener)
            button21.setOnTouchListener(onTouchListener)
            button22.setOnTouchListener(onTouchListener)
            button23.setOnTouchListener(onTouchListener)
            button24.setOnTouchListener(onTouchListener)


            val textView26 = findViewById<TextView>(R.id.textView26)
            textView26.setOnClickListener {
                scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        finish()
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })

                textView26.startAnimation(scaleAnimation)
            }

        }
    }


