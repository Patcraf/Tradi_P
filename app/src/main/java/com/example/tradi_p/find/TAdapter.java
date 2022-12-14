package com.example.tradi_p.find;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tradi_p.R;
import com.example.tradi_p.loginuser.UhomeActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class TAdapter extends FirestoreRecyclerAdapter<TModel, TAdapter.ViewHolder> {

    private static final String TAG = "Adapter Firestore";

    public TAdapter(@NonNull FirestoreRecyclerOptions<TModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public TAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tradi, parent, false);
        return new TAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TModel model) {
        holder.nom.setText(model.getNom());
        holder.prenom.setText(model.getPrenom());
    }

    @Override
    public void onError(@NonNull FirebaseFirestoreException e) {
        Log.e(TAG, "Error Firebase : " + e.getMessage());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom, prenom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tvShowTitle);
            prenom = itemView.findViewById(R.id.tvShowContent);

            // Cliclicetener sur itemView avec le listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && tradiClickListener != null){
                        DocumentSnapshot filmSnapshot = getSnapshots().getSnapshot(position);
                        tradiClickListener.onItemClick(filmSnapshot, position);
                    }
                }
            });
        }
    }

    /** #1 Interface **/
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    private OnItemClickListener tradiClickListener;

    public void setOnItemClickListener(OnItemClickListener tradiClickListener){
        this.tradiClickListener = tradiClickListener;
    }
}
