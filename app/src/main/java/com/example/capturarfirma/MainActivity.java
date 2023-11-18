package com.example.capturarfirma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar, btnLista;
    SignatureView signatureView;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signatureView=findViewById(R.id.signatureView);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnLista = (Button) findViewById(R.id.btnLista);
        txtName=findViewById(R.id.txtName);
        btnGuardar.setOnClickListener(v -> guardarFirma());
        btnLista.setOnClickListener(v -> verLista());

    }

    private void verLista() {
        iniciarNuevoActivity();
    }

    private void guardarFirma() {
        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.nameDB, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            String nombre = txtName.getText().toString();
            byte[] firmaBytes = obtenerBytesDesdeFirma();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombre, nombre);
            valores.put(Transacciones.firma, firmaBytes);


            long resultado = db.insert(Transacciones.Tabla1, null, valores);

            if (resultado > 0) {
                Toast.makeText(this, getString(R.string.Respuesta), Toast.LENGTH_SHORT).show();
                signatureView.clear();
                txtName.setText("");

            } else {
                Toast.makeText(this, getString(R.string.ErrorResp), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception exception) {
            Toast.makeText(this, getString(R.string.ErrorResp), Toast.LENGTH_SHORT).show();
        }
    }


    private void iniciarNuevoActivity() {
        Intent intent=new Intent(MainActivity.this, ListaFirmas.class);
        startActivity(intent);

    }

    private byte[] obtenerBytesDesdeFirma() {
        // Obtén la firma como un objeto Bitmap desde tu SignatureView
        Bitmap firmaBitmap = signatureView.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        firmaBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

/*    private Bitmap obtenerBitmapDesdeFirma() {
        // Obtén la firma como un objeto Bitmap
        // Aquí asumimos que SignatureView tiene un método getBitmap() que devuelve un Bitmap de la firma
        Bitmap firmaBitmap = signatureView.getBitmap();

        // Puedes ajustar el tamaño de la imagen según tus necesidades
        int width = 300; // ajusta el ancho según lo necesario
        int height = 300; // ajusta la altura según lo necesario
        Bitmap firmaResized = Bitmap.createScaledBitmap(firmaBitmap, width, height, false);

        return firmaResized;
    }
*/


}
