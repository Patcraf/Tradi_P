package com.example.tradi_p.serge;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tradi_p.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;


public class conn_tradi_1 extends AppCompatActivity {

    private Button btnSerge1;
    private FirebaseFirestore db;

    private EditText editTextNom, editTextPrenom, editTextCodeTradiP,
    editTextCategorie, editTextAdresseComplete, editTextCodePostal,
    editTextSpecialite, editTextMail;

    private void  initUI(){
        editTextNom=findViewById(R.id.editTextNom);
        editTextPrenom=findViewById(R.id.editTextPrenom);
        editTextCodeTradiP=findViewById(R.id.editTextCodeTradiP);
        editTextCategorie=findViewById(R.id.editTextCategorie);
        editTextAdresseComplete=findViewById(R.id.editTextAdresseComplete);
        editTextCodePostal=findViewById(R.id.editTextCodePostal);
        editTextSpecialite=findViewById(R.id.editTextSpecialite);
        editTextMail=findViewById(R.id.editTextMail);

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
    //fin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serge1);
        initUI();

        db = FirebaseFirestore.getInstance();

        btnSerge1 = findViewById(R.id.btnSerge1);
        clicFrame();
    }

    private void createUserInFirestore() {
        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String codeTradiP = editTextCodeTradiP.getText().toString();
        String categorie = editTextCategorie.getText().toString();
        String adresseComplete = editTextAdresseComplete.getText().toString();
        String codePostal = editTextCodePostal.getText().toString();
        String specialite = editTextSpecialite.getText().toString();
        String mail = editTextMail.getText().toString();



//        String id = UUID.randomUUID().toString(); // Ici tu remplaceras par le userId de l'utilisateur courant obtneu dans Authenticator
    String id = FirebaseAuth.getInstance().getUid();


        Donnees myDataToFirestore = new Donnees(id, nom, prenom, codeTradiP
        , categorie, adresseComplete, codePostal, specialite, mail);

        db.collection("Tradipraticien").document(id).set(myDataToFirestore)   //jai change "Users" en "Tradipraticien"
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(conn_tradi_1.this, serge2.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(conn_tradi_1.this, "Something get wrong !!!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clicFrame() {
        btnSerge1.setOnClickListener(new View.OnClickListener() { // Gestion du clic avec le listener
            @Override
            public void onClick(View view) {
                createUserInFirestore();
                //Toast.makeText(conn_tradi_1.this, "Mes renez vous", Toast.LENGTH_LONG).show();
            }
        });
    }
}