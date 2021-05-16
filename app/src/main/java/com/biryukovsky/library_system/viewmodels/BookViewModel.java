package com.biryukovsky.library_system.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.data.repositories.BookRepository;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private final BookRepository bookRepository;

    public BookViewModel(Application application) {
        super(application);
        bookRepository = new BookRepository(application);
    }

    public LiveData<List<Book>> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void insert(Book book) {
        bookRepository.insert(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public LiveData<Book> get(int id) {
        return bookRepository.get(id);
    }
}
