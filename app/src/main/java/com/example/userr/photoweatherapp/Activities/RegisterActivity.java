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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.userr.photoweatherapp.Model.Dailogs;
import com.example.userr.photoweatherapp.Model.ValidationFields;
import com.example.userr.photoweatherapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.btn_register_register)
    Button registerBTN;
    @BindView(R.id.et_register_email)
    EditText emailET;
    @BindView(R.id.et_register_password)
    EditText passwordET;
    @BindView(R.id.et_reguster_confirmpassword)
    EditText confirmPasswordET;
    @BindView(R.id.et_register_phone)
    EditText phoneET;
    String email, password, confirmPassword, phone;
    Dialog loadingDialog = null;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRequestQueue = Volley.newRequestQueue(this);
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://hassancs.000webhostapp.com/index.php";
                callData();

                if (validate()) {
                    registerRequest(url);
                }


            }
        });

    }

    public boolean validate() {
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
        //----------------------------------------------------
        else if (ValidationFields.isEmpty(confirmPassword)) {
            confirmPasswordET.setError("Enter Confirmation Password");
            return false;
        } else if (!ValidationFields.isValidConfirmPassword(confirmPassword, password)) {
            confirmPasswordET.setError("Password doesnt match");
            return false;
        }
        //---------------------------------------------------------
        else if (ValidationFields.isEmpty(phone)) {
            phoneET.setError("Enter Phone");
            return false;
        } else if (!ValidationFields.isPhoneValid(phone)) {
            phoneET.setError("Phone Invalid");
            return false;
        }

        return true;
    }

    public void callData() {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();
        phone = phoneET.getText().toString();
    }

    public Map<String, String> getMapData() {
        callData();
        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("email", email);
        postParam.put("password", password);
        postParam.put("confirmPassword", confirmPassword);
        postParam.put("phone", phone);
        return postParam;
    }

    public void registerRequest(String url) {
        loadingDialog = Dailogs.createLoadingBar(this);
        loadingDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(getMapData()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String dbStatus = (String) response.get("DBStatus");
                    if (dbStatus.equals("Data Inserted")) {
                        Toast.makeText(RegisterActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("pass", password);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error Happened", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }

}
