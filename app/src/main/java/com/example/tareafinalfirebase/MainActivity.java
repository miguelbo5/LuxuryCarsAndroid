package com.example.tareafinalfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements FragmentListado.OnCocheListener, FragmentAddCoche.OnCrearCoche, FragmentCoche.OnEliminarCocheListener {

    private ArrayList<Coche> coches;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        //escribirFireStore();

        coches = ActivitySplash.getListadoCoches();

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        FragmentListado fragmentListado = FragmentListado.newInstance();

        fragmentListado.coches = coches;

        transaction.replace(R.id.layout_container, fragmentListado);

        transaction.commit();

    }

    public void escribirFireStore() {

        coches = new ArrayList<>();

        coches.add(new Coche("1234ABC", "Opel", "Corsa", 12000, 20000, "opel_corsa"));
        coches.add(new Coche("2345GFD", "Mercedes", "Clase A", 30000, 40000, "mercedes_a45"));
        coches.add(new Coche("7890APL", "Peugeot", "308", 15000, 3000, "peugeot_308_gti"));
        coches.add(new Coche("4567UYT", "Ford", "Fiesta", 20000, 20, "ford_fiesta"));
        coches.add(new Coche("3456RTY", "Ford", "Focus", 13000, 300, "ford_focus"));
        coches.add(new Coche("9876POL", "Renault", "Clio", 23000, 10, "renault_clio"));
        coches.add(new Coche("2569GHJ", "Renault", "Megane", 30000, 5, "renault_megane"));
        coches.add(new Coche("6755TFP", "Audi", "A1", 28000, 67, "audi_a1"));

        for (Coche coche : coches) {

            db.collection("coches").document(coche.getMatricula()).set(coche);

        }

    }

    @Override
    public void onItemClick(Coche c) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        FragmentCoche fragment = FragmentCoche.newInstance();

        fragment.setCoche(c);

        transaction.replace(R.id.layout_container, fragment);

        transaction.addToBackStack(null);

        transaction.commit();

    }


    @Override
    public void onCrearCocheClick(Coche c) {


        db.collection("coches").document(c.getMatricula()).set(c);
        Toast.makeText(this, "Coche guardado en la BD", Toast.LENGTH_LONG).show();


        coches.add(0, c);

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        FragmentListado listado = FragmentListado.newInstance();
        listado.coches = this.coches;

        transaction.replace(R.id.layout_container, listado);

        transaction.addToBackStack(null);

        transaction.commit();

    }

    @Override
    public void onEliminarCocheClick(Coche c) {

        coches.remove(c);

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        FragmentListado listado = FragmentListado.newInstance();
        listado.coches = this.coches;

        transaction.replace(R.id.layout_container, listado);

        transaction.addToBackStack(null);

        transaction.commit();

        db.collection("coches").document(c.getMatricula())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("hola", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.w("hola", "Error deleting document", e);
                    }
                });


    }
}
