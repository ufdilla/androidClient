package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class Client extends Activity {

    private Socket socket;

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

            //                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
//
//                socket = new Socket(serverAddr, SERVERPORT);
            String username = ((EditText) findViewById(R.id.username)).getText().toString();
            String textRequest =((EditText) findViewById(R.id.textRequest)).getText().toString();

//            JSONObject jObj = new JSONObject(textRequest);

            TextView textViewResponse = (TextView) findViewById(R.id.textResponse);
            Connector connector = new Connector(SERVER_IP, SERVERPORT, username, textRequest, textViewResponse);
            connector.execute();

        }

    }

}