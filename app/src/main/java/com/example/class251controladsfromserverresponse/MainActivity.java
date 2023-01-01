package com.example.class251controladsfromserverresponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    AdView mAdView;
    public static boolean SHOW_AD = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            progressBar = findViewById(R.id.progressBar);
            mAdView=findViewById(R.id.adView);
            // initializing  banner ad

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                }
            });




                 // Sending request to server using volley

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://testbdraian.000webhostapp.com/apps/hello.php";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressBar.setVisibility(View.GONE);
                            // Display the first 500 characters of the response string.
//                            textView.setText("Response is: " + response.substring(0,500));
                           if (response.contains("showAd")){
                               SHOW_AD=true;
                                mAdView.setVisibility(View.VISIBLE);
                               AdRequest adRequest = new AdRequest.Builder().build();
                               mAdView.loadAd(adRequest);

                           }else{
                               mAdView.setVisibility(View.GONE);
                               SHOW_AD=false;
                                 }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
//                    textView.setText("That didn't work!");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
}