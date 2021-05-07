package com.juango.masterdetailpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostsListFragment : Fragment() {

    private val adapter = PostsListAdapter(
        listOf(
            Post(
                1,
                1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehen" +
                        "derit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveni" +
                        "et architecto"
            ),
            Post(
                1,
                2,
                "occaecati excepturi optio reprehenderit",
                "expedita et cum reprehen" +
                        "derit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveni" +
                        "et architecto"
            )
        ) as MutableList<Post>, this
    )
    private var tracker: SelectionTracker<Long>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post_recycler_view.layoutManager = LinearLayoutManager(activity)
        post_recycler_view.adapter = adapter

        tracker = SelectionTracker.Builder(
            "favoritesSelection",
            post_recycler_view,
            StableIdKeyProvider(post_recycler_view),
            PostDetailsLookup(post_recycler_view),
            StorageStrategy.createLongStorage()
        )
            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()
        adapter.tracker = tracker

        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            post_recycler_view.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getColor(it, R.color.black), heightInPixels
                )
            )
        }
    }

}