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

public class Connector extends AsyncTask<Void, Void, Void>
{
    String destAddress = "192.168.0.47";
    int destPort = 2002;
    TextView textViewResponse;
    String username;
    String textRequest;
    String message = "";

    public Connector(String destAddress, int destPort, String username, String textRequest, TextView textViewResponse)
    {
        this.destAddress = destAddress;
        this.destPort = destPort;
        this.username = username;
        this.textRequest = textRequest;
        this.textViewResponse = textViewResponse;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        Socket socket = null;

        try {
            socket = new Socket(destAddress, destPort);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            JSONObject jObj = new JSONObject();
            jObj.put("message", textRequest);
            jObj.put("username", username);
            dataOutputStream.writeUTF(String.valueOf(jObj));
            dataOutputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String input = dataInputStream.readUTF();
            JSONObject mesObj = new JSONObject(input);
            message = mesObj.getString("message");
            Log.d("mesObj", String.valueOf(mesObj));
            dataInputStream.close();
            }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
            message = "UnknownHostException: " + e.toString() + "\r\n";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            message = "IOException: " + e.toString() + "\r\n";
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (socket != null)
            {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute (Void result)
    {
        String existingMessage = textViewResponse.getText().toString();
        message = existingMessage + username + " : " + message + "\n";
        textViewResponse.setText(message + "\n");
        super.onPostExecute(result);
    }
}