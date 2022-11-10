package com.example.architectureexample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architectureexample.Model.Note;
import com.example.architectureexample.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> noteList;
    private OnItemClickListener listener;

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = noteList.get(position);
        holder.text_view_title.setText(currentNote.getTitle());
        holder.text_view_description.setText(currentNote.getDescription());
        holder.text_view_priority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return noteList.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView text_view_title;
        private TextView text_view_description;
        private TextView text_view_priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_title = itemView.findViewById(R.id.text_view_title);
            text_view_description = itemView.findViewById(R.id.text_view_description);
            text_view_priority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(noteList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
