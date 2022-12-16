package com.example.tradi_p.loginuser;

import static com.example.tradi_p.common.Constants.TRADIPRATICIEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tradi_p.R;
import com.example.tradi_p.find.TAdapter;
import com.example.tradi_p.find.TModel;
import com.example.tradi_p.profil.ProfilActivity;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class UhomeActivity extends AppCompatActivity {

    private androidx.appcompat.widget.SearchView searchView;
    private TAdapter adapter;
    private static final String TAG = "ShowNoteActivity";
    private RecyclerView rvTradi;
    private FirebaseFirestore db;

    /** INIT **/
    private void initUI() {
        rvTradi = findViewById(R.id.rvTradi);
        rvTradi.hasFixedSize();
        rvTradi.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    /** MÉTHODES PERSOS  **/
    public void readDataFromFirestore() {
        Query query = db.collection(TRADIPRATICIEN).orderBy("nom");

        FirestoreRecyclerOptions<TModel> searchTradi =
                new FirestoreRecyclerOptions.Builder<TModel>()
                        .setQuery(query, TModel.class)
                        .build();

        adapter = new TAdapter(searchTradi);
        rvTradi.setAdapter(adapter);
    }

    public void recherche() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTradiPraticien(newText);
                return false;
            }
        });
    }

    private void searchTradiPraticien(String toString) {
        Query query = db.collection(TRADIPRATICIEN);

        if (!String.valueOf(toString).equals("")) {
            query = query
                    .orderBy("nom")
                    .startAt(toString)
                    .endAt(toString + "\uf8ff");
        }

        FirestoreRecyclerOptions<TModel> searchTradi =
                new FirestoreRecyclerOptions.Builder<TModel>()
                        .setQuery(query, TModel.class)
                        .build();

        adapter = new TAdapter(searchTradi);
        rvTradi.setAdapter(adapter);
        adapter.startListening();
    }

    private void gestionAfficheTradiFiche(){
        adapter.setOnItemClickListener(new TAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Intent intent = new Intent(UhomeActivity.this, DetailTradiPraticien.class);
                intent.putExtra("currentTradipraticien", id);
                intent.putExtra("role", "toto");
                startActivity(intent);
            }
        });
    }

    /** CYCLE DE VIES  **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uhome);

        initUI();
        initFirestore();
        readDataFromFirestore();
        gestionAfficheTradiFiche();
        //initialize search view
        searchView = findViewById(R.id.svHome);
        recherche();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

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
            startActivity(new Intent(UhomeActivity.this, ProfilActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
    //fin
}