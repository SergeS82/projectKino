package ru.anudx.project_kino

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import ru.anudx.project_kino.databinding.ActivityReturnBinding

class ReturnActivity : AppCompatActivity() {
    private lateinit var b: ActivityReturnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityReturnBinding.inflate(layoutInflater)
        setContentView(b.root)
        setSupportActionBar(b.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        b.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        b.button.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            startActivityForResult(intent, 111)
        }
        b.button2.setOnClickListener {
            val intent = Intent(this, ReturnContentActivity::class.java)
            startActivityForResult(intent, 112)
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            111 -> data?.run {
                val uri = data.data
                uri?.run {
                    val cursor = contentResolver.query(uri, null, null, null, null)
                    cursor?.run {
                        this.moveToFirst()
                        val name =
                            this.getString(this.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        this.moveToFirst()
                        val number = this.getString(cursor.getColumnIndex(Phone.NUMBER))
                        cursor.close()
                        val contact = "$name $number"
                        this@ReturnActivity.b.textView.text = contact
                    }
                }
            }
            112 -> data?.run {
                if (resultCode ==0 ){
                    val string = "Result: ${data.getStringExtra("result")}"
                    b.textView.text = string
                }
            }
        }
    }
}