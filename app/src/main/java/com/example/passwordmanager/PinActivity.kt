package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PinActivity : AppCompatActivity() {
    private val correctPin = "1234" // Здесь ваш корректный пин-код

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val pinEditText: EditText = findViewById(R.id.pinEditText)
        val enterButton: Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val enteredPin = pinEditText.text.toString()

            if (enteredPin == correctPin) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Закрыть PinActivity после успешного ввода пин-кода
            } else {
                Toast.makeText(this, "Неверный пин-код. Попробуйте снова.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
