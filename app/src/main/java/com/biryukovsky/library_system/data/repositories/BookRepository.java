package com.biryukovsky.library_system.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.biryukovsky.library_system.data.dao.BookDao;
import com.biryukovsky.library_system.data.entities.Book;
import com.biryukovsky.library_system.data.source.LibraryDatabase;

import java.util.List;

public class BookRepository {
    private final BookDao bookDao;

    public BookRepository(Application application) {
        LibraryDatabase db = LibraryDatabase.getDatabase(application);
        bookDao = db.bookDao();
    }

    public LiveData<List<Book>> getAllBooks() {
        return bookDao.getAll();
    }

    public LiveData<Book> get(int id) {
        return bookDao.get(id);
    }

    public void insert(Book book) {
        LibraryDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.insert(book);
        });
    }

    public void delete(Book book) {
        LibraryDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.delete(book);
        });
    }
}
