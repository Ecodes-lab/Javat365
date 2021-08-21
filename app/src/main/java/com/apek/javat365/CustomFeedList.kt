package com.apek.javat365

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CustomFeedList(private val context: Activity, private val type: Array<String>, private val title: Array<String>, private val description: Array<String>, private val view_count: Array<String>, private val time: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_feed_list, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_feed_list, null, true)

        val feed_type = rowView.findViewById(R.id.feed_type) as TextView
        val feed_title = rowView.findViewById(R.id.feed_title) as TextView
//        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val feed_decription = rowView.findViewById(R.id.feed_description) as TextView
        val feed_view_count = rowView.findViewById(R.id.feed_view_count) as TextView
        val feed_time = rowView.findViewById(R.id.feed_time) as TextView


        feed_type.text = type[position]
        feed_title.text = title[position]
//        imageView.setImageResource(imgid[position])
        feed_decription.text = description[position]
        feed_view_count.text =  view_count[position]
        feed_time.text = time[position]

        return rowView
    }
}