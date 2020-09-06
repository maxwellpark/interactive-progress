package com.example.interactiveprogress

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initialTextViewTranslationY = progress_text.translationY

        // Add onchange listener that updates text based on progress value
        progress_bar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                // Text displays value proportional to progress
                progress_text.text = progress.toString()

                // Get animationStep dimensions from resources folder
                // animationStep is resolution-independent
                val animationStep = resources.getDimension(R.dimen.text_anim_step)

                val translationDistance = initialTextViewTranslationY + progress * animationStep * -1

                // Animate the header_text based on updated distance
                header_text.animate().translationY(translationDistance)
            }

            // No need to implement these
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        task_input.setOnFocusChangeListener { view, b -> println("OnFocusChange") }

        // Pass in a lambda fn
        reset_button.setOnClickListener { v ->
            progress_bar.progress = 0

            // Move text back to original position and rotate it on the way
            progress_text.animate().setDuration(500).rotationBy(360f).
                translationY(initialTextViewTranslationY)
        }


    }
}