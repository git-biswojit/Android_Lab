package notes;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.onenightbeforeexam.R;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(savedInstanceState == null){
            NotesDetailsFragment notesDetailsFragmentFragement = new NotesDetailsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main, notesDetailsFragmentFragement).commit();
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