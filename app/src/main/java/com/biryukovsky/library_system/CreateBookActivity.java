package com.biryukovsky.library_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class CreateBookActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_DATA = "com.biryukovsky.library_system.booklistsql.BOOK_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);
    }

    public void handleAddBookButtonClick(View view) {
        TextInputLayout titleView = findViewById(R.id.bookTitleTextInput);
        TextInputLayout authorView = findViewById(R.id.bookAuthorTextInput);
        TextInputLayout yearPublishedView = findViewById(R.id.bookYearPublishedTextInput);

        Intent intent = new Intent();
//        TODO: proper validation
//        if (TextUtils.isEmpty(titleView.getEditText().getText())) {
//            setResult(RESULT_CANCELED, intent);
//        } else {
//            String title = titleView.getEditText().getText().toString();
//            intent.putExtra(EXTRA_BOOK_DATA, title);
//            setResult(RESULT_OK, intent);
//        }

        HashMap<String, String> bookData = new HashMap<String, String>();
        bookData.put("title", titleView.getEditText().getText().toString());
        bookData.put("author", authorView.getEditText().getText().toString());
        bookData.put("yearPublished", yearPublishedView.getEditText().getText().toString());
        intent.putExtra(EXTRA_BOOK_DATA, bookData);
        setResult(RESULT_OK, intent);
        finish();
    }
}