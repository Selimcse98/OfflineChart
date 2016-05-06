package com.scu.offlinechart;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 4/28/2016.
 */
public class PatientDetail extends Fragment {
    static String ARG_PARAM1 = "name";
    static String ARG_PARAM2="param2";

    String patientName,mParam2;

    public PatientDetail(){
        //Required empty constructor
    }

    public static PatientDetail newInstance(String patientName, String param2) {
        PatientDetail fragment = new PatientDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, patientName);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientName = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_patient_detail, container, false);
        TextView name = (TextView) fragmentView.findViewById(R.id.name);
        name.setText(patientName);
        return fragmentView;
    }

    public void setData(byte []data) {
        ECGGraph ecgGraphFragment = (ECGGraph)getFragmentManager().findFragmentById(R.id.line_chart);
        ecgGraphFragment.setData(data);
        ActivityGraph actGraphFrag = (ActivityGraph)getFragmentManager().findFragmentById(R.id.line_activity_chart);
        actGraphFrag.setData(data);
        //<fragment android:id="@+id/line_activity_chart"
    }
}