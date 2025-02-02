package com.example.evaluationsommative_2708081;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int jaugeValue = 0;
    private ImageView imageViewA, imageViewC;
    private TextView textViewB;
    private View[] bars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewA = findViewById(R.id.Button_Haut);
        imageViewC = findViewById(R.id.Button_Bas);
        textViewB = findViewById(R.id.Chiffre);

        bars = new View[8];
        bars[0] = findViewById(R.id.bar1);
        bars[1] = findViewById(R.id.bar2);
        bars[2] = findViewById(R.id.bar3);
        bars[3] = findViewById(R.id.bar4);
        bars[4] = findViewById(R.id.bar5);
        bars[5] = findViewById(R.id.bar6);
        bars[6] = findViewById(R.id.bar7);
        bars[7] = findViewById(R.id.bar8);

        imageViewA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                increaseJaugeValue(1);
                updateUI();
            }
        });

        imageViewA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                increaseJaugeValue(8);
                updateUI();
                return true; // Consume the long-click event
            }
        });
        imageViewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseJaugeValue(1);
                updateUI();
            }
        });
        imageViewC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                decreaseJaugeValue(8);
                updateUI();
                return true; // Consume the long-click event
            }
        });
        updateUI();
    }
    private void increaseJaugeValue(int increment) {
        jaugeValue += increment;
        if (jaugeValue > 8) {
            jaugeValue = 8;
        }
        updateUI();
    }
    private void decreaseJaugeValue(int decrement) {
        jaugeValue -= decrement;
        if (jaugeValue < 0) {
            jaugeValue = 0;
        }
        updateUI();
    }
    //Mise à jour
    private void updateUI() {
        for (int i = 0; i < bars.length; i++) {
            if (i < jaugeValue) {
                bars[i].setBackgroundColor(Color.RED);
            } else {
                bars[i].setBackgroundColor(Color.GRAY);
            }
        }
        textViewB.setText(String.valueOf(jaugeValue));
    }
    private void chiffre(){
        for (int i = 0; i < bars.length; i++) {
            if (i < jaugeValue) {
                bars[i].setVisibility(TextView.VISIBLE); // Afficher la barre
            } else {
                bars[i].setVisibility(TextView.INVISIBLE); // Masquer la barre
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("jaugeValue", jaugeValue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        jaugeValue = savedInstanceState.getInt("jaugeValue");
        updateUI(); // Mettez à jour l'interface avec la valeur restaurée
    }
}
