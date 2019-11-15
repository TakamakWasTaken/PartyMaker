package com.example.partymaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.partymaker.Models.EvenementsModel
import com.example.partymaker.dummy.DummyContent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
            createEvent()
        }
    }

    fun createEvent(){
        toast("Création effectuée")
        val newEventName = input_newEventName.text.toString()
        val newEventDescription = input_newEventDetails.text.toString()
        val newEventDate = input_newEventDate.text.toString()

        //NTUE

        if(checkNewEventValues(newEventName, newEventDescription, newEventDate)){
            val newEvent = EvenementsModel("3", newEventDescription, newEventName, newEventDate, ArrayList<String>())
            DummyContent.addItem(newEvent)

            //create event in BDD
            //createInDb(newEvent)
            finish()
        }
    }

    private fun checkNewEventValues(nom: String, description: String, date: String): Boolean{
        var isEventValid = false;
        if(nom.length > 0){
            if(date.length === 10){
                isEventValid = true
            }
            else{
                toast("Format de date erroné")
            }
        }
        else{
            toast("Nom trop court")
        }
        return isEventValid
    }

    //connection à la BDD FireBase
    private fun createInDb(newEvent: EvenementsModel){

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myEvents = database.getReference("evenements")
        myEvents.setValue(newEvent)

        myEvents.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.d("xxVALxx", "Value is: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("xxFAILVALxx", "Failed to read value.", error.toException())
            }
        })
    }
}
