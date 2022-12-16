package com.example.tradi_p.loginuser;

import static com.example.tradi_p.common.Constants.TRADIPRATICIEN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tradi_p.R;
import com.example.tradi_p.profil.ProfilActivity;
import com.example.tradi_p.tlogin.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class DetailTradiPraticien extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView editTextNom, editTextPrenom, editTextCodeTradiP,
            editTextCategorie, editTextAdresseComplete, editTextCodePostal,
            editTextSpecialite, editTextMail;

    private Button button;


    private void initFB() {
        db = FirebaseFirestore.getInstance();
    }

    private void initUI() {
        editTextNom = findViewById(R.id.editTextTextPersonName);
        editTextPrenom = findViewById(R.id.editTextTextPersonName11);
        editTextCodeTradiP = findViewById(R.id.editTextTextPersonName12);
        editTextCategorie = findViewById(R.id.editTextTextPersonName13);
        editTextAdresseComplete = findViewById(R.id.editTextTextPersonName14);
        editTextCodePostal = findViewById(R.id.editTextTextPersonName15);
        editTextSpecialite = findViewById(R.id.editTextTextPersonName16);
        editTextMail = findViewById(R.id.editTextTextPersonName17);

        button = findViewById(R.id.btnModifierProfil);
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
            startActivity(new Intent(DetailTradiPraticien.this, ProfilActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
    //fin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tradi_praticien);

        initUI();
        initFB();

        Intent intent = getIntent();
        String currentTradipraticienId = intent.getStringExtra("currentTradipraticien");
//        String role = intent.getStringExtra("Role");
//
//        readDataFromFirestore(currentTradipraticienId);
//        // POur le test
//        switch(role){
//            case "user":
//            Toast.makeText(this, "Vous êtes un utilisateur", Toast.LENGTH_SHORT).show();
//            button.setVisibility(View.GONE);
//            break;
//            case "tradi":
//            button.setVisibility(View.VISIBLE);
//            break;
//        }
//
   }

    public void readDataFromFirestore(String currentTradipraticienId) {


        db.collection(TRADIPRATICIEN).document(currentTradipraticienId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            if (doc.exists()) {
                                editTextNom.setText(doc.getString("nom"));
//                                editTextPrenom.setText(doc.getString("prenom"));
//                                editTextCodeTradiP.setText(doc.getString("codeTradip"));
//                                editTextCategorie.setText(doc.getString("categorie"));
//                                editTextAdresseComplete.setText(doc.getString("codePostal"));
//                                editTextCodePostal.setText(doc.getString("codePostal"));
//                                editTextSpecialite.setText(doc.getString("specialite"));
//                                editTextMail.setText(doc.getString("mail"));
                            }
                        }
                    }
                });
    }
}