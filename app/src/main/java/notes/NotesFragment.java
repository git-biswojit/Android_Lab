package notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.employeeWork.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class NotesFragment extends Fragment {

    NotesDB noteDataBase;
    Button saveNotesBtn;
    TextInputLayout noteTitleET, noteDescriptionET;
    public NotesFragment() {

    }

    public NotesFragment(NotesDB noteDataBase){
        this.noteDataBase = noteDataBase;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        init(view);
        saveNotesBtn.setOnClickListener(v -> handleSaveBtnClick());
        return view;
    }
    private void init(View view){
        saveNotesBtn = view.findViewById(R.id.saveBtn);
        noteTitleET = view.findViewById(R.id.item_notes_heading);
        noteDescriptionET = view.findViewById(R.id.item_notes_desc);
    }

    private void handleSaveBtnClick(){
        fetchAndSaveNotesToDatabase();

        Toast.makeText(getContext(),testMethodGetAllNotes(),Toast.LENGTH_LONG).show();
        getParentFragmentManager().popBackStack();
    }

    private String testMethodGetAllNotes(){
        StringBuilder notesStrings = new StringBuilder();
        noteDataBase.getAllNotes().forEach(note -> notesStrings.append(note.toString()));
        return notesStrings.toString();
    }

    private void fetchAndSaveNotesToDatabase(){
        String noteTitle =  Objects.requireNonNull(noteTitleET.getEditText()).getText().toString();
        String noteDesc = Objects.requireNonNull(noteDescriptionET.getEditText()).getText().toString();
        Notes newNote = new Notes(noteTitle, noteDesc);
        noteDataBase.addNewNote(newNote);
    }
}