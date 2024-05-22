package com.example.bootcampmay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.bootcampmay.R
import com.example.bootcampmay.databinding.RowUserBinding
import com.example.bootcampmay.model.UserModel

class UserAdapter (private val mList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

        var onItemClick: ((UserModel) -> Unit)? = null
        fun setItemClick(action: (UserModel) -> Unit){
            onItemClick = action
        }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_user, parent, false)
        val binding = RowUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)

        return MyViewHolder(binding)
    }



    // binds the list items to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = mList[position]

        holder.binding.tvUserName.text = item.userName
        holder.binding.tvLastMessage.text = item.lastMessage
        holder.binding.ivUser.load(item.userImage) {
            crossfade(true)
            placeholder(R.drawable.user)
            error(R.drawable.user)
        }



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class MyViewHolder(var binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.let {
                    binding.root.setOnClickListener {
                        it(mList[adapterPosition])

                    }
                }

            }
        }
    }
}