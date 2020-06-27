package com.example.tareafinalfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorCoches extends RecyclerView.Adapter<CochesViewHolder> implements View.OnClickListener {

    private Context context;
    private View.OnClickListener listener;
    private ArrayList<Coche> coches;

    public AdaptadorCoches(Context context, ArrayList<Coche> coches) {

        this.context = context;
        this.coches = coches;

    }

    public CochesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_listado, parent, false);

        itemView.setOnClickListener(this);

        CochesViewHolder viewHolder = new CochesViewHolder(itemView, context);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CochesViewHolder holder, int position) {

        Coche coche = coches.get(position);

        holder.bindCoche(coche);

    }

    @Override
    public int getItemCount() {

        return coches.size();

    }

    @Override
    public void onClick(View v) {

        if (listener != null) {

            listener.onClick(v);

        }

    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;

    }


}
