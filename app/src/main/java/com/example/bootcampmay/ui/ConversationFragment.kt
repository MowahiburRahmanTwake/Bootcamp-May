package com.example.bootcampmay.ui

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConversationFragment: Fragment() {
    private lateinit var binding: FragmentConversationBinding

    private val convs = arrayListOf<ConversationModel>()
    private var convAdapter: ConversationAdapter? = null
    private var roomId = ""

    private val db = Firebase.firestore

    private var userModel:UserModel? = null
    private var userAnotherModel:UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            userModel = it.getParcelable("USER_MODEL") as? UserModel? //me
            userAnotherModel = it.getParcelable("USER_ANOTHER_MODEL") as? UserModel? //selected user

            roomId = "${userModel?.userId}${userAnotherModel?.userId}".toCharArray().sorted().joinToString("")

        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConversationBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        convAdapter = ConversationAdapter(convs, userModel?.userId ?: "")
        binding.rvMain.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        binding.rvMain.adapter = convAdapter

        convAdapter?.setItemClick {}

        loadData()

        binding.btnSend.setOnClickListener {
            sendMessage()

        }

    }
    private fun loadData(){

        getMessageRef()
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
//                        Log.w(TAG, "listen:error", e)
                        return@addSnapshotListener
                    }

                    snapshots?.documents?.let { docs ->
                        convs.clear()
                        for (dc in docs) {
//                            if (dc.type == DocumentChange.Type.ADDED) {
//                                //Log.d(TAG, "New city: ${dc.document.data}")
//                            }

                            dc.data?.let { d->
                                val msg = d.get("message") as String?
                                val iMsg = d.get("imageMessage") as String?
                                val tsl = d.get("timeStampLocal") as String?
                                val ts = d.get("timestamp") as Timestamp?
                                val uId = d.get("userId") as String?
                                val img = d.get("userImage") as String?
                                val uName = d.get("userName") as String?

                                val t : String? = ts?.toDate()?.toLocaleString()


                                convs.add(
                                        ConversationModel(
                                                uId?: "",
                                                uName?: "",
                                                img?: "",
                                                msg?: "",
                                                iMsg?: "",
                                                null,
                                                t?: "",

                                                         )
                                         )




                            }

                        } //for
                        activity?.runOnUiThread {
                            convAdapter?.notifyDataSetChanged()
                        }
                    }




                }







//
//
//        convs.add(
//                ConversationModel(
//                        "1234",
//                        "Twake2",
//                        "https://images.pexels.com/photos/1133957/pexels-photo-1133957.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//                        "Hi hello2",
//
//                        "https://images.pexels.com/photos/1133957/pexels-photo-1133957.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//                        null,
//                        "00:00:00"
//
//                         )
//                 )
        convAdapter?.notifyDataSetChanged()

    }//loadData




    private fun sendMessage(){



        val msg = binding.etMessage.text.toString()
        binding.etMessage.text?.clear()


        // Create a new user with a first and last name
        val conv = ConversationModel(
                userModel?.userId?:"",
                userModel?.userName?:"",
                userModel?.userImage?:"",
                msg?:"",
                "",
                null,
                "",
                            )

// Add a new document with a generated ID

        getMessageRef().add(conv)
                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

//                    loadData()

                }
                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
                }

    } //sendMessage

    private fun getMessageRef(): CollectionReference {
        return db.collection("room")
                .document(roomId)
                .collection("message")
    }
}