package com.juango.masterdetailpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail_post.*

class DetailPostFragment : Fragment() {

    private val args: DetailPostFragmentArgs by navArgs()

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
//        button_second.setOnClickListener {
//            findNavController().navigate(R.id.action_Detail_to_List)
//        }
    }
}