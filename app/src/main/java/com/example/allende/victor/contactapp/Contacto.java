package com.example.allende.victor.contactapp;

import java.sql.Blob;

/**
 * Created by allen on 25/01/2017.
 */
public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String e_mail;
    private byte[] imagen;

    public Contacto(int id, String nombre, String telefono, String direccion, String e_mail, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.e_mail = e_mail;
        this.imagen = imagen;
    }

    public Contacto(String nombre, String telefono, String direccion, String e_mail,  byte[] imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.e_mail = e_mail;
        this.imagen = imagen;
    }

    public Contacto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
