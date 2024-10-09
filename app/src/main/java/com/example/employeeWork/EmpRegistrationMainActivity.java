package com.example.employeeWork;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onenightbeforeexam.R;

public class EmpRegistrationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EmpRegistrationPageOneFragment empRegistrationPageOneFragment = new EmpRegistrationPageOneFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout,empRegistrationPageOneFragment)
                .commit();
}
}