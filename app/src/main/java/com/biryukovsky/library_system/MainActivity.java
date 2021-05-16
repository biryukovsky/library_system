package com.biryukovsky.library_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.biryukovsky.library_system.adapters.BookListAdapter;
import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.viewmodels.BookViewModel;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.book_recycler_view);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(this, adapter::submitList);
    }

    public void startCreateBookActivity(View view) {
        Intent intent = new Intent(this, CreateBookActivity.class);
        startActivityForResult(intent, Constants.NEW_BOOK_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            HashMap<String, String> bookData = (HashMap<String, String>) data.getSerializableExtra(CreateBookActivity.EXTRA_BOOK_DATA);
            Book book = new Book(bookData.get("title"), bookData.get("author"));
            bookViewModel.insert(book);
        }
    }
}
