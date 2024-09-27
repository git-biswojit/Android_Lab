package com.example.onenightbeforeexam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page1_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page1_Fragment extends Fragment {
    Employee employee;

    //in init method initialise all views
    TextInputLayout wrpFirstName, wrpLastName, wrpEmail;
    RadioGroup rgGender;
    Button ageBtn, nextBtn;
    CheckBox chkHobby1, chkHobby2, chkHobby3;
    Spinner spnCountry;
    int age;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Page1_Fragment() {
        // Required empty public constructor
    }
    public Page1_Fragment(Employee employee){
        this.employee=employee;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page1_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Page1_Fragment newInstance(String param1, String param2) {
        Page1_Fragment fragment = new Page1_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page1_, container, false);
        init(view);
        clickListner();
        return view;
    }


    private void init(View view){
        wrpFirstName = view.findViewById(R.id.fNameText);
        wrpLastName = view.findViewById(R.id.lNameText);
        wrpEmail = view.findViewById(R.id.emailText);
        rgGender = view.findViewById(R.id.genderRG);
        ageBtn = view.findViewById(R.id.ageBtn);
        nextBtn = view.findViewById(R.id.nextBtn);
        chkHobby1 = view.findViewById(R.id.hobby1Chk);
        chkHobby2 = view.findViewById(R.id.hobby2Chk);
        chkHobby3 = view.findViewById(R.id.hobby3Chk);
        spnCountry = view.findViewById(R.id.conutrySpnr);
    }

    private void clickListner(){
        onTextChangeListner(wrpFirstName);
        onTextChangeListner(wrpLastName);
        onTextChangeListner(wrpEmail);
        ageBtn.setOnClickListener(v -> showDatePickerAndGetAge());
        nextBtn.setOnClickListener(v -> handleData());
    }

    private void handleData(){
        String firstName, lastName, email,
                gender, hobbies, country;

        firstName = Objects.requireNonNull(wrpFirstName.getEditText()).getText().toString();
        lastName = Objects.requireNonNull(wrpLastName.getEditText()).getText().toString();
        email = Objects.requireNonNull(wrpEmail.getEditText()).getText().toString();

        int rBtnChk = rgGender.getCheckedRadioButtonId();
        if(rBtnChk==R.id.genderFemaleRB){
            gender="Female";
        }else {
            gender="Male";
        }
        StringBuilder hobby = new StringBuilder();
        if(chkHobby1.isChecked()){
            hobby.append(chkHobby1.getText().toString());
        }if(chkHobby2.isChecked()){
            hobby.append(chkHobby2.getText().toString());
        }if(chkHobby3.isChecked()){
            hobby.append(chkHobby3.getText().toString());
        }
        hobbies=hobby.toString();
        country=spnCountry.getSelectedItem().toString();

        Employee e = new Employee();
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setEmail(email);
        e.setGender(gender);
        e.setAge(age);
        e.setHobbies(hobbies);
        e.setCountry(country);
        System.out.println(e);

        Page2_Fragment page2Fragment = new Page2_Fragment(e);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout,page2Fragment).commit();

    }

    private void onTextChangeListner(TextInputLayout textInputLayout){
        Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
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

    private void showDatePickerAndGetAge(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        age = getAge(year,month,dayOfMonth);
                    }
                },year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }


    private int getAge(int year,int month,int dayOfMonth){
        return Period.between(LocalDate.of(year,month,dayOfMonth),LocalDate.now()).getYears();
    }
}