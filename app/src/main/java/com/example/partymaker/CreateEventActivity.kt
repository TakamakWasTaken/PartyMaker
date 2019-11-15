package com.example.partymaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_event.*
import org.jetbrains.anko.toast

class CreateEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)


        button_createAnnuler.setOnClickListener {
            finish()
        }

        button_createConfirm.setOnClickListener {
            toast("Création effectuée")
        }
    }
}
