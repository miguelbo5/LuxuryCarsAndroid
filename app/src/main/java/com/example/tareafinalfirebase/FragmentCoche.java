package com.example.tareafinalfirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class FragmentCoche extends Fragment {

    View vistaLayout;

    public Coche c;

    private ImageView imagen;
    private TextView marcaModelo;
    private TextView kilometros;
    private TextView precio;

    private Button botonBorrar;

    private OnEliminarCocheListener eliminarCocheListener;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        vistaLayout = inflater.inflate(R.layout.coche_fragment, container, false);

        imagen = vistaLayout.findViewById(R.id.imagen_coche);
        marcaModelo = vistaLayout.findViewById(R.id.modelo_marca);
        kilometros = vistaLayout.findViewById(R.id.kilometros);
        precio = vistaLayout.findViewById(R.id.precio);

        int id = getResources().getIdentifier(c.getImagen(), "drawable", "com.example.tareafinalfirebase");

        if (id != 0) {

            imagen.setImageResource(id);

        } else {

            imagen.setImageResource(R.drawable.mercedes_a45);

        }

        marcaModelo.setText(c.getMarca() + " " + c.getModelo());
        kilometros.setText(Math.round(c.getKilometros()) + "km");
        precio.setText(Math.round(c.getPrecio()) + "€");

        botonBorrar = vistaLayout.findViewById(R.id.boton_borrar);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(vistaLayout, "Presiona borrar para confirmar", Snackbar.LENGTH_LONG)
                        .setAction("Borrar", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                eliminarCocheListener.onEliminarCocheClick(c);

                                Toast.makeText(getContext(), c.getMarca() + " " + c.getModelo() + " se ha borrado correctamente", Toast.LENGTH_LONG).show();

                            }

                        })
                        .setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .show();

            }
        });

        return vistaLayout;

    }

    public static FragmentCoche newInstance() {

        FragmentCoche fragmentCoche = new FragmentCoche();

        return fragmentCoche;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnEliminarCocheListener) {
            eliminarCocheListener = (OnEliminarCocheListener) context;
        }

    }

    public interface OnEliminarCocheListener {

        void onEliminarCocheClick(Coche c);

    }

    public void setCoche(Coche c) {

        this.c = c;

    }

}
