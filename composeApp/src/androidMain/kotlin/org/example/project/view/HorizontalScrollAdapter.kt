package org.example.project.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.example.project.R
import org.example.project.model.SerialStoreData


internal class HorizontalScrollAdapter // Constructor of our ViewPager2Adapter class
    (private val dataSet: Array<SerialStoreData>,
     private val onClickFunction: (index: Int)->Unit) :
    RecyclerView.Adapter<HorizontalScrollAdapter.ViewHolder>() {


    // This method returns our layout
    override fun onCreateViewHolder(viewGroup:  ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.horizontal_scroller_item, viewGroup, false)
        return ViewHolder(view)
    }

    // This method binds the screen with the view
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = "${"%.2f".format(dataSet[position].price)}$"
        viewHolder.otherTextView.text = dataSet[position].title
        viewHolder.bodyTextView.text = dataSet[position].category
        viewHolder.detailText.text = dataSet[position].description
        viewHolder.imageView.load(dataSet[position].image)
        viewHolder.clickLayout.setOnClickListener {
            onClickFunction(position)
        }

    }

    // This Method returns the size of the Array
    override fun getItemCount() = dataSet.size

    // The ViewHolder class holds the view
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.fragmentH2)
        val otherTextView: TextView = view.findViewById(R.id.fragmentH1)
        val bodyTextView: TextView = view.findViewById(R.id.fragmentBody)
        var imageView: ImageView = view.findViewById(R.id.images)
        var clickLayout : LinearLayout = view.findViewById(R.id.fragmentClick)
        var detailText : TextView = view.findViewById(R.id.fragmentLong)
    }



}

