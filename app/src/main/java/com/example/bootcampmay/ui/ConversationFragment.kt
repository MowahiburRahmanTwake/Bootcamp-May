package com.example.bootcampmay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampmay.adapter.ConversationAdapter
import com.example.bootcampmay.adapter.UserAdapter
import com.example.bootcampmay.databinding.FragmentConversationBinding
import com.example.bootcampmay.databinding.FragmentHomeBinding
import com.example.bootcampmay.databinding.FragmentUserListBinding
import com.example.bootcampmay.model.ConversationModel
import com.example.bootcampmay.model.UserModel
import com.example.bootcampmay.utils.fragmentAdd

class ConversationFragment: Fragment() {
    private lateinit var binding: FragmentConversationBinding

    private val convs = arrayListOf<ConversationModel>()
    private var convAdapter: ConversationAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConversationBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        convAdapter = ConversationAdapter(convs,"123")
        binding.rvMain.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        binding.rvMain.adapter = convAdapter

        convAdapter?.setItemClick {}


        loadData()
    }
    private fun loadData(){

        convs.add(
            ConversationModel(
                "1234",
                "Twake",
                "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                "Hi hello",
                "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                null,
                "00:00:00",

            )
        )
        convs.add(
            ConversationModel(

                "123",
                "Twake2",
                "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                "Hi hello2",
                "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
                null,
                "00:00:00",

            )
        )

        convAdapter?.notifyDataSetChanged()

    }//loadData
}