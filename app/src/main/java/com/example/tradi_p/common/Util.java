package com.example.tradi_p.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Util {

    public static void showSnackBar(View baseView, String message){
        Snackbar.make(baseView, message, Snackbar.LENGTH_LONG).show();
    }

    /** Méthode pour le check d'internet **/
    public static boolean connectionAvailable(Context context){
        // Pour utiliser cette classe il ne faut oublier d'ajouter la permisson dans le manifest
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null ){
            return connectivityManager.getActiveNetworkInfo().isAvailable();
        } else {
            return false;
        }
    }
}
