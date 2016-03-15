package com.example.sapuser.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import android.app.Activity;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;


public class DetailsActivity extends Activity {

    ArrayList<String> next24arr = new ArrayList<String>();
    ArrayList<Integer> summaryArr = new ArrayList<Integer>();
    ArrayList<String> tempArray = new ArrayList<String>();
    String[] dattime;
    Integer[] datsummary;
    String[] dattemp;

 
    ArrayList<String> daysDates = new ArrayList<String>();
    ArrayList<String> minMaxTemps = new ArrayList<String>();
    ArrayList<Integer> icons = new ArrayList<Integer>();
    String[] daysDateArray;
    String[] minMaxTempArray;
    Integer[] iconArray;
    Integer[] colorarray;


    ListView list;

    //Extra data
    String city;
    String degree;
    String state;

 
    LinearLayout layoutnext24arr;
    LinearLayout layoutnext7;

   
    TextView textdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       layoutnext24arr = (LinearLayout) findViewById(R.id.layoutNext24);
      layoutnext7= (LinearLayout) findViewById(R.id.LayoutNext7);
      layoutnext7.setVisibility(View.GONE);

        Bundle b = getIntent().getExtras();
        String response = b.getString("response");
        city = b.getString("city");
        degree = b.getString("degree");
        state = b.getString("state");


        //set More details text by using city name
     textdetails = (TextView) findViewById(R.id.MoreDetailsHeader);
     textdetails.setText("More details for " + city + ", " + state);
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject hourly = obj.getJSONObject("hourly");
            JSONArray hourlydata = hourly.getJSONArray("data");




            //JSONObject dailyD = data.getJSONObject(0);
            //Integer sunriseTime = Integer.parseInt(dailyData.getString("sunriseTime"));


            String offset = obj.getString("offset");
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa"); 
            TimeZone tz = TimeZone.getTimeZone("GMT" + offset);



            int timeVal;
            String summaryicon;
            int summaryID;
            int temperature;
            String tempV = "";
          
            for (int i = 0; i < 24; i++) {

                timeVal = Integer.parseInt(hourlydata.getJSONObject(i).getString("time"));

                Date date = new Date(timeVal * 1000L);

                sdf.format(date);

                Calendar cal = Calendar.getInstance(tz);
                cal.setTime(date);
                sdf.setTimeZone(tz);
                String timeValues = sdf.format(cal.getTime());

                Log.d("time", timeValues);


                summaryicon = hourlydata.getJSONObject(i).getString("icon");
                summaryID = getImageID(summaryicon);
                Log.d("summary", String.valueOf(summaryicon));

                temperature = hourlydata.getJSONObject(i).getInt("temperature");
                tempVal = String.valueOf(temperature);
                Log.d("temperature", tempVal);

                next24arr.add(i, timeValues);
                summaryArr.add(i, summaryID);
                tempArray.add(i, tempVal);
            }

            next24arr.add(24, "      ");
            summaryArr.add(24, R.drawable.plusicon);
            tempArray.add(24, "  ");



            //convert to array to display in list view
            dattime = next24arr.toArray(new String[next24arr.size()]);
            datsummary = summaryArr.toArray(new Integer[summaryArr.size()]);
            dattemp = tempArray.toArray(new String[tempArray.size()]);

            //timeVal



            JSONObject daily=obj.getJSONObject("daily");
            JSONArray dailyd=daily.getJSONArray("data");
            int mintemperature;
            int maxtemperature;
            int colors;
            String mint="";
            String maxt="";
            ArrayList<Integer> colorind = new ArrayList<Integer>();
          
                for (int i = 0; i < 7; i++) {

                    timeVal = Integer.parseInt(dailyd.getJSONObject(i).getString("time"));

                    Date date = new Date(timeVal * 1000L);

                    SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE,MMM dd");

                    sdf1.format(date);

                    Calendar cal = Calendar.getInstance(tz);
                    cal.setTime(date);
                    sdf1.setTimeZone(tz);
                    String dateValues = sdf1.format(cal.getTime());

                    mintemperature = dailyd.getJSONObject(i).getInt("temperatureMin");
                    mint = formatTemp(String.valueOf(mintemperature));

                    maxtemperature=dailydata.getJSONObject(i).getInt("temperatureMax");
                    maxt=formatTemp(String.valueOf(maxtemperature));


                    summaryicon = hourlydata.getJSONObject(i).getString("icon");
                    summaryid = getImageid(summaryicon);
                    Log.d("summary", String.valueOf(summaryicon));

                    colors=i;

                //add to arraylist
                daysDates.add(i, dateValues);
                icons.add(i, summaryID);
                minMaxTemps.add(i, "Min:" + mint + "" + " | Max: " + maxt);
                colorindex.add(i);
            }

            //convert to array to display in list view
            daysDateArray = daysDates.toArray(new String[daysDates.size()]);
            iconArray = icons.toArray(new Integer[icons.size()]);
            minMaxTempArray = minMaxTemps.toArray(new String[minMaxTemps.size()]);
           
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView temptext = (TextView) findViewById(R.id.temptext);
        temptext.setText(formatTemp("Temp"));

        //Setting adapter for next 24 hours tab
        HourData adOne = new HourData(this, dattime, datsummary, dattemp);
        list = (ListView) findViewById(R.id.listNext24);
        list.setAdapter(adOne);

        //Setting adapter for next 7 days tab
        WeekData adTwo = new WeekData(this, daysDateArray, iconArray, minMaxTempArray,colorarray);
        list = (ListView) findViewById(R.id.listNext7);
        list.setAdapter(adTwo);




        //set onClick Listener for the two buttons - next 24 hours and next 7 days
        findViewById(R.id.Next24btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                layoutnext7.setVisibility(View.GONE);
               layoutnext24arr.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.Next7btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               layoutnext24arr.setVisibility(View.GONE);
                layoutnext7.setVisibility(View.VISIBLE);
            }
        });

    }
   
    public String formatTemp(String tempArray) {
        if (degree.equals("Farenheit")) return tempArray + "(°F)";
        else return tempArray + "(°C)";
    }

  
    public static Integer getImageID(String icon) {
        if (icon.equals("clear-day"))
            return R.drawable.clear;
        if (icon.equals("clear-night"))
            return R.drawable.clear_night;
        if (icon.equals("rain"))
            return R.drawable.rain;
        if (icon.equals("snow"))
            return R.drawable.snow;
        if (icon.equals("sleet"))
            return R.drawable.sleet;
        if (icon.equals("wind"))
            return R.drawable.wind;
        if (icon.equals("fog"))
            return R.drawable.fog;
        if (icon.equals("cloudy"))
            return R.drawable.cloudy;
        if (icon.equals("partly-cloudy-day"))
            return R.drawable.cloud_day;
        else
            return R.drawable.cloud_night;
    }
}

