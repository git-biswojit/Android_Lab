package notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onenightbeforeexam.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;


public class NotesDetailsFragment extends Fragment {

    Button next;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public NotesDetailsFragment() {
        // Required empty public constructor
    }

    public static NotesDetailsFragment newInstance(String param1, String param2) {
        NotesDetailsFragment fragment = new NotesDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notes_detailsfragment, container, false);
        ExtendedFloatingActionButton fabAddnew = view.findViewById(R.id.fabAddNewButton);
        init(view);
        fabAddnew.setOnClickListener(v -> onNewClick());

        RecyclerView recyclerView = view.findViewById(R.id.rclnotes);
        NoteAdapter noteAdapter = new NoteAdapter();
        noteAdapter.setNotesArrayList(getNotes());
        recyclerView.setAdapter(noteAdapter);



        return view;
    }

    private void init(View view) {
        next = view.findViewById(R.id.fabAddNewButton);
    }
    private void onNewClick(){
        NotesFragment notesFragment = new NotesFragment();
        getParentFragmentManager().beginTransaction().replace(R.id.main, notesFragment).addToBackStack(null).commit();
    }

    private ArrayList<Notes> getNotes (){
        ArrayList<Notes> notesArrayList = new ArrayList<>();
        notesArrayList.add(new Notes("T1","D1"));
        notesArrayList.add(new Notes("T2","D2"));
        notesArrayList.add(new Notes("T3","D3"));
        return notesArrayList;
    }
}