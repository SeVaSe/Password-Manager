package com.example.passwordmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreatePasswordActivity : AppCompatActivity() {

    private lateinit var themeNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var createButton: Button
    private val passwordList = mutableListOf<Password>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        themeNameEditText = findViewById(R.id.themeNameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        createButton = findViewById(R.id.createButton)

        createButton.setOnClickListener {
            val themeName = themeNameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (themeName.isNotBlank() && password.isNotBlank()) {
                // Создаем новый пароль
                val newPassword = Password(themeName, password)

                // Добавляем его в список паролей
                passwordList.add(newPassword)

                // для отправки результата обратно в MainActivity
                finishWithResult(Activity.RESULT_OK, newPassword)



            } else {
                // Обработка ошибки, если поля пустые
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            finish() // Возвращаемся назад при нажатии FloatingActionButton
        }
    }


    private fun finishWithResult(resultCode: Int, password: Password) {
        val resultIntent = Intent()
        resultIntent.putExtra("NEW_PASSWORD", password) // Передаем новый пароль через Intent
        setResult(resultCode, resultIntent)
        finish()
    }


}
