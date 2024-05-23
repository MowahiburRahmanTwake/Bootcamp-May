package com.example.bootcampmay.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampmay.adapter.UserAdapter
import com.example.bootcampmay.databinding.FragmentUserListBinding
import com.example.bootcampmay.model.UserModel
import com.example.bootcampmay.utils.fragmentAdd
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserListFragment:Fragment(){

    private lateinit var binding: FragmentUserListBinding
    private val users = arrayListOf<UserModel>()
    private var userAdapter: UserAdapter?=null

    private val db = Firebase.firestore

    private var userModel:UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userModel = it.getParcelable("USER_MODEL") as? UserModel?
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserListBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(users)
        binding.rvMain.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        binding.rvMain.adapter = userAdapter

        userAdapter?.setItemClick {
            val bundle = Bundle().apply {
                putParcelable("USER_ANOTHER_MODEL",it)
                putParcelable("USER_MODEL",userModel)
        }
//
        fragmentAdd(activity,ConversationFragment(),bundle)
        }

        setUserToFirestore()

    }

    private fun setUserToFirestore(){
        // Create a new user with a first and last name
        val user = UserModel(
                userModel?.userId?:"",
                userModel?.userName?:"",
                userModel?.userImage?:"",
                "",
                            )

// Add a new document with a generated ID
        db.collection("user")
                .document(userModel?.userId?:"")
                .set(user)
                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                    loadData()

                }
                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
                }

    }

    private fun loadData(){

        db.collection("user")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
//                        Log.d(TAG, "${document.id} => ${document.data}")


                        val d = document.data
                        val uId = d.get("userId") as? String?
                        val uName = d.get("userName") as? String?
                        val uImage = d.get("userImage") as? String?
                        val lm = d.get("lastMessage") as? String?


                        val myUid = userModel?.userId?:""
                        if (uId != myUid){
                            users.add(
                                    UserModel(
                                            uId ?:"",
                                            uName ?:"",
                                            uImage ?:"",
                                            lm ?:""

                                             )
                                     )
                        }


                    } //for
                    activity?.runOnUiThread {
                        userAdapter?.notifyDataSetChanged()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents.", exception)
                }


//
//
//        users.add(
//                UserModel(
//                        "1234",
//                        "Twake2",
//                        "https://images.pexels.com/photos/1133957/pexels-photo-1133957.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//                        "Hi hello2"
//
//                         )
//                 )


    }//loadData
}



