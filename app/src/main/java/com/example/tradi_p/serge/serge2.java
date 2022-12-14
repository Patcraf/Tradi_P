package com.example.tradi_p.serge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tradi_p.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class serge2 extends AppCompatActivity {


    private Button btnSerge21;
    private Button btnSerge22;


    // Debut collage db

    private FirebaseFirestore db;
    FirebaseUser currentUser;

    private EditText editTextNom, editTextPrenom, editTextCodeTradiP,
            editTextCategorie, editTextAdresseComplete, editTextCodePostal,
            editTextSpecialite, editTextMail;


    private void initFB() {
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

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
    }


    // ici le debut du read

    public void readDataFromFirestore() {

        db.collection(TRADIPRATICIEN).document(currentUser.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            if (doc.exists()) {
                                editTextNom.setText(doc.getString("nom"));
                                editTextPrenom.setText(doc.getString("prenom"));
                                editTextCodeTradiP.setText(doc.getString("codeTradip"));
                                editTextCategorie.setText(doc.getString("categorie"));
                                editTextAdresseComplete.setText(doc.getString("codePostal"));
                                editTextCodePostal.setText(doc.getString("codePostal"));
                                editTextSpecialite.setText(doc.getString("specialite"));
                                editTextMail.setText(doc.getString("mail"));
                            }
                        }
                    }
                });
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        // editText.clear();
//                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
//                            editTextNom.setText(documentSnapshot.getString("editTextNom"));
//
//                            ModelDonnees modelDonnees = new ModelDonnees(
//                                    documentSnapshot.getString("id"),
//                                    documentSnapshot.getString("editTextNom"),
//                                    documentSnapshot.getString("editTextPrenom"),
//                                    documentSnapshot.getString("editTextCodeTradiP"),
//                                    documentSnapshot.getString("editTextCategorie"),
//                                    documentSnapshot.getString("editTextAdresseComplete"),
//                                    documentSnapshot.getString("editTextCodePostal"),
//                                    documentSnapshot.getString("editTextSpecialite"),
//                                    documentSnapshot.getString("editTextMail")
//                            );
//
//                        }
//                    }
//                });
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
    }

    // Fin collage db


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serge2);
        btnSerge22 = findViewById(R.id.btnSerge22);
        btnSerge21 = findViewById(R.id.btnSerge21);

        clicFrame();
        clicFrame3();
        initFB();
        initUI();
        readDataFromFirestore();
    }

    private void clicFrame() {

        btnSerge22.setOnClickListener(new View.OnClickListener() { // Gestion du clic avec le listener
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(serge2.this, serge3.class);
                startActivity(intent);
            }
        });
    }

//}


///   ici jai place le deuxieme Bouton "Mes disponibilites"


    public void clicFrame3() {
        btnSerge21.setOnClickListener(new View.OnClickListener() { // Gestion du clic avec le listener
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(serge2.this, MesDispo.class);
                startActivity(intent);

            }
        });
    }

}









