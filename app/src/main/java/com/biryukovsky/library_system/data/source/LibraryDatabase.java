package com.biryukovsky.library_system.data.source;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.biryukovsky.library_system.data.dao.BookDao;
import com.biryukovsky.library_system.data.entities.Book;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Room Database that contains the Book table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = {Book.class}, version = 1, exportSchema = true)
public abstract class LibraryDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static volatile LibraryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LibraryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LibraryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "library")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
