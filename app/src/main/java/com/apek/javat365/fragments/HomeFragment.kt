package com.apek.javat365.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.apek.javat365.CustomFeedList
import com.apek.javat365.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        val botAppBar = findViewById<BottomAppBar>(R.id.bottom_app_bar);
////        //  attach menu to your BottomAppBar
////        botAppBar.replaceMenu(R.menu.bottom_app_bar_menu);
//
//        // use arrayadapter and define an array
//        val arrayAdapter: ArrayAdapter<*>
//
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val feed_type = arrayOf<String>(
                "Document",
                "Document",
                "Document",
                "Document",
                "Document"
        )
        val feed_title = arrayOf<String>(
            "Javat logo-01.svg", "doc-file.docx", "pdf-file.pdf",
            "zip file.zip", "ppt file.ppt"
        )

        val feed_description = arrayOf<String>(
            "Contrary to popular belief", "Lorem ipsum dolor sit amet", "Sed ut perspiciatis unde",
            "Itaque earum rerum", "Nam libero tempore"
        )

        val feed_view_count = arrayOf<String>(
                "20 views",
                "10 views",
                "2k views",
                "200k views",
                "1m views"
        )

        val feed_time = arrayOf<String>(
                "1 day ago",
                "2 day ago",
                "now",
                "1 sec ago",
                "3 months ago"
        )

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        // access the listView from xml file
        var mListView = view.findViewById<ListView>(R.id.feed_list_view)
//        arrayAdapter = ArrayAdapter(this,
//            android.R.layout.simple_list_item_1, users)
//        mListView.adapter = arrayAdapter

        val myListAdapter = CustomFeedList(requireActivity(), feed_type, feed_title, feed_description, feed_view_count, feed_time)
        mListView.adapter = myListAdapter

        mListView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
//            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}