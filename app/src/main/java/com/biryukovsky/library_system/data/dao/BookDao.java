package com.biryukovsky.library_system.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.biryukovsky.library_system.data.entities.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book ORDER BY id ASC")
    LiveData<List<Book>> getAll();

    @Query("SELECT * FROM book WHERE id = :id")
    LiveData<Book> get(int id);

    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);
}
