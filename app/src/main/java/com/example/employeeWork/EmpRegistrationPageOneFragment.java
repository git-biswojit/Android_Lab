package com.example.employeeWork;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.onenightbeforeexam.R;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import android.Manifest;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EmpRegistrationPageOneFragment extends Fragment {
    Employee employee;

    //in init method initialise all views
    TextInputLayout wrpFirstName, wrpLastName, wrpEmail;
    RadioGroup rgGender;
    Button ageBtn, nextBtn;
    CheckBox chkHobby1, chkHobby2, chkHobby3;
    Spinner spnCountry;
    ImageView profilePicture;
    int age;
    private static final int CAMERA_PERMISSION_CODE = 100;


    //NEW APPROACH RECOMMENDED BY CHATGPT
    private static final String PERMISSION_NAME = Manifest.permission.CAMERA;
    private ActivityResultLauncher<String> permissionLauncher;


    public EmpRegistrationPageOneFragment() {
        // Required empty public constructor
    }
    public EmpRegistrationPageOneFragment(Employee employee){
        this.employee=employee;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if(isGranted){
                        launchCamera();
                    }else{
                        Toast.makeText(getContext(),"Camera Permission Not Granted",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emp_reg_pg1, container, false);
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
        profilePicture = view.findViewById(R.id.profilePicture);
    }

    private void clickListner(){
        onTextChangeListner(wrpFirstName);
        onTextChangeListner(wrpLastName);
        onTextChangeListner(wrpEmail);
        ageBtn.setOnClickListener(v -> showDatePickerAndGetAge());
        nextBtn.setOnClickListener(v -> handleData());
        profilePicture.setOnClickListener(v -> getProfilePicture());
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

        EmpRegistrationPageTwoFragment empRegistrationPageTwoFragment = new EmpRegistrationPageTwoFragment(e);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout,empRegistrationPageTwoFragment).commit();

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

    public void checkCameraPermission(){
        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
//            ActivityCompat.requestPermissions(
//                    getActivity(), new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE
//            );
            permissionLauncher.launch(Manifest.permission.CAMERA);

        }else{
            Toast.makeText(getActivity(),"Permission already granted",Toast.LENGTH_SHORT).show();
            launchCamera();
        }
    }

    private void getProfilePicture(){
        checkCameraPermission();
    }
    private void launchCamera(){
        Intent newPictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(newPictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            Toast.makeText(getContext(), "Helo Melo", Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(getContext(), "Helo Melo Jelo", Toast.LENGTH_SHORT);
        }
    }


}