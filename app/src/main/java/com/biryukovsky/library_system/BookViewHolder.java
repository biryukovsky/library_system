package com.biryukovsky.library_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.biryukovsky.library_system.data.entities.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final TextView bookItemTitleView;
    private final TextView bookItemAuthorView;

    private BookViewHolder(View itemView) {
        super(itemView);
        bookItemTitleView = itemView.findViewById(R.id.bookTitleTextView);
        bookItemAuthorView = itemView.findViewById(R.id.bookAuthorTextView);
//        TODO: access to other fields
    }

    public void bind(Book book) {
        bookItemTitleView.setText(book.getTitle());
        bookItemAuthorView.setText(book.getAuthor());
    }

    public static BookViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_book_item, parent, false);
        return new BookViewHolder(view);
    }
}
