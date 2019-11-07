package com.example.partymaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun startInscription(view: View){
        val intention = Intent(this, InscriptionActivity::class.java)
        startActivity(intention)
    }

    //Check user identifiers, if tokens are matching connect him and start eventListActivity. If tokens are wrong, inform user and stay put.
    fun connect(view: View){


    }
}
