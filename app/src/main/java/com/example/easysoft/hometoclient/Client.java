package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Activity {

    Socket socket = null;
    private static final String TAG = "Client";
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

    public void Login(View view){
        try {
            new Thread(new LoginThread()).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String textRequest =((EditText) findViewById(R.id.textRequest)).getText().toString();
        String textDestination =((EditText) findViewById(R.id.textDestination)).getText().toString();

        TextView textViewResponse = findViewById(R.id.textResponse);
        Connector connector = new Connector(socket, username, textRequest, textViewResponse, textDestination);
        connector.execute();

        }

    }

    private class LoginThread implements Runnable {
        @Override
        public void run() {
            String username = ((EditText) findViewById(R.id.username)).getText().toString();

            try {
            String destAddress = "192.168.0.47";
            int destPort = 2003;

            socket = new Socket(destAddress, destPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(username);
//                Log.d("socket ", String.valueOf(out));
            }

            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
          }
        }
}