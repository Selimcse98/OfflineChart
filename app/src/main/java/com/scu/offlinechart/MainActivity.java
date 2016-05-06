package com.scu.offlinechart;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReadOfflineData readOfflineData = new ReadOfflineData();
        readOfflineData.readExteralStorage();

        PatientDetail patientDetail = PatientDetail.newInstance("Patient 1", "");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.patient_container, patientDetail);
        transaction.commit();
    }
}
