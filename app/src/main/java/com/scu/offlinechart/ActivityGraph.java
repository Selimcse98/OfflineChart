package com.scu.offlinechart;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scu.offlinechart.ActivityGraphCanvas;

/**
 * Created by kamrulislam on 7/03/2016.
 */
public class ActivityGraph extends Fragment {
    View activityView;
    public ActivityGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activityView = inflater.inflate(R.layout.fragment_activitygraph, container, false);
        return activityView;
    }

    public void setData (byte []data) {
        ActivityGraphCanvas activityGraphCanvas = (ActivityGraphCanvas)activityView.findViewById(R.id.activity_chart);
        activityGraphCanvas.setData(data);
    }
}