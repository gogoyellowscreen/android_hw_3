package com.example.hw3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val myRequestId = 667

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_NUMBER = "EXTRA_NUMBER"
        const val MESSAGE = "Cannot access contacts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS), myRequestId)
        } else {
            viewContacts()
        }
    }

    private fun viewContacts() {
        val contactList = fetchAllContacts()
        Toast.makeText(
            this@MainActivity,
            resources.getQuantityString(R.plurals.numberOfContacts, contactList.size, contactList.size),
            Toast.LENGTH_SHORT
        ).show()
        val viewManager = LinearLayoutManager(this)
        myRecyclerView.apply {
            layoutManager = viewManager
            adapter = ContactAdapter(contactList) {
                val intent: Intent = Intent(this.context, PhoneActivity::class.java).apply {
                    putExtra(EXTRA_NAME, it.name)
                    putExtra(EXTRA_NUMBER, it.phoneNumber)
                }
                startActivity(intent)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            myRequestId -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewContacts()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        MESSAGE,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}