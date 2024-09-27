package com.example.onenightbeforeexam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDataFragment extends Fragment {

    TextView empNameTV, empEmailTV, empAgeTV, empGenderTV, empHobbiesTV,
            empCountryTV, empExperienceTV, empQualificationTV, empExpSalaryTV, empPrevCompaniesTV;

    Employee employee;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayDataFragment() {
        // Required empty public constructor
    }
    public DisplayDataFragment(Employee employee) {
        // Required empty public constructor
        this.employee= employee;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDataFragment newInstance(String param1, String param2) {
        DisplayDataFragment fragment = new DisplayDataFragment();
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
        View view= inflater.inflate(R.layout.fragment_display_data, container, false);
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