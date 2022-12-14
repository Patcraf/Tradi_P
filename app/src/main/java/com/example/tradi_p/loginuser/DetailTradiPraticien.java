package com.example.tradi_p.loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tradi_p.R;

public class DetailTradiPraticien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tradi_praticien);

        Intent intent = getIntent();
        String currentTradipraticienId = intent.getStringExtra("currentTradipraticien");

        // Ici tu fera une requete pour trouver toutes les données du document avc l'id currentTradipraticienId

        // POur le test
        Toast.makeText(this, "Hello " + currentTradipraticienId, Toast.LENGTH_SHORT).show();
    }
}