package com.aday.abook.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aday.abook.R
import com.aday.core.utils.loadUrl
import com.aday.model.entity.BookInfo

class BookSearchAdapter(
    private val bookList: ArrayList<BookInfo>,
    private val click: (BookInfo) -> Unit
): RecyclerView.Adapter<BookSearchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
        holder.itemView.setOnClickListener { click(bookList[position]) }
    }

    override fun getItemCount(): Int = bookList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val bookName = itemView.findViewById<TextView>(R.id.bookNameResult)
        private val bookAuthor = itemView.findViewById<TextView>(R.id.authorNameResult)
        private val bookPublisher = itemView.findViewById<TextView>(R.id.publisherNameResult)
        private val bookImage = itemView.findViewById<ImageView>(R.id.bookImage)

        fun bind(bookList: BookInfo){
            bookName.text = bookList.title
            bookAuthor.text = bookList.author
            bookPublisher.text = bookList.publisher
            bookImage.loadUrl(bookList.image)
        }
    }
}