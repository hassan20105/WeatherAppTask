package com.example.userr.photoweatherapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.userr.photoweatherapp.Model.Dailogs;
import com.example.userr.photoweatherapp.Model.ErrorHandling;
import com.example.userr.photoweatherapp.Model.PHP_URLS;
import com.example.userr.photoweatherapp.Model.ValidationFields;
import com.example.userr.photoweatherapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_email)
    EditText emailET;
    @BindView(R.id.et_login_password)
    EditText passwordET;
    @BindView(R.id.btn_login_login)
    Button login_activity_loginBTN;
    @BindView(R.id.tv_login_createnew)
            TextView createnewTV;
    Dialog loadingDialog = null;
    private RequestQueue mRequestQueue;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        emailET.setText(getIntent().getStringExtra("email"));
        passwordET.setText(getIntent().getStringExtra("pass"));

        //testing upload

        initlize();
        setEvents();

    }


    private void initlize() {

        mRequestQueue = Volley.newRequestQueue(this);
    }

    private void setEvents() {

        createnewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        login_activity_loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    loginRequest();
                }
            }
        });
    }


    private boolean validate() {

        email = emailET.getText().toString();
        password = passwordET.getText().toString();

        if (ValidationFields.isEmpty(email)) {
            emailET.setError("Enter Email");
            return false;
        } else if (!ValidationFields.isValidEmail(email)) {
            emailET.setError("Invalid Email");
            return false;
        }
        //-------------------------------------------------
        else if (ValidationFields.isEmpty(password)) {
            passwordET.setError("Enter Password");
            return false;
        } else if (!ValidationFields.isValidPassword(password)) {
            passwordET.setError("Invalid Password");
            return false;
        }


        return true;
    }

    public JSONObject getMapData() {
        JSONObject postParam = new JSONObject();
        try {
            postParam.put("email", email);
            postParam.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postParam;
    }


    public void loginRequest() {

        loadingDialog = Dailogs.createLoadingBar(this);
        loadingDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, PHP_URLS.LOGIN_URL, getMapData(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String check = (String) response.get("check");
                    if(check.equals("Success"))
                    {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        loadingDialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "" + check, Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(LoginActivity.this, "" + ErrorHandling.getRequestError(volleyError), Toast.LENGTH_SHORT).show();

                loadingDialog.dismiss();
            }
        });
        mRequestQueue.add(request);
    }



}
