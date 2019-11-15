package com.example.partymaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser


class MainActivity : MenuActivity() {
    private var mAuth: FirebaseAuth? = null
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //récupération de l'instance FireBase
        mAuth = FirebaseAuth.getInstance()
        button_connect.setOnClickListener {view ->
            var mail = input_connectMail.text.toString()
            var pass = input_connectPass.text.toString()
            if(checkUserCredentials(mail, pass)){
                signIn(view, mail, pass)
            }
        }

        //DELETE THIS BUTTON BEFORE PROD, for dev purpose only.
        button_fastConnect.setOnClickListener{
            input_connectMail.setText("gau@y.co")
            input_connectPass.setText("testeur")
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check si le user est connecté (non-null) & update interface.
        val currentUser = mAuth!!.getCurrentUser()
        updateUI(currentUser)
    }

    //mise à jour de l'interface selon les infos de l'utilisateur
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            input_connectMail.setText(user.email)
        } else {
            toast("Bienvenue sur PartyMaker")
        }
    }

    //fonction de connexion de firebase utilisant l'email et le mot de passe
    fun signIn(view: View,email: String, password: String){
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                toast("Authentification réussie")
                var intent = Intent(this, EvenementListActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)
            }else{
                toast("Erreur de connexion")
                //Log.w("xxxERRORxxx", "createUserWithEmail:failure", task.exception)
               // showMessage(view,"Error: ${task.exception?.message}")
            }
        })
    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    //lancement de l'activité inscription
    fun startInscription(view: View){
        val intentionInscription = Intent(this, InscriptionActivity::class.java)
        startActivity(intentionInscription)
    }

    //Never Trust User Entry
    private fun checkUserCredentials(email: String, pass: String): Boolean{
        var connect = false

        if(pass.length > 5 && isEmailValid(email)){
            connect = true
        }
        else{
            toast("Email ou mot de passe incorrect!")
        }

        return connect
    }

    //Fonction de vérification automatique de la validité de l'email.
    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
