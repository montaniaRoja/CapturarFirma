package com.example.capturarfirma;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.widget.ImageView;

public class VisualizarFirma extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_firma);


        // Recupera los bytes de la firma del Intent
        byte[] firmaBytes = getIntent().getByteArrayExtra("firmaBytes");


        ImageView imageView = findViewById(R.id.imageView);
        Bitmap firmaBitmap = BitmapFactory.decodeByteArray(firmaBytes, 0, firmaBytes.length);
        imageView.setImageBitmap(firmaBitmap);
    }
    }
