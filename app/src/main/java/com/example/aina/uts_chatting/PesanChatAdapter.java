package com.example.aina.uts_chatting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ASUS on 03/11/2017.
 */

public class PesanChatAdapter extends RecyclerView.Adapter<PesanChatAdapter.ViewHolder> {
    JSONArray jsonArray;

    public PesanChatAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.editNama.setText(jsonObject.getString("NamaPengguna"));
            holder.editChat.setText(jsonObject.getString("KontenChat"));
            holder.tanggal.setText(jsonObject.getString("Tanggal"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView editNama, editChat, tanggal;
        public ViewHolder(View itemView) {

            super(itemView);
            editNama =  itemView.findViewById(R.id.namaUser);
            editChat =  itemView.findViewById(R.id.isiChat);
            tanggal =  itemView.findViewById(R.id.tanggal);
        }
    }
}
