package com.example.capturarfirma;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class ListaFirmas extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listView;
    ArrayList<Firmas> listFirmas;

    ArrayList<String> arregloFirmas;

    int selectedPosition = ListView.INVALID_POSITION;

    private byte[] selectedFirma;

    private ArrayList<byte[]> arregloFirma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_firmas);

        conexion = new SQLiteConexion(this, Transacciones.nameDB, null, 1);
        arregloFirmas = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        arregloFirma = new ArrayList<byte[]>();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, arregloFirmas);
        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedContact = arregloFirmas.get(i);
                selectedPosition = i;
                selectedFirma = listFirmas.get(i).getFirma();  // Obtener la firma del objeto Firmas
                showConfirmationDialog(selectedContact, selectedFirma);
            }
        });




        GetPersons();

    }

    private void showConfirmationDialog(String selectedContact, byte[] firmaBytes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ver Firma de " + selectedContact);
        builder.setMessage("¿Desea ver la firma de este contacto?");

        builder.setPositiveButton("Ver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ListaFirmas.this, VisualizarFirma.class);

                intent.putExtra("firmaBytes", firmaBytes);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


    private void GetPersons() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Firmas firma = null;
        listFirmas = new ArrayList<Firmas>();

        Cursor cursor = db.rawQuery(Transacciones.SelectTableFirmas, null);
        while (cursor.moveToNext()) {
            firma = new Firmas();
            firma.setId(cursor.getInt(0));
            firma.setNombre(cursor.getString(1));
            firma.setFirma(cursor.getBlob(2)); // Utiliza getBlob() en lugar de getString().getBytes()

            listFirmas.add(firma);
        }

        cursor.close();
        FillList();
    }


    private void FillList() {
        for (int i = 0; i < listFirmas.size(); i++) {
            arregloFirmas.add(listFirmas.get(i).getId() + "-" +
                    listFirmas.get(i).getNombre() + "**" +
                    "Tamaño de la firma: " + listFirmas.get(i).getFirma().length);
        }
    }



}
