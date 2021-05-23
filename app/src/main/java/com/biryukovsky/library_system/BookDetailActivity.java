package com.biryukovsky.library_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.viewmodels.BookViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class BookDetailActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private int bookId;
    private LiveData<Book> book;

    private boolean isGalleryPermitted;

    private TextInputLayout titleView;
    private TextInputLayout authorView;
    private TextInputLayout yearPublishedView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(BookViewModel.class);

        isGalleryPermitted = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        Intent intent = getIntent();
        bookId = intent.getExtras().getInt(Constants.EXTRA_BOOK_ID);
        book = bookViewModel.get(bookId);

        titleView = findViewById(R.id.bookDetailTitleTextView);
        authorView = findViewById(R.id.bookDetailAuthorTextView);
        yearPublishedView = findViewById(R.id.bookDetailYearPublishedTextView);
        imageView = findViewById(R.id.bookDetailImageView);

        setViewItemsValues();
    }

    private void setViewItemsValues() {
        book.observe(this, b -> {
            titleView.getEditText().setText(b.getTitle());
            authorView.getEditText().setText(b.getAuthor());
            yearPublishedView.getEditText().setText(b.getYearPublished());

            try {
                Uri uri = Uri.parse(Uri.decode(b.getImagePath()));
                if (uri != null && isGalleryPermitted) {
                    imageView.setImageURI(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public void handleEditBookButtonClick(View v) {
        finish();
    }
}