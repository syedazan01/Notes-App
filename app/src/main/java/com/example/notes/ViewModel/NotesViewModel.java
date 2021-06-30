package com.example.notes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notes.Model.Notes;
import com.example.notes.Repostiory.NoteRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NoteRepository mNoteRepository;
    private final LiveData<List<Notes>> mAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        mNoteRepository = new NoteRepository(application);
        mAllNotes = mNoteRepository.getAllNotes();
    }

   public LiveData<List<Notes>> getAllNotes(){
        return mAllNotes;
    }

    public void insert(Notes notes){
        mNoteRepository.insert(notes);
    }

    public void delete(Notes notes){
        mNoteRepository.delete(notes);
    }
}
