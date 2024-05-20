package com.example.bootcampmay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bootcampmay.databinding.FragmentConversationBinding
import com.example.bootcampmay.databinding.FragmentHomeBinding
import com.example.bootcampmay.databinding.FragmentUserListBinding
import com.example.bootcampmay.utils.fragmentAdd

class ConversationFragment: Fragment() {
    private lateinit var binding: FragmentConversationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConversationBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// todo this line adapter click
        val bundle = Bundle().apply {
//            putParcelable()
        }
        fragmentAdd(activity,ConversationFragment(),bundle)
    }
}