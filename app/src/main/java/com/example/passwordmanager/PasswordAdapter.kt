package com.example.passwordmanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PasswordAdapter(
    private val passwordList: MutableList<Password>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {

    // ViewHolder для отображения элемента списка
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Инициализация элементов макета
        val websiteTextView: TextView = view.findViewById(R.id.websiteTextView)
        val passwordTextView: TextView = view.findViewById(R.id.passwordTextView)

        init {
            // Добавляем обработчик нажатия на элемент списка
            view.setOnClickListener {
                // Получаем позицию элемента, на который кликнули
                val position: Int = adapterPosition
                // Вызываем обработчик, передавая позицию элемента
                onItemClickListener(position)
            }
        }
    }

    // Создание нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.password_item, parent, false)
        return ViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val password = passwordList[position]

        // Установка текста и изменение внешнего вида для TextView с названием сайта
        holder.websiteTextView.apply {
            text = password.website
            setTextColor(Color.WHITE)  // Изменить цвет текста на белый
            textSize = 22f  // Изменить размер текста (в sp)
        }

        // Установка текста и изменение внешнего вида для TextView с паролем
        holder.passwordTextView.apply {
            text = password.password
            setTextColor(Color.WHITE)  // Изменить цвет текста на белый
            textSize = 18f  // Изменить размер текста (в sp)
        }
    }

    // Возвращает общее количество элементов списка
    override fun getItemCount(): Int {
        return passwordList.size
    }
}
