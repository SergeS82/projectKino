package ru.anudx.project_kino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.anudx.project_kino.databinding.ActivityReturnBinding
import ru.anudx.project_kino.databinding.ActivityReturnContentBinding
import java.nio.channels.InterruptedByTimeoutException

class ReturnContentActivity : AppCompatActivity() {
    private lateinit var b: ActivityReturnContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityReturnContentBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.button.setOnClickListener {
            val intent = Intent()
            val text = b.editTextTextPersonName.text.toString()
            intent.putExtra("result", text)
            setResult(0,intent)
            onBackPressed()
        }
    }
}