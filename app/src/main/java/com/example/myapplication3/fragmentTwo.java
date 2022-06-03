package com.example.myapplication3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication3.R;

import org.json.JSONException;
import org.json.JSONObject;


public class fragmentTwo extends Fragment {

    EditText email;
    EditText password;
    String Email;
    String Password;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);

        email = v.findViewById(R.id.userEmail);
        password = v.findViewById(R.id.userPassword);
        btn = v.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = email.getText().toString();
                Password = password.getText().toString();
                login(v);

                Log.e("", email.getText().toString());

            }
        });
        return v;


    }

    public void login(View view) {

        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "qwe", Toast.LENGTH_LONG).show();
            сreateDialog(getActivity(), "Все поля должны быть заполнены.");
        } else {
            if (!checkEmail(email.getText().toString())) {
                сreateDialog(getActivity(), "Почта не соответствует паттерну \"name@domenname.ru\".");
            } else {
                userLogin();
            }
        }
    }

    public void userLogin() {

//        String email = findViewById(R.id.userEmail)).getText().toString();
//        String password = ((TextView)findViewById(R.id.userPassword)).getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("email", Email);
            json.put("password", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String baseUrl = "https://food.madskill.ru/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, baseUrl, json,
                response -> {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                },
                error -> сreateDialog(getActivity(), "Не удалось авторизироваться:\n" + error.getMessage()));
        requestQueue.add(request);
    }


    private boolean checkEmail(String email) {
        if (email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})$")) {
            return true;
        } else {
            return false;
        }
    }

    public void сreateDialog(Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle("Error")
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
}