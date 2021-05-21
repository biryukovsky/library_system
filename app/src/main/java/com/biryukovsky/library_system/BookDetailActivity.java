package com.biryukovsky.library_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.viewmodels.BookViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class BookDetailActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private int bookId;
    private LiveData<Book> book;

    private TextInputLayout titleView;
    private TextInputLayout authorView;
    private TextInputLayout yearPublishedView;

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

        titleView = findViewById(R.id.bookDetailTitleTextView);
        authorView = findViewById(R.id.bookDetailAuthorTextView);
        yearPublishedView = findViewById(R.id.bookDetailYearPublishedTextView);

        setViewItemsValues();
    }

    private void setViewItemsValues() {
        book.observe(this, b -> {
            titleView.getEditText().setText(b.getTitle());
            authorView.getEditText().setText(b.getAuthor());
            yearPublishedView.getEditText().setText(b.getYearPublished());
        });
    }

    public void handleEditBookButtonClick(View v) {
        finish();
    }
}