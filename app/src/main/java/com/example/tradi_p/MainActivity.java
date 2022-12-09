package com.example.tradi_p;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tradi_p.common.Util;
import com.example.tradi_p.loginuser.UhomeActivity;
import com.example.tradi_p.tlogin.TloginActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Debut

    private View baseView;


    private void initUI() {
        baseView = findViewById(R.id.mainLayout);
        btnTradi = findViewById(R.id.btnTradi);
    }

    private void singUpActivity(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build()
        );

        Intent signIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                // Ici l'ajout de design
                //.setLogo(R.drawable.firebase_logo)
                // .setTheme(R.style.LoginTheme)
                .setTosAndPrivacyPolicyUrls("https://google.fr", "https://yahoo.fr")
                .setIsSmartLockEnabled(true)
                .build();

        signLauncher.launch(signIntent);
    }

    private final ActivityResultLauncher<Intent> signLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignResult(result);
                }
            }
    );

    private void onSignResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if(result.getResultCode() == RESULT_OK){
            // Connecté ;)
            Util.showSnackBar(baseView, getString(R.string.connected));
        } else {
            /// Pas connecté
            if(response == null){
                Util.showSnackBar(baseView, getString(R.string.error_canceled));
            } else if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK){
                Util.showSnackBar(baseView, getString(R.string.no_internet));
            } else if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR){
                Util.showSnackBar(baseView, getString(R.string.unknow_error));
            }
        }
    }

    public void startSignUpActivity(View view){
        Log.i("TAG", "startSignUpActivity: ");
        singUpActivity();
    }




    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){

            startActivity(new Intent(MainActivity.this, UhomeActivity.class));
        }

    }

    // Fin

    //Vars
        Button btnUser, btnTradi;

    //lien code design
//    private void initUI() {
//        btnUser = findViewById(R.id.btnUser);
//        btnTradi = findViewById(R.id.btnTradi);
//    }

    //Methode


    private void clicBtnTradi(){
        btnTradi.setOnClickListener(new View.OnClickListener() { // Gestion du clic avec le listener
            @Override
            public void onClick(View view) {
                // Action effectuée lors du clic ici un intent pour se rendre sur l'activité des linear layout
                // Départ                   // Arrivée
                Intent intent = new Intent(MainActivity.this, TloginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration Methode
        initUI();
        clicBtnTradi();

    }
}