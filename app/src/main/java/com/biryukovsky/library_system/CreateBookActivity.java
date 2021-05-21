package com.biryukovsky.library_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class CreateBookActivity extends AppCompatActivity {

    public static final String TAG = "CreateBookActivity";
    public static final String EXTRA_BOOK_DATA = "com.biryukovsky.library_system.booklistsql.BOOK_DATA";
    public static final int LOAD_PHOTO_REQUEST = 1;

    private TextInputLayout titleView;
    private TextInputLayout authorView;
    private TextInputLayout yearPublishedView;
    private ImageView imageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);
    }

    @Override
    protected void onStart() {
        super.onStart();

        titleView = findViewById(R.id.bookTitleTextInput);
        authorView = findViewById(R.id.bookAuthorTextInput);
        yearPublishedView = findViewById(R.id.bookYearPublishedTextInput);
        imageView = findViewById(R.id.bookImageView);
    }

    public void handleAddBookButtonClick(View view) {

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
        bookData.put("imagePath", imageUri.toString());
        intent.putExtra(EXTRA_BOOK_DATA, bookData);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void handleLoadFileButtonClick(View view) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, LOAD_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOAD_PHOTO_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}