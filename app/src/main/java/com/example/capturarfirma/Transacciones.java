package com.example.capturarfirma;

public class Transacciones
{
    // Nombre de la base de datos
    public static final String nameDB = "firmasdb";

    //Tablas de la base de datos
    public static final String Tabla1  = "firmas";


    // Campos de la tabla
    public static final String id = "id";

    public static final String firma = "firma";
    public static final String nombre = "nombre";

    public static final String DeleteContact = "DELETE FROM " + Transacciones.Tabla1 + " WHERE " + Transacciones.id + " = ?";


    // Consultas de Base de datos
    //ddl


    public static final String CreateTableFirmas = "CREATE TABLE firmas " +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(100),firma BLOB)";


    public static final String DropTableVideos  = "DROP TABLE IF EXISTS firmas";

    public static final String SelectTableFirmas = "SELECT * FROM " + Transacciones.Tabla1;




}
