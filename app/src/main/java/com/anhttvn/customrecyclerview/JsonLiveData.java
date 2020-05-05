package com.anhttvn.customrecyclerview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonLiveData  {

        private String resp;
        ProgressDialog progressDialog;
        Context context;
        JsonLiveData(Context context){
            this.context = context;
            new GetLiveData().execute();
        }

    public class GetLiveData extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... params) {
        publishProgress("Sleeping..."); // Calls onProgressUpdate()
        StringBuffer response = null;
        try {
            URL obj = new URL("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("Application/JSON", "");
            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return response.toString();
        }


@Override
protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        progressDialog.dismiss();
        //finalResult.setText(result);
        Log.e("Async",result);
        }


@Override
protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,
        "ProgressDialog",
        "Please wait...");
        }
     }
}
