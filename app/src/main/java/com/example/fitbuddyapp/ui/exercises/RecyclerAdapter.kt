package com.example.fitbuddyapp.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbuddyapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerAdapter(val onSelect:(position: Int)->Unit ) : RecyclerView.Adapter<ExerciseViewHolder>(){


    private  val title: List<String> = ExerciseObject.getTitles()
    private val detail:List<String> = ExerciseObject.getDetails()
    private val image:List<Int> = ExerciseObject.getImages()
    private val number:List<Int> = ExerciseObject.getNumbers()
    val db = Firebase.firestore


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder{

        val v=LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ExerciseViewHolder(v)
    }

    override fun onBindViewHolder(holder:ExerciseViewHolder, position: Int) {

        holder.itemTitle.text=title[position]
        holder.itemDetail.text=detail[position]
        holder.itemImage.setImageResource(image[position])
        var user_data = db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener{ result->
            var nr=result.getString("how active")
            if(nr=="1" || nr=="empty")
            {holder.itemNumber.text= number[position].toString()}
            if(nr=="2")
            {var new_number=number[position]+10;
                holder.itemNumber.text= new_number.toString()}
            if(nr=="3")
            {var new_number=number[position]+20;
                holder.itemNumber.text= new_number.toString()}
        }

        holder.itemView.setOnClickListener {
            onSelect(position)
        }

    }

    override fun getItemCount():Int{
       return detail.size
    }

}

 class ExerciseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    var itemImage: ImageView=itemView.findViewById(R.id.item_image)
    var itemTitle:TextView=itemView.findViewById(R.id.item_title)
     var itemNumber:TextView =itemView.findViewById(R.id.item_number)
    var itemDetail:TextView=itemView.findViewById(R.id.item_detail)
}