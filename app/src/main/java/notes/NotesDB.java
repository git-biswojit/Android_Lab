package notes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NotesDB {
    static NotesDB object;
    private final ArrayList<Notes> notesArrayList;

    private NotesDB(){
        notesArrayList=new ArrayList<>();
    };

    public static NotesDB getInstance(){
        if(object==null){
            return new NotesDB();
        }else{
            return object;
        }
    }

    public void addNewNote(Notes newNote){
       notesArrayList.add(newNote);
    }
    public ArrayList<Notes> getAllNotes(){
        return notesArrayList;
        //TODO: improve
    }
}
