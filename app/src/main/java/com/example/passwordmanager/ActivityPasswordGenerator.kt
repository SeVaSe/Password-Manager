package com.example.passwordmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class PasswordGeneratorActivity : AppCompatActivity() {

    // Символы для генерации пароля
    private val capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val smallLetters = "abcdefghijklmnopqrstuvwxyz"
    private val numbers = "0123456789"
    private val specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?/~"

    // Views
    private lateinit var capitalLettersCheckBox: CheckBox
    private lateinit var smallLettersCheckBox: CheckBox
    private lateinit var numbersCheckBox: CheckBox
    private lateinit var specialCharsCheckBox: CheckBox
    private lateinit var generateButton: Button
    private lateinit var passwordTextViews: List<TextView>

    // Массив, содержащий длины паролей
    private val passwordLengths = arrayOf(7, 10, 15, 20, 25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_password)

        capitalLettersCheckBox = findViewById(R.id.capitalLettersCheckBox)
        smallLettersCheckBox = findViewById(R.id.smallLettersCheckBox)
        numbersCheckBox = findViewById(R.id.numbersCheckBox)
        specialCharsCheckBox = findViewById(R.id.specialCharsCheckBox)
        generateButton = findViewById(R.id.generateButton)

        passwordTextViews = listOf(
            findViewById(R.id.password1TextView),
            findViewById(R.id.password2TextView),
            findViewById(R.id.password3TextView),
            findViewById(R.id.password4TextView),
            findViewById(R.id.password5TextView)
        )

        generateButton.setOnClickListener {
            generatePasswords()
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            finish() // Возвращаемся назад при нажатии FloatingActionButton
        }


        // Устанавливаем обработчик нажатия для каждого TextView
        passwordTextViews.forEach { textView ->
            textView.setOnClickListener {
                val text = (it as TextView).text.toString()
                val password = text.substringAfterLast(": ").trim() // Извлекаем пароль из строки
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("password", password)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Пароль скопирован", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generatePasswords() {
        val selectedOptions = mutableListOf<String>()

        if (capitalLettersCheckBox.isChecked) selectedOptions.add(capitalLetters)
        if (smallLettersCheckBox.isChecked) selectedOptions.add(smallLetters)
        if (numbersCheckBox.isChecked) selectedOptions.add(numbers)
        if (specialCharsCheckBox.isChecked) selectedOptions.add(specialChars)

        if (selectedOptions.isEmpty()) return

        for ((index, textView) in passwordTextViews.withIndex()) {
            val passwordLength = passwordLengths[index]

            val password = buildString {
                repeat(passwordLength) {
                    val randomOption = Random.nextInt(selectedOptions.size)
                    val characters = selectedOptions[randomOption]
                    val randomChar = characters[Random.nextInt(characters.length)]
                    append(randomChar)
                }
            }

            textView.text = "Пароль (ур.${index + 1}): \n$password"
        }
    }
}
