package com.example.sapuser.weatherapp;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ResultActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        //Facebook content





        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
      
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {


                    //onSuccess
                    public void onSuccess(Sharer.Result result) {


                        Toast.makeText(getApplicationContext(), "Facebook post successful", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "Facebook post successful",
     //                           Toast.LENGTH_LONG).show();


                       // Log.v("MyApp", "Share success!"); //Showed if I press the share or the cancel button

                    }

            @Override
            public void onCancel() {

                //Toast.makeText(getBaseContext(), "Post cancelled", Toast.LENGTH_LONG).show();
               Toast.makeText(getApplicationContext(), "Post cancelled", Toast.LENGTH_LONG).show();
                //Log.v("MyApp", "Share canceled"); //Only showed when I press the close button
            }

            @Override
            public void onError(FacebookException e) {


                Toast.makeText(getApplicationContext(), "Error while posting",Toast.LENGTH_LONG).show();

                //Toast.makeText(getBaseContext(), "Error while posting", Toast.LENGTH_LONG).show();

               // Log.v("MyApp","Share error: " + e.toString());
            }
                });


            //onCreate




        //Facebook create

        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String city = bundle.getString("city").toString();
        Log.d("city", city);

        String response = bundle.getString("response");

        Log.d("response", response.toString());

        String state = bundle.getString("state").toString();
        String street = bundle.getString("street").toString();
        String degree = bundle.getString("degree").toString();

        String lat=bundle.getString("latitude");
        String lng=bundle.getString("longitude");


        Log.d("degree", degree);


        try {

            JSONObject obj = new JSONObject(response);
            JSONObject currently=obj.getJSONObject("currently");



            ImageView image=(ImageView)findViewById(R.id.WeatherImage);

            String icons=currently.getString("icons").toString();
            String imageicons="";


            if(icons.equals("clear-day"))
            {
                
                image.setImageResource(R.drawable.clear);
                imageicons="";
            }

            else if(icons.equals("clear-night"))
            {
                
                image.setImageResource(R.drawable.clear_night);
            }
            else if(icons.equals("rain"))
            {
                
                image.setImageResource(R.drawable.rain);
            }
            else if(icons.equals("snow"))
            {
                
                image.setImageResource(R.drawable.snow);
            }
            else if(icons.equals("sleet"))
            {
                
                image.setImageResource(R.drawable.sleet);
            }
            else if(icons.equals("wind"))
            {
                
                image.setImageResource(R.drawable.wind);
            }
            else if(icons.equals("fog"))
            {
                
                image.setImageResource(R.drawable.fog);
            }

            else if(icons.equals("cloudy"))
            {
                
                image.setImageResource(R.drawable.cloudy);
            }
            else if(icons.equals("partly-cloudy-day"))
            {
                
                image.setImageResource(R.drawable.cloud_day);
            }
            else if(icons.equals("partly-cloudy-night"))
            {
                
                image.setImageResource(R.drawable.cloud_night);
            }



            String summary=currently.getString("summary").toString();
            

            String weatherDetails=summary+" in "+city+", "+state;
            
            TextView wd=(TextView)findViewById(R.id.WeatherDetails);
            wd.setText(weatherDetails);

            TextView sunrisetime=(TextView)findViewById(R.id.SunriseVal);
            TextView sunsettime=(TextView)findViewById(R.id.SunsetVal);


            JSONObject daily=obj.getJSONObject("daily");
            JSONArray data=daily.getJSONArray("data");
            Log.d("data array",data.toString());

            JSONObject dailyData = data.getJSONObject(0);
            int temp= currently.getInt("temperature");
            String tempv="";

            if(degree.equals("Farenheit"))
            {
                tempv=temp+"°F";
                ;
            }
            else if(degree.equals("Celsius"))
            {
                tempv=temp+"°C";

            }

            TextView tempd=(TextView)findViewById(R.id.TempDetails);
            tempd.setText(tempv);


            String windSpd=currently.getString("windSpeed");
            double winspd=Double.parseDouble(windSpd);
            String ws="";

            if(degree.equals("Farenheit"))
            {
                ws=String.format("%.2f", winspd)+" "+"mph";

            }
            else
            {
                ws=String.format("%.2f", winspd)+" "+"m/s";
            }
            TextView windspeed=(TextView)findViewById(R.id.WindSpeedVal);
            windspeed.setText(ws);

            String dewPoint=currently.getString("dewPoint");

            double dew=Double.parseDouble(dewPoint);
            String dewp="";

            if(degree.equals("Farenheit"))
            {
                dewp=String.format("%.2f", dew)+"°F";

            }
            else
            {
                dewp=String.format("%.2f", dew)+"°C";
            }
            TextView dwp=(TextView)findViewById(R.id.DewPointVal);
            dwp.setText(dewp);




            String visibility=currently.getString("visibility");
            double visib=Double.parseDouble(visibility);
            String v="";
            if(degree.equals("Farenheit"))
            {
                v=String.format("%.2f",visib )+" "+"mi";

            }
            else
            {
                v=String.format("%.2f", visib)+" "+"km";
            }
            TextView vis=(TextView)findViewById(R.id.VisibilityVal);
            vis.setText(v);

            int rain=currently.getInt("precipProbability");
            TextView chanceRain=(TextView)findViewById(R.id.ChanceOfRainVal);
            String displayRain=(rain*100)+"%";
            chanceRain.setText(displayRain);


            int lowTemp= dailyData.getInt("temperatureMin");

            int highTemp=dailyData.getInt("temperatureMax");
            String lowhigh="";

            if(degree.equals("Farenheit"))
            {
                lowhigh="L:"+lowTemp+"°F|H:"+highTemp+"°F";

            } else if(degree.equals("Celsius"))
            {
                lowhigh="L:"+lowTemp+"°C|H:"+highTemp+"°C";

            }


            TextView tempdetails=(TextView)findViewById(R.id.LowHighTempDetails);
            tempdetails.setText(lowhigh);

            Integer sunriseTime = Integer.parseInt(dailyData.getString("sunriseTime"));

            Integer sunsetTime = Integer.parseInt(dailyData.getString("sunriseTime"));

            String offset = obj.getString("offset");
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa"); // the format of your date
            TimeZone tz = TimeZone.getTimeZone("GMT" + offset);


            Date date = new Date(sunriseTime*1000L);
            Date date1 = new Date(sunsetTime*1000L);
            sdf.format(date);
            sdf.format(date1);
            Calendar cal = Calendar.getInstance(tz);
            cal.setTime(date);
            sdf.setTimeZone(tz);
            String fdate = sdf.format(cal.getTime());
            cal.setTime(date1);
            String fdate1 = sdf.format(cal.getTime());
            TextView rise=(TextView)findViewById(R.id.SunriseVal);
            rise.setText(fdate);

            TextView set=(TextView)findViewById(R.id.SunsetVal);
            set.setText(fdate1);


            int humidity=currently.getInt("humidity");
            String displayHumidity=(humidity*100)+"%";
            TextView hum=(TextView)findViewById(R.id.HumidityVal);
            hum.setText(displayHumidity);

            double precipitation=currently.getDouble("precipIntensity");

            String displayPrecip="";
            if(precipitation>=0 && precipitation<0.002)
                displayPrecip="None";//
            else if(precipitation>=0.002 && precipitation<0.017)
                displayPrecip="Very Light";
            else if(precipitation>=0.017 && precipitation<0.1)
                displayPrecip="Light";
            else if (precipitation>=0.1 && precipitation<0.4)
                displayPrecip="Moderate";
            else if (precipitation>=0.4)
                displayPrecip="Heavy";

            TextView precipI=(TextView)findViewById(R.id.PrecipVal);
            precipI.setText(displayPrecip);


        }

        catch (JSONException e) {
            e.printStackTrace();
        }

        final Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);


        intent.putExtra("response", response.toString());
        intent.putExtra("city", city);
        intent.putExtra("state", state);
        intent.putExtra("street", street);
        intent.putExtra("degree", degree);

        final Intent intent1=new Intent(ResultActivity.this,MapActivity.class);


        intent1.putExtra("response", response.toString());
        intent1.putExtra("city", city);
        intent1.putExtra("state", state);
        intent1.putExtra("street", street);
        intent1.putExtra("degree", degree);
        intent1.putExtra("lat",lat);
        intent1.putExtra("lng",lng);


        Button hoursDetails=(Button)findViewById(R.id.MoreDetailsBtn);
        hoursDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }

        });

        Button map=(Button)findViewById(R.id.ViewMapBtn);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent1);

            }

        });



        ImageView facebook = (ImageView) findViewById(R.id.fbImage);

        facebook.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

