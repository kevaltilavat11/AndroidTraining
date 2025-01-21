package com.example.mytodobook

import android.content.Intent
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(
    private val bookList: List<Book>,
    private val context: Context
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookName: TextView = itemView.findViewById(R.id.tvBookName)
        val authorName: TextView = itemView.findViewById(R.id.tvAuthorName)
        val genre: TextView = itemView.findViewById(R.id.tvGenre)
        val ageGroups: TextView = itemView.findViewById(R.id.tvAgeGroups)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bookName.text = book.bookName
        holder.authorName.text = "Author Name: ${book.authorName}"
        holder.genre.text = "Genre: ${book.genre}"
        holder.ageGroups.text = "Age Groups: ${book.ageGroups.joinToString(", ")}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra("BOOK_TITLE", book.bookName)
            intent.putExtra("BOOK_AUTHOR", book.authorName)
            intent.putExtra("GENRE", book.genre)
            intent.putExtra("AGE_GROUPS", book.ageGroups.joinToString(", "))
            intent.putExtra("FACTION", book.isFiction)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = bookList.size
}
