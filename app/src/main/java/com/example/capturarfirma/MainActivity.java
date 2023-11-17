package com.example.capturarfirma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar;
    SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = findViewById(R.id.btnGuardar);
        signatureView = findViewById(R.id.signatureView);
    }

    // Agrega este método para manejar el clic del botón
    public void saveSignature(View view) {
        // Obtén la firma del objeto SignatureView
        // Puedes usar signatureView.getSignature() para obtener la Path de la firma
        // y realizar cualquier acción necesaria, como guardarla en SQLite
    }
}
