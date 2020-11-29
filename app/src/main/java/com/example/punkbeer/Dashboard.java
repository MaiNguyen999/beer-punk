package com.example.punkbeer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private Button btn_go;
    private EditText et_beer, et_food,et_ibu, et_abv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        et_beer = findViewById(R.id.et_beer);
        et_food = findViewById(R.id.et_food);
        et_ibu = findViewById(R.id.et_ibu);
        et_abv = findViewById(R.id.et_abv);

        btn_go = findViewById(R.id.btn_go);
        btn_go.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Dashboard.this, Beers.class);
        intent.putExtra("beer",et_beer.getText().toString());
        intent.putExtra("food",et_food.getText().toString());
        intent.putExtra("ibu",et_ibu.getText().toString());
        intent.putExtra("abv",et_abv.getText().toString());
        startActivity(intent);
    }

//    public void onClick(View view) {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(Dashboard.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}