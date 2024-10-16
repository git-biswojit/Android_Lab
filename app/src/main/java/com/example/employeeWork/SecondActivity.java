package com.example.employeeWork;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    TextInputLayout wrpQualification, wrpExperience, wrpExpectedSalary;
    CheckBox tcChk;
    Button submitButton;
    LinearLayout linearLayout;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        init();
        clickListners();
    }

    private void init(){
        wrpQualification = findViewById(R.id.qualificationText);
        wrpExperience = findViewById(R.id.experienceText);
        wrpExpectedSalary = findViewById(R.id.expectedSalaryText);
        tcChk = findViewById(R.id.tcChk);
        submitButton = findViewById(R.id.submitBtn);
        linearLayout = findViewById(R.id.linearLayout);
        employee = (Employee) getIntent().getSerializableExtra("Employee");
    }

    private void clickListners(){
        textChangeListner(wrpExperience);
        textChangeListner(wrpQualification);
        textChangeListner(wrpExpectedSalary);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tcChk.isChecked()){
                    handleData();
//                    Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
//                    intent.putExtra("Employee",employee);
//                    startActivity(intent);
                }else{
                    Toast.makeText(SecondActivity.this,"Please accept the Terms and Conditions",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void textChangeListner(TextInputLayout textInputLayout){
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(textInputLayout.getEditText().getText().toString().isEmpty()){
                    textInputLayout.setError("Please enter valid "+textInputLayout.getHint());
                }else{
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void handleData(){
        String qualification,
                prevCompanies;
        int experienceYear;
        float expectedSalary;

        qualification=getText(wrpQualification);
        expectedSalary=Float.parseFloat(getText(wrpExpectedSalary)+"0");
        experienceYear=Integer.parseInt(getText(wrpExperience)+"0");
        employee.setQualification(qualification);
//        employee.setPrevCompanies();
        employee.setExperienceYear(experienceYear);
        employee.setExpectedSalary(expectedSalary);
        System.out.println(employee);
    }

    private String getText(TextInputLayout textInputLayout){
        return Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
    }


}