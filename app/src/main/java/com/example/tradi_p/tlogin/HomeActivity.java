package com.example.tradi_p.tlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.example.tradi_p.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}