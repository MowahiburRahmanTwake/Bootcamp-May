package com.example.bootcampmay.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.bootcampmay.R
import com.example.bootcampmay.databinding.RowConversitionBinding
import com.example.bootcampmay.databinding.RowUserBinding
import com.example.bootcampmay.model.ConversationModel
import com.example.bootcampmay.model.UserModel

class ConversationAdapter (private val mList: List<ConversationModel>, private var myUid:String) :
    RecyclerView.Adapter<ConversationAdapter.MyViewHolder>() {

        var onItemClick: ((ConversationModel) -> Unit)? = null
        fun setItemClick(action: (ConversationModel) -> Unit){
            onItemClick = action
        }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_user, parent, false)
        val binding = RowConversitionBinding.inflate(LayoutInflater.from(parent.context), parent,false)

        return MyViewHolder(binding)
    }



    // binds the list items to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = mList[position]

        holder.binding.tvUserMessage.text = item.message
        holder.binding.tvDateTime.text = item.timestampLocal


        if (!item.imageMessage.isNullOrEmpty()){
            holder.binding.ivMessageImage.visibility = View.VISIBLE
            holder.binding.ivMessageImage.load(item.userImage) {
                crossfade(true)
                placeholder(R.drawable.user)
                error(R.drawable.user)
            }
        }else{
            holder.binding.ivMessageImage.visibility = View.GONE
        }

        if (myUid ==item.userId){//me

            holder.binding.ivUser.visibility = View.GONE
            holder.binding.ivUserMe.visibility = View.VISIBLE

            holder.binding.tvUserMessage.gravity = Gravity.END
            holder.binding.tvDateTime.gravity = Gravity.END

            holder.binding.ivMessageImage.scaleType = ImageView.ScaleType.FIT_END


            holder.binding.ivUserMe.load(item.userImage) {
                crossfade(true)
                placeholder(R.drawable.user)
                error(R.drawable.user)
            }

        }else{//other user

            holder.binding.ivUser.visibility = View.VISIBLE
            holder.binding.ivUserMe.visibility = View.GONE

            holder.binding.tvUserMessage.gravity = Gravity.START
            holder.binding.tvDateTime.gravity = Gravity.START

            holder.binding.ivMessageImage.scaleType = ImageView.ScaleType.FIT_START


            holder.binding.ivUser.load(item.userImage) {
                crossfade(true)
                placeholder(R.drawable.user)
                error(R.drawable.user)
            }

        }



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class MyViewHolder(var binding: RowConversitionBinding) : RecyclerView.ViewHolder(binding.root) {
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