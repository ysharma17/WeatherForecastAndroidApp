package com.example.sapuser.weatherapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

    public class HourData extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] datatimes;
        private final Integer[] dataicons;
        private final String[] datatemps;

        public HourData(Activity context, String[] timeVal, Integer[] icon, String[] temp) {
            super(context, R.layout.hourslist, timeVal);
            this.context=context;
            this.datatimes=timeVal;
            this.dataicons=icon;
            this.datatemps=temp;
        }

        public View getView(int pos,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.hourslist, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.timestext);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.summaryicon);
            TextView txtTemp = (TextView) rowView.findViewById(R.id.tempstext);

       

            txtTitle.setText(datatimes[pos]);
            imageView.setImageResource(dataicons[pos]);
            txtTemp.setText(datatemps[pos]);
            return rowV;

        }
    }

