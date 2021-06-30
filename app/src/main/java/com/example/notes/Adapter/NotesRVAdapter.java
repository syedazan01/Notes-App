package com.example.notes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Model.Notes;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {
    private ArrayList<Notes> allNotes = new ArrayList<>();
    private INotesRVAdapter iNotesRVAdapter;

    public NotesRVAdapter(INotesRVAdapter iNotesRVAdapter) {
        this.iNotesRVAdapter = iNotesRVAdapter;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Notes currentNote = allNotes.get(position);
        holder.tv_notes.setText(currentNote.getText());
        holder.deleteButton.setOnClickListener(view -> iNotesRVAdapter.onItemClicked(currentNote));


    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void updateNotes(List<Notes> updateNotes) {
        allNotes.clear();
        allNotes.addAll(updateNotes);

        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notes;
        ImageView deleteButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_notes = itemView.findViewById(R.id.tv_text);
            deleteButton = itemView.findViewById(R.id.iv_delete);
        }
    }

   public interface INotesRVAdapter {
        void onItemClicked(Notes note);
    }
}

