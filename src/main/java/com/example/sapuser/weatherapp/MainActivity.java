package com.example.sapuser.weatherapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
//import org.apache.http.HttpEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//import learn2crack.asynctask.library.JSONParser;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    public EditText streetVal;
    public EditText cityVal;
    public String stateVal;
    public TextView error;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button about=(Button)findViewById(R.id.buttonAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),AboutActivity2.class);
                startActivity(intent);

            }
        });

       Button search=(Button)findViewById(R.id.buttonS);
       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           
               validateForm();
           }
       });


        Button clear=(Button)findViewById(R.id.buttonC);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        
                clearForm();
            }
        });



        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();



            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forecast.io"));


                startActivity(browserIntent);

            }

        });
        }

    public void validateForm()
    {
        error=(TextView)findViewById(R.id.textViewError);
        error.setTextColor(Color.RED);
        error.setTextSize(20);


            streetVal=(EditText)findViewById(R.id.editStreetVal);
            cityVal=(EditText)findViewById(R.id.editCityVal);
            stateVal=spinner.getSelectedItem().toString();

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        final int selectoption = rg.getCheckedRadioButtonId();
        final View radiodegree = rg.findViewById(selectoption);
        final View radioop = rg.findViewById(selectoption);
        final int rid = rg.indexOfChild(radiodegree);
        final RadioButton selop = (RadioButton) dg.getChildAt(rid);
        String degVal = (String) selop.getText();
        String deg="";
        String stateAbbrv="";



        if(stateVal.equals("Alabama"))
        {
            stateAbbrv="AL";
        }
        else if(stateVal.equals("Alaska"))
        {
            stateAbbrv="AK";
        }
        else if(stateVal.equals("Arizona"))
        {
            stateAbbrv="AZ";
        }

        else if(stateVal.equals("Arkansas"))
        {
            stateAbbrv="AR";
        }

        else if(stateVal.equals("Colorado"))
        {
            stateAbbrv="CO";
        }
        else if(stateVal.equals("Connecticut"))
        {
            stateAbbrv="CT";
        }

        else if(stateVal.equals("California"))
        {
            stateAbbrv="CA";
        }
        else if(stateVal.equals("Delaware"))
        {
            stateAbbrv="DE";
        }
        else if(stateVal.equals("District of Columbia"))
        {
            stateAbbrv="DC";
        }
        else if(stateVal.equals("Florida"))
        {
            stateAbbrv="FL";
        }

        else if(stateVal.equals("Georgia"))
        {
            stateAbbrv="GA";
        }
        else if(stateVal.equals("Hawaii"))
        {
            stateAbbrv="HI";
        }
        else if(stateVal.equals("Idaho"))
        {
            stateAbbrv="ID";
        }
        else if(stateVal.equals("Illinois"))
        {
            stateAbbrv="IL";
        }
        else if(stateVal.equals("Indiana"))
        {
            stateAbbrv="IN";
        }

        else if(stateVal.equals("Iowa"))
        {
            stateAbbrv="IA";
        }

	else if(stateVal.equals("Kansas"))
        {
            stateAbbrv="KS";
        }else if(stateVal.equals("Kentucky"))
        {
            stateAbbrv="KY";
        }else if(stateVal.equals("Maine"))
        {
            stateAbbrv="ME";
        }
        else if(stateVal.equals("Maryland"))
        {
            stateAbbrv="MD";
        }
        else if(stateVal.equals("Massachusetts"))
        {
            stateAbbrv="MA";
        }
        else if(stateVal.equals("Michigan"))
        {
            stateAbbrv="MI";
        }
        else if(stateVal.equals("Minesotta"))
        {
            stateAbbrv="MN";
        }

        else if(stateVal.equals("Mississippi"))
        {
            stateAbbrv="MS";
        }
        else if(stateVal.equals("Missouri"))
        {
            stateAbbrv="MO";
        }
        else if(stateVal.equals("Montana"))
        {
            stateAbbrv="MT";
        }

        else if(stateVal.equals("Nebraska"))
        {
            stateAbbrv="NE";
        }
        else if(stateVal.equals("Nevada"))
        {
            stateAbbrv="NV";
        }
        else if(stateVal.equals("New Hampshire"))
        {
            stateAbbrv="NH";
        }
        else if(stateVal.equals("New Mexico"))
        {
            stateAbbrv="NM";
        }
        else if(stateVal.equals("New York"))
        {
            stateAbbrv="NY";
        }
        else if(stateVal.equals("North Carolina"))
        {
            stateAbbrv="NC";
        }
        else if(stateVal.equals("North Dakota"))
        {
            stateAbbrv="ND";
        }

        else if(stateVal.equals("Ohio"))
        {
            stateAbbrv="OH";
        }

        else if(stateVal.equals("Oregon"))
        {
            stateAbbrv="OR";
        }

        else if(stateVal.equals("Pennsylvania"))
        {
            stateAbbrv="PA";
        }
        else if(stateVal.equals("Rhode Island"))
        {
            stateAbbrv="RI";
        }
        else if(stateVal.equals("South Carolina"))
        {
            stateAbbrv="SC";
        }
        else if(stateVal.equals("South Dakota"))
        {
            stateAbbrv="SD";
        }

        else if(stateVal.equals("Tennessee"))
        {
            stateAbbrv="TN";
        }

        else if(stateVal.equals("Texas"))
        {
            stateAbbrv="TX";
        }

        else if(stateVal.equals("Utah"))
        {
            stateAbbrv="UT";
        }
        else if(stateVal.equals("Vermont"))
        {
            stateAbbrv="VT";
        }
        else if(stateVal.equals("Virginia"))
        {
            stateAbbrv="VA";
        }

        else if(stateVal.equals("Washington"))
        {
            stateAbbrv="WA";
        }

        else if(stateVal.equals("West Virginia"))
        {
            stateAbbrv="WV";
        }

        else if(stateVal.equals("Wisconsin"))
        {
            stateAbbrv="WI";
        }
        else if(stateVal.equals("Wyoming")) {
            stateAbbrv = "WY";

        }



        if(degVal.equals("Farenheit"))
        {
            deg="us";
        }
        else if(degVal.equals("Celsius"))
        {
            deg="on";
        }

                if(streetVal.getText().toString().equals(""))
                {

                    error.setText("Please enter a street name");
                }
                else if(cityVal.getText().toString().equals(""))
                {

                    error.setText("Please enter a city name");

                }

                else if(stateVal.equals("Select your state"))
                {

                    error.setText("Please select a state");
                }


                else {

                    error.setText("");
                    try{
                        String forecasturl="stadd="+URLEncoder.encode(streetVal.getText().toString(),"UTF-8")+"&city="+URLEncoder.encode(cityVal.getText().toString(),"UTF-8")+ "&state="+URLEncoder.encode(stateAbbrv,"UTF-8")+ "&temp="+URLEncoder.encode(deg,"UTF-8");

                        String awsurl="http://second-app.elasticbeanstalk.com/?"+forecasturl;
                        new JSONParse().execute(awsurl);

                    }
                    catch(UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

         

                }


    }


    public void clearForm()
    {
        streetVal=(EditText)findViewById(R.id.editStreetVal);
        cityVal=(EditText)findViewById(R.id.editCityVal);
        stateVal=spinner.getSelectedItem().toString();

        RadioGroup dg = (RadioGroup) findViewById(R.id.radioGroup);
        int selectoption = rg.getCheckedRadioButtonId();
        View radiodegree = rg.findViewById(selectoptioinn);
        View radiooption = rg.findViewById(selectdg);
        int rid = rg.indexOfChild(radiodegree);
        RadioButton selop = (RadioButton) rg.getChildAt(rid);
        String degVal = (String) seldg.getText();

        streetVal.setText("");
        cityVal.setText("");
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton f= (RadioButton) findViewById(R.id.radioF);
        RadioButton c = (RadioButton) findViewById(R.id.radioC);
        f.setChecked(true);
        c.setChecked(false);
        
        Spinner state = (Spinner) findViewById(R.id.spinner);
        state.setSelection(0);


    }


    private class JSONParse extends AsyncTask<String,Void,JSONObject> {





        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... params) {

            Log.d("Entered", "Entered JSONParse method");


            String response;
            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(params[0]);
                HttpResponse responce = httpClient.execute(httpget);
                HttpEntity httpEntity = responce.getEntity();
                response = EntityUtils.toString(httpEntity);
                return new JSONObject(response);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject result) {



            EditText streetVal = (EditText) findViewById(R.id.editStreetVal);
            EditText cityVal = (EditText) findViewById(R.id.editCityVal);
            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            String stateVal = spinner.getSelectedItem().toString();
            final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
            final int selectoption = rg.getCheckedRadioButtonId();
            final View radiodegree = rg.findViewById(selectoption);
            final View radioop = rg.findViewById(selectoption);
            final int rid = rg.indexOfChild(radiodegree);
            final RadioButton seldg = (RadioButton) rg.getChildAt(rid);
            String degVal = (String) selop.getText();
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("response", result.toString());
            intent.putExtra("city", cityVal.getText().toString());
            


            String latitude="";
            String longitude="";

            try {

                latitude=result.getString("latitude");
                longitude=result.getString("longitude");
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            String stateAbbrv="";

            if(stateVal.equals("Alabama"))
            {
                stateAbbrv="AL";
            }
            else if(stateVal.equals("Alaska"))
            {
                stateAbbrv="AK";
            }
            else if(stateVal.equals("Arizona"))
            {
                stateAbbrv="AZ";
            }

            else if(stateVal.equals("Arkansas"))
            {
                stateAbbrv="AR";
            }

            else if(stateVal.equals("California"))
            {
                stateAbbrv="CA";
            }
            else if(stateVal.equals("Colorado"))
            {
                stateAbbrv="CO";
            }
            else if(stateVal.equals("Connecticut"))
            {
                stateAbbrv="CT";
            }
            else if(stateVal.equals("Delaware"))
            {
                stateAbbrv="DE";
            }
            else if(stateVal.equals("District of Columbia"))
            {
                stateAbbrv="DC";
            }
            else if(stateVal.equals("Florida"))
            {
                stateAbbrv="FL";
            }

            else if(stateVal.equals("Georgia"))
            {
                stateAbbrv="GA";
            }
            else if(stateVal.equals("Hawaii"))
            {
                stateAbbrv="HI";
            }
            else if(stateVal.equals("Idaho"))
            {
                stateAbbrv="ID";
            }
            else if(stateVal.equals("Illinois"))
            {
                stateAbbrv="IL";
            }
            else if(stateVal.equals("Indiana"))
            {
                stateAbbrv="IN";
            }

            else if(stateVal.equals("Iowa"))
            {
                stateAbbrv="IA";
            }else if(stateVal.equals("Kansas"))
            {
                stateAbbrv="KS";
            }else if(stateVal.equals("Kentucky"))
            {
                stateAbbrv="KY";
            }else if(stateVal.equals("Maine"))
            {
                stateAbbrv="ME";
            }
            else if(stateVal.equals("Maryland"))
            {
                stateAbbrv="MD";
            }
            else if(stateVal.equals("Massachusetts"))
            {
                stateAbbrv="MA";
            }
            else if(stateVal.equals("Michigan"))
            {
                stateAbbrv="MI";
            }
            else if(stateVal.equals("Minessota"))
            {
                stateAbbrv="MN";
            }

            else if(stateVal.equals("Mississippi"))
            {
                stateAbbrv="MS";
            }
            else if(stateVal.equals("Missouri"))
            {
                stateAbbrv="MO";
            }
            else if(stateVal.equals("Montana"))
            {
                stateAbbrv="MT";
            }

            else if(stateVal.equals("Nebraska"))
            {
                stateAbbrv="NE";
            }
            else if(stateVal.equals("Nevada"))
            {
                stateAbbrv="NV";
            }
            else if(stateVal.equals("New Hampshire"))
            {
                stateAbbrv="NH";
            }
            else if(stateVal.equals("New Mexico"))
            {
                stateAbbrv="NM";
            }
            else if(stateVal.equals("New York"))
            {
                stateAbbrv="NY";
            }
            else if(stateVal.equals("North Carolina"))
            {
                stateAbbrv="NC";
            }
            else if(stateVal.equals("North Dakota"))
            {
                stateAbbrv="ND";
            }

            else if(stateVal.equals("Ohio"))
            {
                stateAbbrv="OH";
            }

            else if(stateVal.equals("Oregon"))
            {
                stateAbbrv="OR";
            }

            else if(stateVal.equals("Pennsylvania"))
            {
                stateAbbrv="PA";
            }
            else if(stateVal.equals("Rhode Island"))
            {
                stateAbbrv="RI";
            }
            else if(stateVal.equals("South Carolina"))
            {
                stateAbbrv="SC";
            }
            else if(stateVal.equals("South Dakota"))
            {
                stateAbbrv="SD";
            }

            else if(stateVal.equals("Tennessee"))
            {
                stateAbbrv="TN";
            }

            else if(stateVal.equals("Texas"))
            {
                stateAbbrv="TX";
            }

            else if(stateVal.equals("Utah"))
            {
                stateAbbrv="UT";
            }
            else if(stateVal.equals("Vermont"))
            {
                stateAbbrv="VT";
            }
            else if(stateVal.equals("Virginia"))
            {
                stateAbbrv="VA";
            }

            else if(stateVal.equals("Washington"))
            {
                stateAbbrv="WA";
            }

            else if(stateVal.equals("West Virginia"))
            {
                stateAbbrv="WV";
            }

            else if(stateVal.equals("Wisconsin"))
            {
                stateAbbrv="WI";
            }
            else if(stateVal.equals("Wyoming")) {
                stateAbbrv = "WY";

            }

            intent.putExtra("state", stateAbbrv);
            Log.d("State", stateVal.toString());

            intent.putExtra("street", streetVal.toString());
            Log.d("street", streetVal.getText().toString());

            intent.putExtra("degree", degVal.toString());
            startActivity(intent);



        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      
        int id = item.getItemId();

  
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



