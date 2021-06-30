package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.Adapter.NotesRVAdapter;
import com.example.notes.Model.Notes;
import com.example.notes.ViewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity implements NotesRVAdapter.INotesRVAdapter {
    RecyclerView recyclerView;
    NotesRVAdapter mNotesRVAdapter = new NotesRVAdapter(this);
    private NotesViewModel mNotesViewModel;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_notesInput);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mNotesRVAdapter);

        mNotesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        mNotesViewModel.getAllNotes().observe(this,notes -> mNotesRVAdapter.updateNotes(notes));
    }

    @Override
    public void onItemClicked(Notes note) {
        mNotesViewModel.delete(note);
        Toast.makeText(this, note.getText()+"Deleted", Toast.LENGTH_SHORT).show();
    }

    public void submitData(View view) {
        String notesText = editText.getText().toString();
        if(! notesText.isEmpty()){
            mNotesViewModel.insert(new Notes(notesText));
            Toast.makeText(this, notesText+"Inserted", Toast.LENGTH_SHORT).show();
        }
    }
}