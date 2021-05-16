package com.biryukovsky.library_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.viewmodels.BookViewModel;

public class BookDetailActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private int bookId;
    private LiveData<Book> book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(BookViewModel.class);

        Intent intent = getIntent();
        bookId = intent.getExtras().getInt(Constants.EXTRA_BOOK_ID);
        book = bookViewModel.get(bookId);
        setViewItemsValues();
    }

    private void setViewItemsValues() {
        TextView titleView = findViewById(R.id.bookDetailTitleTextView);
        TextView authorView = findViewById(R.id.bookDetailAuthorTextView);
        TextView yearPublishedView = findViewById(R.id.bookDetailYearPublishedTextView);

        book.observe(this, b -> {
            titleView.setText(b.getTitle());
            authorView.setText(b.getAuthor());
            yearPublishedView.setText(b.getYearPublished());
        });
    }
}