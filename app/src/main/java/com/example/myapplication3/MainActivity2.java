package com.example.myapplication3;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class MainActivity2 extends FragmentActivity {

    EditText Email;
    EditText Password;
    ViewPager viewPager;
    fragmentTwo fragment2;
    Button btn;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //fragment2 = new fragmentTwo();

        viewPager = findViewById(R.id.viewPager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

        ScrollingPagerIndicator indicator = findViewById(R.id.indicator);
        indicator.attachToPager(viewPager);


//        btn.findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Email = findViewById(R.id.userEmail);
//                str = Email.getText().toString();
//                Log.e("",str);
//
//            }
//        });
    }
    public void test3(View view) {

//        Password = findViewById(R.id.userPassword);
        Toast.makeText(MainActivity2.this, "asdasdasd",Toast.LENGTH_LONG).show();

    }
    public void login(View view) {

//        Log.e("",Email.getText().toString());
//        Toast.makeText(this, "qwe", Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity2.this, Email.getText().toString(), Toast.LENGTH_LONG).show();
//        if (Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
//            Toast.makeText(this, "qwe", Toast.LENGTH_LONG).show();
//            //сreateDialog(this, "Все поля должны быть заполнены.");
//        } else {
//            if (!checkEmail(Email.getText().toString())) {
//                //сreateDialog(this, "Почта не соответствует паттерну \"name@domenname.ru\".");
//            } else {
//                userLogin();
//            }
//        }
    }

    public void userLogin() {
        //startActivity(new Intent(this, MainScreen.class));
        String email = ((TextView)findViewById(R.id.userEmail)).getText().toString();
        String password = ((TextView)findViewById(R.id.userPassword)).getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String baseUrl = "https://food.madskill.ru/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, baseUrl, json,
                response -> {
                    startActivity(new Intent(this, MainActivity.class));
                },
                error -> сreateDialog(this, "Не удалось авторизироваться:\n" + error.getMessage()));
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


    public void test123(View view) {
        Toast.makeText(MainActivity2.this,
                "w", Toast.LENGTH_SHORT).show();
    }
}