package com.example.admin.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    ClassVariables CV = new ClassVariables();
    public static final Profile prof = new Profile();



    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private EditText editTextVID;
    private EditText editTextSID;
    private Button buttonLogin;

    private String vid;
    private String sid;
    private String nm;
    private String ph;
    private String ad;

    public static String alertMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextVID = (EditText) findViewById(R.id.editText);
        editTextSID = (EditText) findViewById(R.id.editText2);

        buttonLogin = (Button) findViewById(R.id.buttonReq);

        assert buttonLogin != null;
        buttonLogin.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    private void userLogin() {
        vid = editTextVID.getText().toString().trim();
        sid = editTextSID.getText().toString().trim();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(vid.equals("") && sid.equals("")){
            Toast.makeText(LoginActivity.this, "Enter Both details!" , Toast.LENGTH_LONG).show();
        }

        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, CV.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) throws JSONException {
                            if(response != "failure") {
                                Toast.makeText(LoginActivity.this, "Logged In!!!", Toast.LENGTH_LONG).show();
                                //Toast.makeText(LoginActivity.this, response.toString().trim(), Toast.LENGTH_LONG).show();
                                openProfile(response);
                                alert();
                            }
                                else{
                                Toast.makeText(LoginActivity.this, "Login Failed!!!", Toast.LENGTH_LONG).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(CV.KEY_V_ID, vid);
                    map.put(CV.KEY_S_ID, sid);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            editor.putString(CV.KEY_V_ID, vid);
            editor.putString(CV.KEY_S_ID, sid);
            editor.commit();
        }
    }

    private void openProfile(String response) throws JSONException {

        JSONObject jsonobj = new JSONObject(response);
        String id = jsonobj.getString("result");
        JSONObject jsonobj2 = new JSONObject(id);

        String vehicle;
        String sensor;
        String naam;
        String mobile;
        String add;

        vehicle = jsonobj2.getString("0");
        vid = vehicle.toString();

        sensor = jsonobj2.getString("1");
        sid = sensor.toString();

        naam = jsonobj2.getString("2");
        nm = naam.toString();

        mobile = jsonobj2.getString("4");
        ph = mobile.toString();

        add = jsonobj2.getString("3");
        ad = add.toString();

        Toast.makeText(LoginActivity.this, "Hi!!!", Toast.LENGTH_LONG).show();

        prof.Vehicle = vid;
        prof.Sensor = sid;
        prof.Name = nm;
        prof.Phone = ph;
        prof.Address = ad;


        Intent i = new Intent(LoginActivity.this, Welcome.class);
       /* i.putExtra(CV.KEY_V_ID, vid);
        i.putExtra(CV.KEY_S_ID, sid);
        i.putExtra(CV.KEY_NAME, nm);
        i.putExtra(CV.KEY_PHONE, ph);
        i.putExtra(CV.KEY_ADDRESS, ad);*/
        startActivity(i);

        /*
        Bundle bundle = new Bundle();
        bundle.putString(CV.KEY_V_ID, vid);
        bundle.putString(CV.KEY_S_ID, sid);
        bundle.putString(CV.KEY_NAME, nm);
        bundle.putString(CV.KEY_PHONE, ph);
        bundle.putString(CV.KEY_ADDRESS, ad);

        Welcome.fragment_obj3.setArguments(bundle);
        */


    }

    public void alert()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CV.ALERT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) throws JSONException {
                            extract(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(CV.KEY_V_ID, vid);
                map.put(CV.KEY_CO, LoginActivity.prof.CO_DATA);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void extract(String response) throws JSONException {
        JSONObject jsonObj = new JSONObject(response);
        JSONArray jArray = jsonObj.getJSONArray("result");
        JSONObject jObj = jArray.getJSONObject(0);

        alertMessage = jObj.getString("0");
        //Toast.makeText(LoginActivity.this,alertMessage.toString().trim(), Toast.LENGTH_SHORT).show();

        LoginActivity.prof.Data = alertMessage;

    }
    @Override
    public void onClick(View v) {
        if (v==buttonLogin) {
            userLogin();
        }
    }

}

