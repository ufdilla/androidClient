package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

class Client extends Activity {

    private static Socket socket = null;
    private static final String TAG = "Client";

    myModel model;
    Button btnLogin;
    List<myModel> list;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);

        btnLogin = findViewById(R.id.btn_login);

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

    public static void setSocket(Socket _socket) {
        Client.socket = _socket;
    }

    public static Socket getSocket() {
        return Client.socket;
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

                Client.setSocket(socket);
                Intent intent = new Intent(Client.this, Chat.class);
                startActivity(intent);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private Context getActivity() {
            return null;
        }

    }

}
