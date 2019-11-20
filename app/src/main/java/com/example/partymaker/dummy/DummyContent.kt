package com.example.partymaker.dummy

import com.example.partymaker.Models.EvenementsModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<EvenementsModel> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, EvenementsModel> = HashMap()

    private val COUNT = 3

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            //addItem(createDummyItem(i))
        }
        var membres1: ArrayList<String> = ArrayList<String>()
        membres1.add("Les rois mages")
        membres1.add("Marie")
        membres1.add("D.")
        membres1.add("Des animaux")
        addItem(createDummyItem("1", "Anniversaire de J-C", "24/12/2019", "On fêtera ses 2019 ans!", membres1))
        var membres2: ArrayList<String> = ArrayList<String>()
        membres2.add("Personne 1")
        membres2.add("Personne 2")
        membres2.add("P3")
        addItem(createDummyItem("2", "Crémaillère", "02/06/2020", "L'emménagement de Louis et Mat' est terminé, célèbront ça :)", membres2))
    }

    fun addItem(item: EvenementsModel) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    /*private fun createDummyItem(position: Int): EvenementsModel {

        return EvenementsModel(position.toString(), "Item " + position, makeDetails(position), "dd/MM/yy", ArrayList<String>())
    }*/
    private fun createDummyItem(id: String, name: String, date: String, details: String, members: ArrayList<String>): EvenementsModel {

        return EvenementsModel(id, name, date, details, members)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details de l'évènement : ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class EvenementItem(val id: String, val name: String, val details: String, val date: String, val members: ArrayList<String>) {
        override fun toString(): String = name
    }
}
