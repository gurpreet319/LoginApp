package com.example.task

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var sp : SharedPreferences
        var username  :String
        var password :String
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sp = getSharedPreferences("data_file", MODE_PRIVATE)
        editTextTextEmailAddressUsername.setText(sp.getString("un", ""))
        editTextTextPasswordPassword.setText(sp.getString("ps", ""))


        checkBox.setOnClickListener {
            username = editTextTextEmailAddressUsername.text.toString()
            password = editTextTextPasswordPassword.text.toString()

            val ed : SharedPreferences.Editor = sp.edit()
            ed.putString("un", username)
            ed.putString("ps", password)
            ed.commit()
        }
        buttonSubmit.setOnClickListener {
            username = editTextTextEmailAddressUsername.text.toString()
            password = editTextTextPasswordPassword.text.toString()

            /*val psd = sp.getString("p", password)
            val usd = sp.getString("u", username)*/

            val usd = intent.extras!!.getString("usd", username)
            val psd = intent.extras!!.getString("psd", "gurpreet@123")

            if(password.compareTo(psd as String) == 0 && username.compareTo(usd as String) == 0)
                {
                    val i = Intent(this, secondactivity::class.java)
                    Toast.makeText(this,"Welcome $username", Toast.LENGTH_LONG).show()
                    startActivity(i)
                }

        else Toast.makeText(this,"Invalid Password or Username", Toast.LENGTH_SHORT).show()

            }


        buttonReg.setOnClickListener {
            var In = Intent(this, Register::class.java)
            startActivity(In)
        }


    }
}