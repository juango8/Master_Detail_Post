package com.juango.masterdetailpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detail_post.*

class DetailPostFragment : Fragment() {

    private val args: DetailPostFragmentArgs by navArgs()
    private val adapter = CommentsListAdapter(
        listOf(
            Comment(
                2,
                6,
                "et fugit eligendi deleniti quidem qui sint nihil autem",
                "Presley.Mueller@myrl.com",
                "doloribus at sed quis culpa deserunt consectetur qui praesentium accusamus fugiat dictavoluptatem rerum ut voluptate autem voluptatem repellendus aspernatur dolorem in"
            ),
            Comment(
                1,
                2,
                "occaecati excepturi optio reprehenderit",
                "occaecati excepturi optio reprehenderit",
                "expedita et cum reprehen" +
                        "derit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveni" +
                        "et architecto"
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
        tv_title_detail.text = args.titlePost
        tv_content_detail.text = args.contentPost

        rv_list_comments.layoutManager = LinearLayoutManager(activity)
        rv_list_comments.adapter = adapter

        val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
        context?.let {
            rv_list_comments.addItemDecoration(
                DividerItemDecoration(
                    ContextCompat.getColor(it, R.color.black), heightInPixels
                )
            )
        }
//        button_second.setOnClickListener {
//            findNavController().navigate(R.id.action_Detail_to_List)
//        }
    }
}