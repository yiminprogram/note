package com.example.androidtutorial;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * implementation 'com.android.volley:volley:1.2.1'
 * <user-permission android:name="android.permission.INTERNET"/>
 */

public class VolleyTransmit extends AppCompatActivity
{
    private Button btn, btn2, btnImage, btnJson;
    private TextView text;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_transmit);

        btn = findViewById(R.id.btn_volley);
        btn2 = findViewById(R.id.btn_volley2);
        btnImage = findViewById(R.id.btn_volley3);
        btnJson = findViewById(R.id.btn_volley4);
        text = findViewById(R.id.text_view_volley);
        imageView = findViewById(R.id.volley_image);

        btn.setOnClickListener(v ->
        {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "http://.......",
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            text.setText(response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Log.d("apple", error.toString());
                        }
                    }
            );
            MainApp.queue.add(request);
        });

        btn2.setOnClickListener(v ->
        {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "json url",
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            parseJson(response);
                        }
                    }, null
            );
            MainApp.queue.add(request);
        });

        btnImage.setOnClickListener(v ->
        {
            ImageRequest request = new ImageRequest(
                    "image url",
                    new Response.Listener<Bitmap>()
                    {
                        @Override
                        public void onResponse(Bitmap response)
                        {
                            imageView.setImageBitmap(response);
                        }
                    }
                    , 0
                    , 0
                    , Bitmap.Config.ARGB_8888
                    , null
            );

            MainApp.queue.add(request);
        });

        btnJson.setOnClickListener(v ->
        {
            JsonArrayRequest request = new JsonArrayRequest(
                    "json url",
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            parseJsonArray(response);
                        }
                    }
                    , null
            );
            MainApp.queue.add(request);
        });
    }

    private void parseJson(String jsonString)
    {
        try
        {
            JSONArray root = new JSONArray(jsonString);
            for (int i = 0; i < root.length(); i++)
            {
                JSONObject row = root.getJSONObject(i);
                String name = row.getString("Name");
                String email = row.getString("Email");

                text.append("name : " + name + ", email : " + email + "\n");
            }
        }
        catch (Exception e)
        {
            Log.d("apple", e.toString());
        }
    }

    private void parseJsonArray(JSONArray root)
    {
        try
        {
            for (int i = 0; i < root.length(); i++)
            {
                JSONObject row = root.getJSONObject(i);
                String name = row.getString("Name");
                String email = row.getString("Email");

                text.append("name : " + name + ", email : " + email + "\n");
            }
        }
        catch (Exception e)
        {
            Log.d("apple", e.toString());
        }
    }
}
