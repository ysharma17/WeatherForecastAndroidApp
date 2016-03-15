package com.example.sapuser.weatherapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WeekData extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] dD;
    private final Integer[] icon;
    private final String[] minMaxTemp;
    private final Integer[] colorarray;

    public WeekData(Activity context, String[] daysdates, Integer[] iconsval, String[] minmaxtemp, Integer[] colorarray) {
        super(context, R.layout.weekslist, daysdates);
        this.context=context;
        this.dD=daysdates;
        this.icon=iconsval;
        this.minMaxTemp=minmaxtemp;
        this.colorarray=colorarray;



    }

    public View getView(int pos,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.weekslist, null, true);

        TextView txtDayDate = (TextView) rowView.findViewById(R.id.daydatetextval);
        ImageView imgSummaryIcon = (ImageView) rowView.findViewById(R.id.imageicon);
        TextView txtMinMaxTemp = (TextView) rowView.findViewById(R.id.minmaxtext);
        RelativeLayout colorl=(RelativeLayout)rowView.findViewById(R.id.colorlayout);


        if(colorarray[pos]==0)
        {
            colorl.setBackgroundColor(Color.parseColor("#FEDA69"));
        }

        else if(colorarray[pos]==1)
        {
            colorl.setBackgroundColor(Color.parseColor("#A0E7FF"));
        }

        else if(colorarray[pos]==2)
        {
            colorl.setBackgroundColor(Color.parseColor("#FFC4EA"));
        }

        else if(colorarray[pos]==3)
        {
            colorl.setBackgroundColor(Color.parseColor("#C4FFA5"));
        }

        if(colorarray[pos]==4)
        {
            colorl.setBackgroundColor(Color.parseColor("#FFBDB7"));
        }

        if(colorarray[pos]==5)
        {
            colorl.setBackgroundColor(Color.parseColor("#EFFFB5"));
        }

        if(colorarray[pos]==6)
        {
            colorl.setBackgroundColor(Color.parseColor("#BCBEFF"));
        }



        txtDayDate.setText(dD[pos]);
        imgSummaryIcon.setImageResource(icon[position]);
        txtMinMaxTemp.setText(minMaxTemp[position]);




        return rowV;

    }
}