package com.example.tradi_p.serge;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tradi_p.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MesDispo extends AppCompatActivity {

    private FloatingActionButton fab;

    //Var pour la date
    private String date;

    // Var pour les heures
    private int tmHour, tmMinute; // Var pour la gestion de la sélection de l'heure
    private String heureDebut;
    private String heureFin;

    // Var pour le remplissage du linear layout
    private TextView tvDate;
    private TextView tvHeureDebut, tvHeureFin;


    private void initUI() {
        fab = findViewById(R.id.floatingActionButton);
        tvDate = findViewById(R.id.tvDate);
        tvHeureDebut = findViewById(R.id.tvHeureDebut);
        tvHeureFin = findViewById(R.id.tvHeureFin);
    }

    private void gestionDeLaDate() {
        Calendar mCurrentDate = Calendar.getInstance();
        int mYear = mCurrentDate.get(Calendar.YEAR);
        int mMonth = mCurrentDate.get(Calendar.MONTH);
        int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(MesDispo.this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int
                            selectedYear, int selectedMonth, int selectedDay) {
// Update the editText to display the selected date
                        date = selectedDay
                                + "/"
                                + selectedMonth + 1
                                + "/"
                                + selectedYear;
                        gestionHeure();
                    }
                }, mYear, mMonth, mDay);
        mDatePicker.show();
    }

    private void gestionHeure() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MesDispo.this, new TimePickerDialog.OnTimeSetListener() {
            @Override

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Initialisation heure et minute
                tmHour = hourOfDay;
                tmMinute = minute;
                // Initialisation du l'objet calendar
                Calendar calendar = Calendar.getInstance();
                // Ajustement de l'heure et des minutes
                calendar.set(0, 0, 0, tmHour, tmMinute);

                // Gestion de l'enregistrement des heures debut et fin
                if (heureDebut == null) {
                    heureDebut = (String) DateFormat.format("HH:mm", calendar);
                    gestionHeure();
                } else {
                    heureFin = (String) DateFormat.format("HH:mm", calendar);
                    affichageDispo();
                }
                // Set du temps sélectionner dans le TextView
            }
        }, 24, 0, true
                // Réglage de l'horloge les heures, les minutes et si c'est sur 24 heures
        );
        // Affichage du temps sélectionné
        timePickerDialog.updateTime(tmHour, tmMinute);
        // Affcihage de la boite de dialogue
        timePickerDialog.show();

    }

    private void affichageDispo() {
        // Ici tu fais l'enregistrement en db puis tu affiches le tout dans un recyclerView
        tvDate.setText(date);
        tvHeureDebut.setText(heureDebut);
        tvHeureFin.setText(heureFin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_dispo);

        initUI();
        heureDebut = null;
        tvDate.setText("");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestionDeLaDate();

            }
        });
    }


}




