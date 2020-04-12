package com.chudy.miquidocatfacts.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chudy.miquidocatfacts.R
import com.chudy.miquidocatfacts.activity.DetailsActivity
import kotlinx.android.synthetic.main.item_fact.view.*
import kotlin.random.Random

/**
 * An adapter class that adapts repeatedly a received JsonArray to a pattern.
 * Takes in a response from the api and
 * creates as many items as getItemCount has.
 * @param facts a list object from a json array made with moshi
 */
class CatFactAdapter(private val context: Context, private val facts: List<CatFact>) :
    RecyclerView.Adapter<CatFactAdapter.CatFactViewHolder>() {

    /**
     * Creating a viewHolder, it holds an inflated layout of an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder =
        CatFactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        )

    /**
     * Item count is equal to the size of adapted list, in this app it is 30 (GET request param).
     */
    override fun getItemCount(): Int = facts.size

    /**
     * When binding the view holder, a random one of 3 cat icons is set,
     * each item's id is displayed and on the whole item - a listener is attached,
     * that launches a new activity with the item's fact and date it was updated at.
     */
    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        val fact = facts[position]

        when (Random.nextInt(0, 3)) {
            0 -> holder.imgCat.setImageResource(R.drawable.ic_cat_brown)
            1 -> holder.imgCat.setImageResource(R.drawable.ic_cat_ginger)
            2 -> holder.imgCat.setImageResource(R.drawable.ic_cat_pink)
        }

        val textId = "Cat fact id: ${fact._id}"
        holder.textIdFact.text = textId

        holder.relLayoutItem.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("factText", fact.text)
            intent.putExtra("factDate", fact.updatedAt)
            context.startActivity(intent)
        }

    }

    /**
     * A view holder that holds references to the inflated layout's components.
     */
    class CatFactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textIdFact: TextView = itemView.text_id_fact
        val relLayoutItem: RelativeLayout = itemView.layout_rel_item
        val imgCat: ImageView = itemView.image_cat
    }
}