package notes;

import java.io.Serializable;

public class Notes implements Serializable {
        private String noteTitle;
        private String noteDescription;

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

        public String getNoteTitle() {
                return noteTitle;
        }

        @Override
        public String toString() {
                return "Notes{ " + noteTitle  +"\\"+ noteDescription +"}";
        }

        public void setNoteTitle(String noteTitle) {
                this.noteTitle = noteTitle;
        }
}
