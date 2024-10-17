package notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DBManager{

    private final Context context;

    private NoteDatabaseHelper noteDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context){
        this.context=context;
    }

    public void openDB() throws SQLException {
        noteDatabaseHelper = new NoteDatabaseHelper(context);
        sqLiteDatabase = noteDatabaseHelper.getWritableDatabase();
    }

    public void closeDB(){
        noteDatabaseHelper.close();
    }
}
