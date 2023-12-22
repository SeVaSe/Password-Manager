package com.example.passwordmanager

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.databinding.ActivityMainBinding
import com.example.passwordmanager.CreatePasswordActivity
import com.example.passwordmanager.databinding.ActivityGeneratePasswordBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var passwordAdapter: PasswordAdapter
    private val passwordList = mutableListOf<Password>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Настройка RecyclerView для отображения паролей
        passwordAdapter = PasswordAdapter(passwordList) { position ->
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("Удалить пароль")
                setMessage("Вы уверены, что хотите удалить этот пароль?")
                setPositiveButton("Да") { _, _ ->
                    passwordList.removeAt(position)
                    passwordAdapter.notifyDataSetChanged()
                }
                setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            val dialog = builder.create()
            dialog.show()
        }
        binding.passwordRecyclerView.adapter = passwordAdapter
        binding.passwordRecyclerView.layoutManager = LinearLayoutManager(this)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, InfoAppActivity::class.java)
            startActivity(intent)
        }



        // Пример заполнения списка паролей (можешь удалить этот код)
        val password1 = Password("Google", "123456")
        val password2 = Password("КИП-Элжур", "qwerty")
        val password3 = Password("ВК", "sen1231sdf_231")
        val password4 = Password("TG", "saf2k2m321")
        val password5 = Password("Yandex", "1223fsdfsdsd")
        val password6 = Password("Аккаунт от Google (2)", "sasda_aaaa")
        val password7 = Password("Inst", "sevslm123")
        val password8 = Password("YouTube", "12346")
        val password9 = Password("Escape from Tarkov", "sdn123_32ms")
        val password10 = Password("Microsoft", "mic_12")
        passwordList.add(password1)
        passwordList.add(password2)
        passwordList.add(password3)
        passwordList.add(password4)
        passwordList.add(password5)
        passwordList.add(password6)
        passwordList.add(password7)
        passwordList.add(password8)
        passwordList.add(password9)
        passwordList.add(password10)

        passwordAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_password -> {
                startCreatePasswordActivity()
                true
            }
            R.id.action_generate_password -> {
                val intent = Intent(this, PasswordGeneratorActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




    private val CREATE_PASSWORD_REQUEST = 1 // Любой уникальный код для запроса

    private fun startCreatePasswordActivity() {
        val intent = Intent(this, CreatePasswordActivity::class.java)
        startActivityForResult(intent, CREATE_PASSWORD_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_PASSWORD_REQUEST && resultCode == Activity.RESULT_OK) {
            // Получаем данные из Intent, переданные из CreatePasswordActivity
            val newPassword: Password? = data?.getParcelableExtra("NEW_PASSWORD")

            // Проверяем, что пароль не null и добавляем его в список
            newPassword?.let {
                passwordList.add(it)
                passwordAdapter.notifyDataSetChanged()
            }
        }
    }

}
