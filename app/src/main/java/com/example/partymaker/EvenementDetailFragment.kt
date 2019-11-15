package com.example.partymaker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.partymaker.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_evenement_detail.*
import kotlinx.android.synthetic.main.evenement_detail.view.*

/**
 * A fragment representing a single Evenement detail screen.
 * This fragment is either contained in a [EvenementListActivity]
 * in two-pane mode (on tablets) or a [EvenementDetailActivity]
 * on handsets.
 */
class EvenementDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: DummyContent.EvenementItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.name
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.evenement_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.evenement_detail.text = it.details
            rootView.evenement_member1.text = it.members[0]
            rootView.evenement_member2.text = it.members[1]
            rootView.evenement_member3.text = it.members[2]
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
