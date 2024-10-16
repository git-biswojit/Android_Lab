package com.example.employeeWork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DisplayEmpDataFragment extends Fragment {

    TextView empNameTV, empEmailTV, empAgeTV, empGenderTV, empHobbiesTV,
            empCountryTV, empExperienceTV, empQualificationTV, empExpSalaryTV, empPrevCompaniesTV;

    Employee employee;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public DisplayEmpDataFragment() {
        // Required empty public constructor
    }
    public DisplayEmpDataFragment(Employee employee) {
        // Required empty public constructor
        this.employee= employee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_display_emp_data, container, false);
        init(view);
        getAndSetData();
        return view;
    }



    private void init(View view){
        empNameTV = view.findViewById(R.id.empNameTV);
        empEmailTV = view.findViewById(R.id.empEmailTV);
        empAgeTV = view.findViewById(R.id.empAgeTV);
        empGenderTV = view.findViewById(R.id.empGenderTV);
        empHobbiesTV = view.findViewById(R.id.empHobbiesTV);
        empCountryTV = view.findViewById(R.id.empCountryTV);
        empExperienceTV = view.findViewById(R.id.empExperienceTV);
        empQualificationTV = view.findViewById(R.id.empQualificationTV);
        empExpSalaryTV = view.findViewById(R.id.empExpSalaryTV);
        empPrevCompaniesTV = view.findViewById(R.id.empPrevCompaniesTV);
    }

    private void getAndSetData(){
        String fullName = employee.getFirstName()+" "+employee.getLastName();
        empNameTV.setText(fullName);
        empEmailTV.setText(employee.getEmail());
        empAgeTV.setText(employee.getAge().toString()); //to strings are very important
        empGenderTV.setText(employee.getGender());
        empHobbiesTV.setText(employee.getHobbies());
        empCountryTV.setText(employee.getCountry());
        empExpSalaryTV.setText(employee.getExpectedSalary().toString());
        empExperienceTV.setText(employee.getExperienceYear().toString());
        empQualificationTV.setText(employee.getQualification());
        empPrevCompaniesTV.setText(employee.getPrevCompanies());
    }
}