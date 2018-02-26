package com.example.easysoft.hometoclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button btn1;
//    private static final int SERVERPORT = 2003;
//    private static final String SERVER_IP = "192.168.0.47";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view1){
        startActivity(new Intent(this, Client.class));
    }

    public void exitApp(View view) {
        finish();
        System.exit(0);
    }
}