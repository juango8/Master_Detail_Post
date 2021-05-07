package com.juango.masterdetailpost

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostsListFragment : Fragment() {

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val remoteApi = App.remoteApi
    private var adapter = PostsListAdapter(
        listOf(
            Post(
                1,
                1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehen" +
                        "derit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveni" +
                        "et architecto"
            )
        ) as MutableList<Post>, this
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post_recycler_view.layoutManager = LinearLayoutManager(activity)
        getAllPosts()
        post_recycler_view.adapter = adapter

        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            post_recycler_view.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getColor(it, R.color.black), heightInPixels
                )
            )
        }
    }

    private fun getAllPosts() {
        networkStatusChecker.performIfConnectedToInternet {
            remoteApi.getPosts { posts, error ->
                if (posts.isNotEmpty()) {
                    onPostReceived(posts)
                } else if (error != null) {
                    Toast.makeText(context, "Failed to fetch posts!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onPostReceived(posts: List<Post>) {
        adapter = PostsListAdapter(posts as MutableList<Post>, this)
    }

}