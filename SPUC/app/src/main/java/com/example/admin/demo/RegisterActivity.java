package com.example.admin.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ClassVariables CV = new ClassVariables();


    private EditText editTextVID;
    private EditText editTextSID;
    private EditText editTextNAME;
    private EditText editTextPHONE;
    private EditText editTextADDRESS;

    private Button buttonRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextVID = (EditText) findViewById(R.id.editText3);
        editTextSID= (EditText) findViewById(R.id.editText4);
        editTextNAME = (EditText) findViewById(R.id.editText5);
        editTextPHONE = (EditText) findViewById(R.id.editText6);
        editTextADDRESS = (EditText) findViewById(R.id.editText8);


        buttonRegister = (Button) findViewById(R.id.regButton);

        buttonRegister.setOnClickListener(this);

    }

    private void registerUser() throws NoSuchAlgorithmException {
        final String vid = editTextVID.getText().toString().trim();
        final String sid = editTextSID.getText().toString().trim();
        final String name = editTextNAME.getText().toString().trim();
        final String phone = editTextPHONE.getText().toString().trim();
        final String address = editTextADDRESS.getText().toString().trim();

        //VOLLEY
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CV.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(!response.toString().trim().equals("Could not register")){
                            Toast.makeText(RegisterActivity.this, response.trim() + "\nPlease Login now", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(RegisterActivity.this, response.toString().trim(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(CV.KEY_V_ID,vid);
                params.put(CV.KEY_S_ID, sid);
                params.put(CV.KEY_NAME,name);
                params.put(CV.KEY_PHONE, phone);
                params.put(CV.KEY_ADDRESS,address);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v==buttonRegister) {
            try {
                registerUser();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
