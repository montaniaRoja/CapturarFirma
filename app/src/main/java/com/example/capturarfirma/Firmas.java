package com.example.capturarfirma;


public class Firmas {

    private Integer id;
    private String nombre;

    private byte[] firma;



    public Firmas(Integer id, String nombre, byte firma[]){
        this.id=id;
        this.nombre=nombre;

        this.firma=firma;

    }

    public Firmas(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }
}