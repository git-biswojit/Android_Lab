package notes;

import java.io.Serializable;

public class Notes implements Serializable {
        private String noteTitle;
        private String noteDescription;
        public static final String TABLE_NAME = "notes";
        public static final String C_NOTE_ID="noteID";
        public static final String C_NOTE_TITLE="noteTitle";
        public static final String C_NOTE_DESC="noteDesc";

        public static final String SQL_CREATE_ENTITY="CREATE TABLE "+TABLE_NAME
                                        +" ( "+C_NOTE_ID+" INTEGER PRIMARY KEY, "
                                        +C_NOTE_TITLE +" TEXT, "
                                        +C_NOTE_DESC  + " TEXT );";

        public static final String SQL_DELETE_ENTITY="DROP TABLE IF EXISTS "+TABLE_NAME;



        public Notes(String noteTitle, String noteDescription) {
                this.noteDescription = noteDescription;
                this.noteTitle = noteTitle;
        }

        public Notes(){}


        public String getNoteDescription() {
                return noteDescription;
        }

        public void setNoteDescription(String noteDescription) {
                this.noteDescription = noteDescription;
        }

        public void setNoteTitle(String noteTitle) {
                this.noteTitle = noteTitle;
        }

        public String getNoteTitle() {
                return noteTitle;
        }

        @Override
        public String toString() {
                return "Notes{ " + noteTitle  +"\\"+ noteDescription +"}";
        }



}
