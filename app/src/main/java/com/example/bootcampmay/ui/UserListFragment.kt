package com.example.bootcampmay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampmay.adapter.UserAdapter
import com.example.bootcampmay.databinding.FragmentHomeBinding
import com.example.bootcampmay.databinding.FragmentUserListBinding
import com.example.bootcampmay.model.UserModel
import com.example.bootcampmay.utils.fragmentAdd

class UserListFragment: Fragment() {
    private lateinit var binding: FragmentUserListBinding

    private val users = arrayListOf<UserModel>()
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserListBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(users)
        binding.rvMain.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        binding.rvMain.adapter = userAdapter

        userAdapter?.setItemClick {
                    val bundle = Bundle().apply {
//            putParcelable()
        }
        fragmentAdd(activity, ConversationFragment(),bundle)
        }


        loadData()
    }
    private fun loadData(){

        users.add(
            UserModel(
            "123",
            "Twake",
            "https://unsplash.com/photos/young-asian-travel-woman-is-enjoying-with-beautiful-place-in-bangkok-thailand-_Fqoswmdmoo",
            "Hi hello"

        )
        )
        users.add(
            UserModel(
            "123",
            "Twake2",
            "https://unsplash.com/photos/young-asian-travel-woman-is-enjoying-with-beautiful-place-in-bangkok-thailand-_Fqoswmdmoo",
            "Hi hello2"

        )
        )

        userAdapter?.notifyDataSetChanged()

    }//loadData
}