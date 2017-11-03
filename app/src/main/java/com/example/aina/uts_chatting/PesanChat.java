package com.example.aina.uts_chatting;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PesanChat extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharePEdit;
    EditText EditNama, EditChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_chat);

        getSupportActionBar().setTitle("Create Pesan");

        EditNama = (EditText) findViewById(R.id.editNama);
        EditChat = (EditText) findViewById(R.id.editChat);

        sharedPreferences = getSharedPreferences(MainActivity.PrefChat,0);
        sharePEdit = sharedPreferences.edit();

    }

    public void KirimPesan(View view) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("NamaPengguna",EditNama.getText().toString());
            jsonObject.put("KontenChat",EditChat.getText().toString());
            jsonObject.put("Tanggal",new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        if (sharedPreferences.contains("PesanChat"))
        {
            String content =sharedPreferences.getString("PesanChat","No_PesanChat");
            try {
                JSONArray jsonArray = new JSONArray(content);
                jsonArray.put(jsonObject);
                sharePEdit.putString("PesanChat",jsonArray.toString());
                sharePEdit.apply();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            sharePEdit.putString("PesanChat",jsonArray.toString());
            sharePEdit.apply();
        }
        finish();
    }
}
