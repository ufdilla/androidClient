package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Client extends Activity {

    private static final int SERVERPORT = 2003;
    private static final String SERVER_IP = "192.168.0.47";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
    }

    public void onClick(View view) {
        try {
            new Thread(new ClientThread()).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReturnHome(View view){
        super.onBackPressed();
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String textRequest =((EditText) findViewById(R.id.textRequest)).getText().toString();
        String textDestination =((EditText) findViewById(R.id.textDestination)).getText().toString();

        TextView textViewResponse = findViewById(R.id.textResponse);
        Connector connector = new Connector(SERVER_IP, SERVERPORT, username, textRequest, textViewResponse, textDestination);
        connector.execute();

        }

    }

}