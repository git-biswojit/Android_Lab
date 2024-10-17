package notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NoteDatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "notes.db";
    static final int DB_VERSION = 1;

    public NoteDatabaseHelper(Context context) {
        super(context, DB_NAME, null , DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Notes.SQL_CREATE_ENTITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Notes.SQL_DELETE_ENTITY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
