package com.scu.offlinechart;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scu.offlinechart.ChartView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ECGGraph extends Fragment {

    View ecgView;
    public ECGGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ecgView = inflater.inflate(R.layout.fragment_ecggraph, container, false);
        return ecgView;
    }

    public void setData (byte []data) {
        ChartView chartViewCanvas = (ChartView)ecgView.findViewById(R.id.chart);
        chartViewCanvas.setData(data);
        //QRScheck qrsCheck = new QRScheck();
        //qrsCheck.setData(data);
        //System.out.println(" Heart Rate: "+(int)qrsCheck.setData(data));
    }
}