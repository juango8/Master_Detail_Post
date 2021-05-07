package com.juango.masterdetailpost

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsListAdapter(
    private val posts: MutableList<Post>,
    private val actualFragment: PostsListFragment
) :
    RecyclerView.Adapter<PostsListAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(itemView: View, private val fragment: PostsListFragment) :
        View.OnClickListener, RecyclerView.ViewHolder(itemView) {
        private lateinit var post: Post

        init {
            itemView.setOnClickListener(this)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

        @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
        fun bind(post: Post) {
            this.post = post
            itemView.context
            itemView.tv_title.text = post.title
            itemView.tv_body.text = "${post.body.substring(0, 60)}..."
        }

        override fun onClick(view: View?) {
            view?.let {
                val action =
                    PostsListFragmentDirections.actionListToDetail(post.id, post.title, post.body)
                findNavController(fragment).navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.list_item_post, parent, false)
        return ViewHolder(contactView, actualFragment)
    }

    override fun getItemCount() = posts.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemId(position: Int): Long = position.toLong()

}