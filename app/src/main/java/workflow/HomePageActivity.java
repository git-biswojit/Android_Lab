package workflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.employeeWork.EmpRegistrationMainActivity;
import com.example.employeeWork.R;

import notes.NotesActivity;

public class HomePageActivity extends AppCompatActivity {

    Button empActivityButton, notesActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        init();
        empActivityButton.setOnClickListener(v->switchToEmpActivity());
        notesActivityButton.setOnClickListener(v->switchToNotesActivity());
    }


    private void init(){
        empActivityButton = findViewById(R.id.empActivitySwitch);
        notesActivityButton = findViewById(R.id.notesActivitySwitch);
    }

    private void switchToEmpActivity(){
        Intent empActivity = new Intent(this, EmpRegistrationMainActivity.class);
        startActivity(empActivity);

    }
    private void switchToNotesActivity(){
        Intent notesActivity = new Intent(this, NotesActivity.class);
        startActivity(notesActivity);
    }
}