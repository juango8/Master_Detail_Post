package com.juango.masterdetailpost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_comment.view.*


class CommentsListAdapter(
    private val comments: MutableList<Comment>
) :
    RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var comment: Comment

        fun bind(comment: Comment) {
            this.comment = comment
            itemView.context
            itemView.tv_name.text = comment.name
            itemView.tv_email.text = comment.email
            itemView.tv_comment.text = "test"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.list_item_comment, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount() = comments.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemId(position: Int): Long = position.toLong()

}