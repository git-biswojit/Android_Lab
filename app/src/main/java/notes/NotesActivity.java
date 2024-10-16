package notes;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employeeWork.R;


public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        if(savedInstanceState == null){
            NotesDisplayFragment notesDisplayFragment = new NotesDisplayFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main, notesDisplayFragment).commit();
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback( true) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0){
                    getSupportFragmentManager().popBackStack();
                }
                else {
                    finish();
                }
            }
        });
    }
}