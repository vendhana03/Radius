package com.example.vendhana.radius;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    List<user> userList;
    ListView listView;
    ProgressDialog p;
    listAdap lta;
    String url = "https://raw.githubusercontent.com/iranjith4/radius-intern-mobile/master/users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listview);
        p = new ProgressDialog(this);
        getfunc();
    }

    void getfunc(){
        p.setMessage("Loading...");
        p.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray userArray = obj.getJSONArray("results");
                            for (int i = 0; i < userArray.length(); i++) {
                                JSONObject userobj = userArray.getJSONObject(i);
                                JSONObject nameobj = (JSONObject) userobj.get("name");
                                JSONObject dobobj = (JSONObject) userobj.get("dob");
                                JSONObject imgobj = (JSONObject) userobj.get("picture");

                                String name1 = nameobj.getString("title")+ ". " + nameobj.getString("first")+ " " + nameobj.getString("last");
                                String age1 = dobobj.getString("age");
                                String url1 = imgobj.getString("medium");

                                user user = new user(name1,age1,url1);

                                userList.add(user);
                            }

                            listAdap adapter = new listAdap(getApplicationContext(), userList);
                            listView.setAdapter(adapter);
                            for (user s : userList) {
                                s.loadImage(lta);
                            }
                            if(p.isShowing()){
                                p.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(p.isShowing()){
                            p.dismiss();
                        }
                        Intent i = new Intent(getApplicationContext(), error.class);
                        startActivity(i);
                        finish();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).create().show();
    }

}
