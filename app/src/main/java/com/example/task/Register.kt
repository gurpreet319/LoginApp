package com.example.task

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        lateinit var sp : SharedPreferences
        sp = getSharedPreferences("data_file", MODE_PRIVATE)
        var gen:String =""
        Gender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radioG: RadioButton = findViewById(checkedId)
                gen = radioG.text.toString()

            }
        )
        var veh :String = ""
        vehicle.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radioV: RadioButton = findViewById(checkedId)
                veh = radioV.text.toString()
                }
        )

        var selectItem = ""

        val varr: Array<String> = resources.getStringArray(R.array.Countries)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, varr
            )
            spinner.adapter = adapter

            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    selectItem = varr[position]

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        }


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val username = editTextTextPersonNameU.text.toString()
            val password = editTextTextPersonNameP.text.toString()
            val ed : SharedPreferences.Editor = sp.edit()
            ed.putString("u", username)
            ed.putString("p", password)
            ed.commit()

            val builder   = AlertDialog.Builder(this)
            builder.setTitle("Check Data!!")
            builder.setMessage("Username = $username \nPassword = $password" +
                    "\nGender = $gen\nVehicle = $veh\nCountry = $selectItem")


            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"Data is saved",Toast.LENGTH_LONG).show()
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("usd", username )
                i.putExtra("psd", password)
                startActivity(i)
            }

            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext,"Data is not saved",Toast.LENGTH_LONG).show()
            }
            val alertDialog :AlertDialog = builder.create()

                alertDialog.setCancelable(false)
                alertDialog.show()

            }
        }

    }




