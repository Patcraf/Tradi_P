package com.example.tradi_p.loginuser;

import static com.example.tradi_p.common.Constants.TRADIPRATICIEN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tradi_p.R;
import com.example.tradi_p.chat.ChatActivity;
import com.example.tradi_p.find.TAdapter;
import com.example.tradi_p.find.TModel;
import com.example.tradi_p.profil.ProfilActivity;
import com.example.tradi_p.serge.Donnees;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
}