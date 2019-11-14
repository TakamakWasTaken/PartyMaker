package com.example.partymaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_inscription.*
import org.jetbrains.anko.toast
import android.util.Log


class InscriptionActivity : AppCompatActivity() {

    private var newUserMail: String = ""
    private var newUserName: String = ""
    private var newUserPass: String = ""
    private var newUserConfirmPass: String = ""

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        //Récupération de l'instance FireBase
        mAuth = FirebaseAuth.getInstance()

        //définition des actions sur le clique des boutons annuler & confirmer inscription
        button_inscriptionAnnuler.setOnClickListener {
            finish()
        }
        button_inscriptionConfirm.setOnClickListener {
            confirmInscription()
        }
    }

    private fun confirmInscription(){

        //récupération des informations entrées par l'utilisateur
        newUserMail = input_inscriptionMail.text.toString()
        newUserName = input_inscriptionNom.text.toString()
        newUserPass = input_inscriptionPass.text.toString()
        newUserConfirmPass = input_inscriptionConfirmPass.text.toString()

        //Si NTUE passé avec succès
        if(checkUserCredentials(newUserMail, newUserPass, newUserConfirmPass)){
            toast("Création de votre compte $mAuth")
            mAuth?.createUserWithEmailAndPassword(newUserMail, newUserPass)
                ?.addOnCompleteListener(this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        toast("création successfull")
                        val user = mAuth!!.getCurrentUser()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Log.w("xxxERRORxxx", "createUserWithEmail:failure", task.exception)
                        toast("Authentication failed.")
                    }
                }
        }
    }

    //Never Trust User Entry
    private fun checkUserCredentials(email: String, pass: String, confirmPass: String): Boolean{
        var isPassValid = false
        var isMailValid = false
        var isUserValid = false

        //vérification du format de l'adresse mail
        if(isEmailValid(email)){
            isMailValid = true
            if(newUserName.length > 0){
                //vérification de la frome du mot de passe & de la validité de la confirmation
                if(pass!!.length > 5){
                    if(pass == confirmPass ){
                        isPassValid = true
                    }
                    else{
                        toast("Confirmation du mot de passe erronée")
                    }
                }
                else{
                    toast("Mot de passe trop court")
                }
            }
            else{
                toast("Veuillez indiquer votre Nom")
            }
        }
        else{
            toast("Email invalide")
        }

        if(isMailValid && isPassValid){
            isUserValid = true
        }
        return isUserValid
    }

    //Fonction de vérification de la validité de l'email.
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
