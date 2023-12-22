package com.example.passwordmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_app) // Убедитесь, что имя разметки совпадает с вашим info_app.xml

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            finish() // Возвращаемся назад при нажатии FloatingActionButton
        }

    }
}
