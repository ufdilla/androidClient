package com.example.easysoft.hometoclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

import static android.content.ContentValues.TAG;

public class Chat extends Activity {

    Socket socket = null;
    public static final String KEY_ITEM = "item";
    myModel myModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

//        myModel = (myModel) getIntent().getSerializableExtra(KEY_ITEM);

    }

    public void onClick(View view) {
        try {
            new Thread(new Chat.ClientThread()).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Context getActivity() {
        return null;
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {
            try {

            Socket socket = Client.getSocket();
            String textRequest = ((EditText) findViewById(R.id.textRequest)).getText().toString();
//            String textDestination =((EditText) findViewById(R.id.textDestination)).getText().toString();
            TextView textViewResponse = findViewById(R.id.textResponse);
//            String username = myModel.getNama();
            Connector connector = new Connector(socket, textRequest, textViewResponse);
            connector.execute();

            }
            catch (Exception e)
            {
                Log.e(TAG, "run: ", e );
            }

        }

    }
}
