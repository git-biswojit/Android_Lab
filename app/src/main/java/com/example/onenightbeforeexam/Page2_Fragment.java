package com.example.onenightbeforeexam;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page2_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page2_Fragment extends Fragment {

    TextInputLayout wrpQualification, wrpExperience, wrpExpectedSalary;
    CheckBox tcChk;
    Button submitButton;
    LinearLayout linearLayout;
    Employee employee;
    int companyCount=0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Page2_Fragment() {
        // Required empty public constructor
    }

    public Page2_Fragment(Employee employee){
        this.employee=employee;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page2_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Page2_Fragment newInstance(String param1, String param2) {
        Page2_Fragment fragment = new Page2_Fragment();
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
        View view= inflater.inflate(R.layout.fragment_page2_, container, false);
        init(view);
        clickListners();
        addNewCompany();

        return view;
    }

    private void init(View view){
        wrpQualification = view.findViewById(R.id.qualificationText);
        wrpExperience = view.findViewById(R.id.experienceText);
        wrpExpectedSalary = view.findViewById(R.id.expectedSalaryText);
        tcChk = view.findViewById(R.id.tcChk);
        submitButton = view.findViewById(R.id.submitBtn);
        linearLayout = view.findViewById(R.id.linearLayout);
//        employee = (Employee) view.getIntent().getSerializableExtra("Employee");
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
                    DisplayDataFragment displayDataFragment = new DisplayDataFragment(employee);
                    getParentFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,displayDataFragment).commit();
                }else{
                    Toast.makeText(getContext(),"Please accept the Terms and Conditions",Toast.LENGTH_SHORT).show();
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
        prevCompanies=getAllCompanyName();
        employee.setQualification(qualification);
        employee.setPrevCompanies(prevCompanies);
        employee.setExperienceYear(experienceYear);
        employee.setExpectedSalary(expectedSalary);
        System.out.println(employee);
    }

    private String getText(TextInputLayout textInputLayout){
        return Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
    }

    private void addNewCompany(){
        View company = getLayoutInflater().inflate(R.layout.company_element,linearLayout,false);
        ImageView addBtn = company.findViewById(R.id.addCompanyBtn);
        ImageView rmvBtn = company.findViewById(R.id.rmvCompanyBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCompany();
            }
        });

        rmvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(company);
                companyCount--;
                updateItemVisibility();
            }
        });

        linearLayout.addView(company);
        companyCount++;
        updateItemVisibility();
    }

    private void updateItemVisibility(){
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View company = getLayoutInflater().inflate(R.layout.company_element,linearLayout,false);
            ImageView addBtn = company.findViewById(R.id.addCompanyBtn);
            ImageView rmvBtn = company.findViewById(R.id.rmvCompanyBtn);
            addBtn.setVisibility(View.GONE);
            rmvBtn.setVisibility(View.VISIBLE);
        }
        if(companyCount>0){
            View company = linearLayout.getChildAt(linearLayout.getChildCount()-1);
            ImageView addBtn = company.findViewById(R.id.addCompanyBtn);
            addBtn.setVisibility(View.VISIBLE);
        }
        if(companyCount==1){
            View company = linearLayout.getChildAt(linearLayout.getChildCount()-1);
            ImageView rmvBtn = company.findViewById(R.id.rmvCompanyBtn);
            rmvBtn.setVisibility(View.GONE);
        }
    }

    private String getAllCompanyName(){
        StringBuilder companies = new StringBuilder();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextInputLayout editText = linearLayout.getChildAt(i).findViewById(R.id.prevCompanyName);
            String companyName = Objects.requireNonNull(Objects.requireNonNull(((TextInputLayout) editText).getEditText()).getText()).toString();
            if(!companyName.isEmpty()){
                companies.append(companyName);
            }
        }
        return companies.toString();
    }
}