package com.scu.offlinechart;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

/**
 * Created by Mohammad on 4/29/2016.
 */
public class ReadOfflineData{
    private ArrayList<Byte> data = new ArrayList<Byte>();
    public void readExteralStorage() {
        try {
//            File directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
//            File filePath = new File(directoryPath, "/" + "myFileExternal.txt");
//            FileOutputStream fos = new FileOutputStream(filePath);
//            fos.write("This is a test file written ".getBytes());
//            fos.flush();
//            fos.close();

            String line;
            File directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            File filePath = new File(directoryPath, "/" + "ecgData.txt");
            //BufferedReader br = new BufferedReader(new FileReader("/storage/emulated/0/Movies/ecgData.txt"));
            if(filePath.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                while ((line = br.readLine()) != null) {
                    System.out.println("================================= " + line + " ===================================================");
                }
                //File file = new File(Environment.getExternalStoragePublicDirectory(
                //      Environment.DIRECTORY_PICTURES), "myFileExternal.txt");
            }else {
                System.out.println("file not fount");
                Log.e("FileError", "Directory not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
