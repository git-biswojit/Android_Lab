package notes;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import com.example.employeeWork.R;

public class NotesDisplayFragment extends Fragment {

    Button next;
    NotesDB notesDataBase;
    public NotesDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_notes, container, false);
        ExtendedFloatingActionButton addNewNoteBtn = view.findViewById(R.id.addNewNoteBtn);
        init(view);
        addNewNoteBtn.setOnClickListener(v -> onNewClick());

        RecyclerView recyclerView = view.findViewById(R.id.rclnotes);
        NoteAdapter noteAdapter = new NoteAdapter();
        noteAdapter.setNotesArrayList(notesDataBase.getAllNotes());
        recyclerView.setAdapter(noteAdapter);
        return view;
    }

    private void init(View view) {
        next = view.findViewById(R.id.addNewNoteBtn);
        notesDataBase = NotesDB.getInstance();
    }
    private void onNewClick(){
        NotesFragment notesFragment = new NotesFragment(notesDataBase);
        getParentFragmentManager().beginTransaction().replace(R.id.main, notesFragment).addToBackStack(null).commit();
    }

//    private ArrayList<Notes> getNotes (){
//        ArrayList<Notes> notesArrayList = new ArrayList<>();
//        notesArrayList.add(new Notes("T1","D1"));
//        notesArrayList.add(new Notes("T2","D2"));
//        notesArrayList.add(new Notes("T3","D3"));
//        return notesArrayList;
//    }
}