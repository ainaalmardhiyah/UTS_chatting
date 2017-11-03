package com.example.aina.uts_chatting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    public static String PrefChat = "file.main.pesanchat";
    RecyclerView recyclerView;
    PesanChatAdapter pesanChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SharedPreferences sharedPreferences = getSharedPreferences(PrefChat,0);
        String content= sharedPreferences.getString("PesanChat","No_PesanChat");

        try {
            JSONArray jsonArray = new JSONArray(content);
            pesanChatAdapter = new PesanChatAdapter(jsonArray);

           recyclerView.setAdapter(pesanChatAdapter);
            pesanChatAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.d("json",content);
    }

    public void createPesan(View view) {
        Intent intent = new Intent(this,PesanChat.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
