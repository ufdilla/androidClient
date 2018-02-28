package com.example.easysoft.hometoclient;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector extends AsyncTask<Void, Void, Void> {

    TextView textViewResponse;
    String textDestination;
    String username;
    String textRequest;
    String message = "";
    Socket socket;

    myModel model;
//    String destAddress;
//    int destPort;

    public Connector(Socket socket, String textRequest, TextView textViewResponse, String username) {
        this.socket = socket;
        this.textRequest = textRequest;
        this.textViewResponse = textViewResponse;
        this.username = username;
//        this.username = username;
//        this.textDestination= textDestination;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
//            String destAddress = "192.168.0.47";
//            int destPort = 2003;
//            socket = new Socket(destAddress, destPort);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            JSONObject jObj = new JSONObject();
            jObj.put("message", textRequest);
            jObj.put("username", username);
            jObj.put("destination", textDestination);
            dataOutputStream.writeUTF(String.valueOf(jObj));
            dataOutputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String input = dataInputStream.readUTF();
            JSONObject mesObj = new JSONObject(input);
            message = mesObj.getString("message");
            Log.d("mesObj", String.valueOf(mesObj));
//            dataInputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            message = "UnknownHostException: " + e.toString() + "\r\n";
        } catch (IOException e) {
            e.printStackTrace();
            message = "IOException: " + e.toString() + "\r\n";
            e.getCause();
            e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        String existingMessage = textViewResponse.getText().toString();
        message = existingMessage + username + " : " + message + "\n";
        textViewResponse.setText(message + "\n");
        super.onPostExecute(result);
    }
}
