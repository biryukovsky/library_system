package com.biryukovsky.library_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.biryukovsky.library_system.data.entities.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final TextView bookItemView;

    private BookViewHolder(View itemView) {
        super(itemView);
        bookItemView = itemView.findViewById(R.id.bookTitleTextView);
//        TODO: access to other fields
    }

    public void bind(Book book) {
        bookItemView.setText(book.getTitle());
    }

    public static BookViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_book_item, parent, false);
        return new BookViewHolder(view);
    }
}