// open the desired page

                Log.d("Clicked","Entered click function");
                facebookActivity();



            }

        });


    }


    //Implement Facebook function
    public void facebookActivity()
    {
        Log.d("Entered","Entered facebookActivity");

        Bundle bundle = getIntent().getExtras();
        String response=bundle.getString("response");
        String degree=bundle.getString("degree").toString();
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject currently = obj.getJSONObject("currently");
            JSONObject daily=obj.getJSONObject("daily");
            JSONArray data=daily.getJSONArray("data");
            Log.d("data array",data.toString());

            JSONObject dailyData = data.getJSONObject(0);
            int temp= currently.getInt("temperature");
            String tempv="";

            if(degree.equals("Farenheit"))
            {
                tempv=temp+"°F";

            }
            else if(degree.equals("Celsius"))
            {
                tempv=temp+"°C";

            }

            String city = bundle.getString("city").toString();
            Log.d("city", city);

            String state = bundle.getString("state").toString();

            String summary = currently.getString("summary").toString();
            Log.d("sum", summary);

            String contentTitle = "Current weather in " + city + ", " + state;
            String contentDesc=summary+", "+tempv;


            String icons=currently.getString("icons").toString();
            String imageicons="";

            String image="";

            if(icons.equals("clear-day"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/clear.png";
                imageicons="";
            }

            else if(icons.equals("clear-night"))
            {
                
                imageicons="http://cs-server.usc.edu:45678/hw/hw8/images/clear_night.png";
                image="http://cs-server.usc.edu:45678/hw/hw8/images/clear_night.png";
            }
            else if(icons.equals("rain"))
            {

                image="http://cs-server.usc.edu:45678/hw/hw8/images/rain.png";
            }
            else if(icons.equals("snow"))
            {
 		image="http://cs-server.usc.edu:45678/hw/hw8/images/snow.png";
            }
            else if(icons.equals("sleet"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/snow.png";
            }
            else if(icons.equals("wind"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/wind.png";
            }
            else if(icons.equals("fog"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/fog.png";
            }

            else if(icons.equals("cloudy"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/cloudy.png";
            }
            else if(icons.equals("partly-cloudy-day"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/cloud_day.png";
            }
            else if(icons.equals("partly-cloudy-night"))
            {
                
                image="http://cs-server.usc.edu:45678/hw/hw8/images/cloud_night.png";
            }

            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(this);


            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(contentTitle)
                        .setContentDescription(
                                contentDesc)
                        .setImageUrl(Uri.parse(image))
                        .setContentUrl(Uri.parse("http://forecast.io"))
                        .build();

                shareDialog.show(linkContent);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        }


//Implement facebook function

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }







}