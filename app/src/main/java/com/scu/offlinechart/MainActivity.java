package com.scu.offlinechart;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Byte> data = new ArrayList<Byte>();
    final int LIST_SIZE = 1080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PatientDetail patientDetail = PatientDetail.newInstance("Patient 1", "");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.patient_container, patientDetail);
        transaction.commit();

        try {
            String line;
            File directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            File filePath = new File(directoryPath, "/" + "ecgData.txt"); ///storage/emulated/0/Movies/ecgData.txt
            byte byteValues[];
            //BufferedReader br = new BufferedReader(new FileReader("/storage/emulated/0/Movies/ecgData.txt"));
            if(filePath.exists()) {
                data = new ArrayList<>(LIST_SIZE);
                String str;
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                while ((line = br.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(line,",");
                    while (tokens.hasMoreTokens()) {
                        str = tokens.nextToken();
                        //System.out.println(str);
                        if (str.length() > 1) {
                            if(data.size() == LIST_SIZE)
                                data.remove(0);
                            data.add(Byte.parseByte(str));
                        }
                    }

                    byteValues= new byte[data.size()];
                    for (int i =0;i<data.size();i++) {
                        //System.out.println("ECG: " + data.get(i));
                        byteValues[i]=data.get(i);
                    }

//                    for (int i =0; i<data.size();i++)
//                        System.out.println("Byte values: "+i+"  "+byteValues[i]);
                    patientDetail.setData(byteValues);
                    Thread.sleep(1000);
                }
                //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "myFileExternal.txt");
            }else {
                System.out.println("file not fount");
                Log.e("FileError", "Directory not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}