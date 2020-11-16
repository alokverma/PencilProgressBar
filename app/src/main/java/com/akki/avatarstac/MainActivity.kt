package com.akki.avatarstac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akki.pencilloader.PencilLoader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val PencilLoad = findViewById<PencilLoader>(R.id.pencil)
        PencilLoad.showProgress()

    }
}