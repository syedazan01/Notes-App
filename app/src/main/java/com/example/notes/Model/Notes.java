package com.example.notes.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text_in_notes")
    @NonNull
    private String mText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Notes(@NonNull String mText) {
        this.mText = mText;
    }


    public String getText() {
        return mText;
    }

}
