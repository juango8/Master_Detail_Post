package com.juango.masterdetailpost

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detail_post.*

class DetailPostFragment : Fragment() {

    private val args: DetailPostFragmentArgs by navArgs()
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private val remoteApi = App.remoteApi
    private var adapter = CommentsListAdapter(
        listOf(
            Comment(
                2,
                6,
                "et fugit eligendi deleniti quidem qui sint nihil autem",
                "Presley.Mueller@myrl.com",
                "doloribus at sed quis culpa deserunt consectetur qui praesentium accusamus fugiat dictavoluptatem rerum ut voluptate autem voluptatem repellendus aspernatur dolorem in"
            )
        ) as MutableList<Comment>
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        tv_title_detail.text = args.titlePost
        tv_content_detail.text = args.contentPost

        rv_list_comments.layoutManager = LinearLayoutManager(activity)
        getAllComments(args.idPost)
        rv_list_comments.adapter = adapter

        setDividers()
    }

    private fun setDividers() {
        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            rv_list_comments.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getColor(it, R.color.black), heightInPixels
                )
            )
        }
    }

    private fun getAllComments(postId: Int) {
        networkStatusChecker.performIfConnectedToInternet {
            remoteApi.getDetailComments(postId) { comment, error ->
                if (comment.isNotEmpty()) {
                    onCommentReceived(comment)
                } else if (error != null) {
                    Toast.makeText(context, "Failed to fetch comments!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onCommentReceived(comments: List<Comment>) {
        adapter = CommentsListAdapter(comments as MutableList<Comment>)
    }
}