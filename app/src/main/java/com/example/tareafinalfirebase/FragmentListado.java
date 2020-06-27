package com.example.tareafinalfirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentListado extends Fragment implements View.OnClickListener{

    public ArrayList<Coche> coches;
    private RecyclerView recyclerView;
    public AdaptadorCoches adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private OnCocheListener cocheListener;

    private Button botonAddCoche;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaLayout = inflater.inflate(R.layout.listado_fragment, container, false);

        botonAddCoche = vistaLayout.findViewById(R.id.boton_add_coche);
        botonAddCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getFragmentManager();

                FragmentTransaction transaction = supportFragmentManager.beginTransaction();

                FragmentAddCoche fragmentAddCoche = FragmentAddCoche.newInstance();

                transaction.replace(R.id.layout_container, fragmentAddCoche);

                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        recyclerView = vistaLayout.findViewById(R.id.rv_coches);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adaptador = new AdaptadorCoches(getContext(), coches);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cocheListener.onItemClick(coches.get(recyclerView.getChildAdapterPosition(v)));
            }
        });

        recyclerView.setAdapter(adaptador);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        return vistaLayout;

    }

    @Override
    public void onClick(View v) {



    }

    public interface OnCocheListener{

        void onItemClick(Coche c);

    }

    public static FragmentListado newInstance() {

        FragmentListado fragmentListado = new FragmentListado();

        return fragmentListado;

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof OnCocheListener){

            cocheListener = (OnCocheListener) context;

        }

    }

}
