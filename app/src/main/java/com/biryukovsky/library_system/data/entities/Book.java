package com.biryukovsky.library_system.data.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "author")
    public String author;

    @Nullable
    @ColumnInfo(name = "year_published")
    public String yearPublished;

    @Nullable
    @ColumnInfo(name = "image_path")
    public String imagePath;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getYearPublished() {
        return yearPublished;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Book(
            String title,
            String author,
            @Nullable String imagePath,
            @Nullable String yearPublished
    ) {
        this.title = title;
        this.author = author;
        this.imagePath = imagePath;
        this.yearPublished = yearPublished;
    }
}
