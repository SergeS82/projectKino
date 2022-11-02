package ru.anudx.project_kino

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.getSystemService
import ru.anudx.project_kino.databinding.SubscribeBinding

class SubscribeView(context: Context, @Nullable attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    private val editText: EditText
    private val subscribeButton: Button
    init {
        // "Надуваем наш фрагмент"
        LayoutInflater.from(context).inflate(R.layout.subscribe, this)
        val bs = SubscribeBinding.inflate(context.getSystemService()!!)
        //Нужно явно указать ориентацию, если она вертикальная, иначе отображение будет не корректное
        this.orientation = VERTICAL
        // Привязываем наши View из xml (можно и без переменных обращаться к ним сразу напрямую)
        editText = bs.etSubscribe
        subscribeButton = bs.btnSubscribe

        //Вешаем слушатель на нашу кнопку, сейчас он отправляет данные из EditText в Toast, в рабочем приложении он бы отправлял данные на сервер
        subscribeButton.setOnClickListener {
            Toast.makeText(context, editText.text, Toast.LENGTH_SHORT).show()
        }
    }
}