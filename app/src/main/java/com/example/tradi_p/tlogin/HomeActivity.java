package com.example.tradi_p.tlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tradi_p.R;
import com.example.tradi_p.profil.ProfilActivity;

public class HomeActivity extends AppCompatActivity {




    /**
     * 2 Ajout de la méthode onCreateOptionsMenu pour afficher le menu
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On souffle le xml de menu_main dans le menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // fin

    /**
     * 3 Ajout de la méthode onOptionsItemSelected pour activer le clic sur les items du menu
     **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnuProfile) {
            startActivity(new Intent(HomeActivity.this, ProfilActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
    //fin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}