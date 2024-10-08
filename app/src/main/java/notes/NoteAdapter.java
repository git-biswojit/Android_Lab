package notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onenightbeforeexam.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private ArrayList<Notes> notesArrayList = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_notes,parent,false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = notesArrayList.get(position);
        holder.tvTitle.setText(note.getNoteTitle());
        holder.tvDescription.setText(note.getNoteDescription());
    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public void setNotesArrayList(ArrayList<Notes> notesArrayList){
        this.notesArrayList.clear();
        this.notesArrayList.addAll(notesArrayList);
        notifyDataSetChanged();
    }
}
