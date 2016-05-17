package com.scu.offlinechart;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ECGGraph extends Fragment {

    View ecgView;
    public ECGGraph() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ecgView = inflater.inflate(R.layout.fragment_ecggraph, container, false);
        return ecgView;
    }

    public void setData (byte []data) {
        for(int i =0;i<data.length;i++)
            System.out.println("Data at ECGGraph"+i+" "+data[i]);
        ChartView chartViewCanvas = (ChartView)ecgView.findViewById(R.id.chart);
        if(chartViewCanvas!=null)
            chartViewCanvas.setData(data);
//        //QRScheck qrsCheck = new QRScheck();
        //qrsCheck.setData(data);
        //System.out.println(" Heart Rate: "+(int)qrsCheck.setData(data));
    }
}