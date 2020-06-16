package com.aday.abook.feature.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aday.abook.R
import com.aday.core.utils.loadUrl
import com.aday.model.room.BookListEntity

class BookListAdapter(
    private val myAllBookList: ArrayList<BookListEntity>,
    private val click: (BookListEntity) -> Unit
): RecyclerView.Adapter<BookListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_saved_my_book_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = myAllBookList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myAllBookList[position])
        holder.itemView.setOnClickListener { click(myAllBookList[position]) }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val bookName = itemView.findViewById<TextView>(R.id.myBookName)
        private val bookImage = itemView.findViewById<ImageView>(R.id.myBookImage)
        private val myRating = itemView.findViewById<RatingBar>(R.id.myRating)
        private val fiveWords = itemView.findViewById<TextView>(R.id.myFiveWords)

        fun bind(myBook: BookListEntity){
            bookName.text = myBook.name
            bookImage.loadUrl(myBook.image)
            myRating.rating = myBook.rating
            fiveWords.text = myBook.fiveWords
        }
    }
}