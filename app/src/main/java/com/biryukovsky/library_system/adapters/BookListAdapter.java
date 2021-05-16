package com.biryukovsky.library_system.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.biryukovsky.library_system.BookDetailActivity;
import com.biryukovsky.library_system.BookViewHolder;
import com.biryukovsky.library_system.Constants;
import com.biryukovsky.library_system.data.entities.Book;

public class BookListAdapter extends ListAdapter<Book, BookViewHolder> {

    private final View.OnClickListener onClickListener;

    public BookListAdapter(@NonNull DiffUtil.ItemCallback<Book> diffCallback) {
        super(diffCallback);

        onClickListener = v -> {
            Book book = (Book) v.getTag();

            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra(Constants.EXTRA_BOOK_ID, book.getId());
            v.getContext().startActivity(intent);
        };
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BookViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book current = getItem(position);
        holder.bind(current);
        holder.itemView.setTag(current);
        holder.itemView.setOnClickListener(onClickListener);
    }

    public static class BookDiff extends DiffUtil.ItemCallback<Book> {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
