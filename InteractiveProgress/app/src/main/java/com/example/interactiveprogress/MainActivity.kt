package com.example.interactiveprogress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initialTextViewTranslationY = textViewProgress.translationY

        // Add onchange listener that updates text based on progress value
        seekBar3.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                // Text displays value proportional to progress
                textViewProgress.text = progress.toString()

                // Get animationStep dimensions from resources folder
                // animationStep is resolution-independent
                val animationStep = resources.getDimension(R.dimen.text_anim_step)

                val translationDistance = initialTextViewTranslationY + progress * animationStep * -1

                // Animate the textview based on updated distance
                textViewProgress.animate().translationY(translationDistance)
            }

            // No need to implement these
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        // Pass in a lambda fn
        buttonReset.setOnClickListener { v ->
            seekBar3.progress = 0

            // Move text back to original position and rotate it on the way
            textViewProgress.animate().setDuration(500).rotationBy(360f).
                translationY(initialTextViewTranslationY)

        }


    }
}