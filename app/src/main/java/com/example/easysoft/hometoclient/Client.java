package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

public class Client extends Activity {

    Socket socket = null;
    private static final String TAG = "Client";

    myModel model;
    Button btnLogin;
    List<myModel> list;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);

        btnLogin = (Button)findViewById(R.id.btn_login);

        list = new ArrayList<>();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username = ((EditText) findViewById(R.id.username)).getText().toString();
                Intent i = new Intent(Client.this, Chat.class);
                new Thread(new LoginThread()).start();
                model = new myModel();
                model.setNama(username);
                list.add(model);

                i.putExtra(Chat.KEY_ITEM, model);

                startActivity(i);
            }
        });

    }

    public Socket getSocket() {
        return socket;
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

                JSONObject jsObject = new JSONObject();
                jsObject.put("socket", socket);
                jsObject.put("username", username);

                System.out.println("object : " + jsObject);
//                Intent intentObj = null;
//                intentObj.putExtra(Client.this, Chat.java);
                Intent intObj = new Intent(Client.this, Chat.class);

            }

            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}