package com.example.tareafinalfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ActivitySplash extends Activity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static ArrayList<Coche> coches;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        coches = leerFireStore();


    }

    public ArrayList<Coche> leerFireStore() {

        final ArrayList<Coche> cochesLeer = new ArrayList<>();

        db.collection("coches").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String matricula = document.getString("matricula");
                        String marca = document.getString("marca");
                        String modelo = document.getString("modelo");
                        double precio = document.getDouble("precio");
                        double kilometros = document.getDouble("kilometros");
                        String imagen = document.getString("imagen");

                        Coche cocheLeido = new Coche(matricula, marca, modelo, precio, kilometros, imagen);

                        Log.i("hola", cocheLeido.toString());

                        cochesLeer.add(cocheLeido);

                    }

                    //Para iniciar el activity una vez ha terminado de cargar los datos
                    startActivity(new Intent(ActivitySplash.this, MainActivity.class));
                    finish();

                } else {
                    Log.w("hola", "Error getting documents.", task.getException());
                }

            }
        });

        return cochesLeer;

    }

    public static ArrayList<Coche> getListadoCoches() {

        return coches;

    }
}