package org.example.project.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.example.project.R
import org.example.project.model.SerialStoreData


class VerticalScrollAdapter(
    private val dataSet: Array<SerialStoreData>,
    private val onClickFunction: (index: Int)->Unit) :
    RecyclerView.Adapter<VerticalScrollAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.vertical_scroller_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = "    ${"%.2f".format(dataSet[position].price)}\$"
        viewHolder.otherTextView.text = dataSet[position].title
        viewHolder.bodyTextView.text ="Rated: ${dataSet[position].rating.rate}/5"


        viewHolder.imageView.load(dataSet[position].image)

        viewHolder.clickLayout.setOnClickListener {
            onClickFunction(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.cardH2)
        val otherTextView: TextView = view.findViewById(R.id.cardH1)
        val imageView: ImageView = view.findViewById(R.id.cardImage)
        val bodyTextView: TextView = view.findViewById(R.id.cardBody)
        var clickLayout : ConstraintLayout = view.findViewById(R.id.cardClick)
    }

}


