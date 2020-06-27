package com.example.tareafinalfirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentAddCoche extends Fragment {

    private OnCrearCoche crearCocheListener;

    private EditText editTextMatricula;
    private EditText editTextMarca;
    private EditText editTextModelo;
    private EditText editTextPrecio;
    private EditText editTextKilometros;
    private EditText editTextImagen;

    private Button botonCrear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaLayout = inflater.inflate(R.layout.add_coche_fragment, container, false);

        editTextMatricula = vistaLayout.findViewById(R.id.et_matricula_coche);
        editTextMarca = vistaLayout.findViewById(R.id.et_marca_coche);
        editTextModelo = vistaLayout.findViewById(R.id.et_modelo_coche);
        editTextKilometros = vistaLayout.findViewById(R.id.et_kilometros_coche);
        editTextPrecio = vistaLayout.findViewById(R.id.et_precio_coche);
        editTextImagen = vistaLayout.findViewById(R.id.et_imagen_coche);

        botonCrear = vistaLayout.findViewById(R.id.boton_crear);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String matricula = editTextMatricula.getText().toString();
                String marca = editTextMarca.getText().toString();
                String modelo = editTextModelo.getText().toString();
                double precio = Double.parseDouble(editTextPrecio.getText().toString());
                double kilometros = Double.parseDouble(editTextKilometros.getText().toString());
                String imagen = editTextImagen.getText().toString();

                Coche c = new Coche(matricula, marca, modelo, precio, kilometros, imagen);

                if(c != null){

                    crearCocheListener.onCrearCocheClick(c);

                }

            }
        });

        return vistaLayout;

    }

    public interface OnCrearCoche{

        void onCrearCocheClick(Coche c);

    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);

        if(context instanceof OnCrearCoche){

            crearCocheListener = (OnCrearCoche) context;

        }

    }

    public static FragmentAddCoche newInstance() {

        FragmentAddCoche fragmentNuevoProducto = new FragmentAddCoche();

        return fragmentNuevoProducto;

    }

}
