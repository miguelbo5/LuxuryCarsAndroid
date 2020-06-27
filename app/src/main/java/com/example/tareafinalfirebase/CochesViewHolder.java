package com.example.tareafinalfirebase;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CochesViewHolder extends RecyclerView.ViewHolder {

    private Context contexto;
    private TextView tv_marca_modelo;
    private TextView tv_precio;
    private TextView tv_kilometros;
    private ImageView img;

    public CochesViewHolder(View itemView, Context contexto) {

        super(itemView);

        tv_marca_modelo = itemView.findViewById(R.id.texto_marca_modelo);
        tv_precio = itemView.findViewById(R.id.texto_precio);
        tv_kilometros = itemView.findViewById(R.id.texto_kilometros);
        img = itemView.findViewById(R.id.img_coche);

        this.contexto = contexto;
    }

    public void bindCoche(Coche c) {

        int id = contexto.getResources().getIdentifier(c.getImagen(), "drawable", "com.example.tareafinalfirebase");
        if (id != 0) {
            img.setImageResource(id);
        } else {
            img.setImageResource(R.drawable.mercedes_a45);
        }

        tv_marca_modelo.setText(c.getMarca() + " " + c.getModelo());

        tv_kilometros.setText(Math.round(c.getKilometros()) + " km");

        tv_precio.setText((int) c.getPrecio() + "€");

    }
}
