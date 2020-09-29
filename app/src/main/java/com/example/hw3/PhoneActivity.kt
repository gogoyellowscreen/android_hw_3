package com.example.hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw3.MainActivity.Companion.EXTRA_NAME
import com.example.hw3.MainActivity.Companion.EXTRA_NUMBER
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        val phoneName = intent.getStringExtra(EXTRA_NAME)
        val phoneNumber = intent.getStringExtra(EXTRA_NUMBER)
        phone_name.text = phoneName
        phone_number.text = phoneNumber
    }
}