package notes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import com.example.employeeWork.R;

public class NotesDisplayFragment extends Fragment implements NoteItemInterface {

    NotesDB notesDataBase;
    NotesActivity notesActivity;
    ExtendedFloatingActionButton addNewNoteBtn;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        notesActivity=(NotesActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_notes,container, false);
        init(view);
        addNewNoteBtn.setOnClickListener(v->notesActivity.loadFragment(new NotesFragment()));
        RecyclerView recyclerView = view.findViewById(R.id.rclnotes);
        NoteAdapter noteAdapter = new NoteAdapter(this);
        noteAdapter.setNotesArrayList(notesDataBase.getAllNotes());
        recyclerView.setAdapter(noteAdapter);
        return  view;
    }

    private void init(View view) {
        addNewNoteBtn = view.findViewById(R.id.addNewNoteBtn);
        notesDataBase = NotesDB.getInstance();
    }
//    private void onNewClick(){
//        NotesFragment notesFragment = new NotesFragment(notesDataBase);
//        getParentFragmentManager().beginTransaction().replace(R.id.main, notesFragment).addToBackStack(null).commit();
//    }

    @Override
    public void onItemClickListner(Notes note) {
        Toast.makeText(getContext(), note.getNoteTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClickListner(Notes note) {
        Toast.makeText(getContext(), note.getNoteTitle(),Toast.LENGTH_SHORT).show();
    }

//    private ArrayList<Notes> getNotes (){
//        ArrayList<Notes> notesArrayList = new ArrayList<>();
//        notesArrayList.add(new Notes("T1","D1"));
//        notesArrayList.add(new Notes("T2","D2"));
//        notesArrayList.add(new Notes("T3","D3"));
//        return notesArrayList;
//    }
}