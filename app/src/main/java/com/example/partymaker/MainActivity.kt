package com.example.partymaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun startInscription(view: View){
        val intentionInscription = Intent(this, InscriptionActivity::class.java)
        startActivity(intentionInscription)
    }

    //Check user identifiers, if tokens are matching connect him and start eventListActivity. If tokens are wrong, inform user and stay put.
    fun connect(view: View){
        val email = input_connectMail.text.toString()
        val pass = input_connectPass.text.toString()


        if(checkUserCredentials(email, pass)){
            val intentionConnection = Intent(this, EvenementListActivity::class.java)
            intentionConnection.putExtra("connectedUserMail", email)
            intentionConnection.putExtra("connectedUserPass", pass)

            startActivity(intentionConnection)
        }
    }

    private fun checkUserCredentials(email: String, pass: String): Boolean{
        var connect = false

        if(pass.length > 3 && isEmailValid(email)){
            connect = true
        }
        else{
            toast("Email ou mot de passe incorrect!")
        }

        return connect
    }

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
