package com.example.allende.victor.contactapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by allen on 31/01/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "base_datos_contacApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase data_base_sqLite) {
        String crear_base_datos_contacApp = "CREATE TABLE contactos  (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, direccion TEXT, email TEXT, foto BLOB )";
        data_base_sqLite.execSQL(crear_base_datos_contacApp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase data_base_name, int i, int i1) {
        data_base_name.execSQL("DROP TABLE IF EXISTS  contactos");
        this.onCreate(data_base_name);
    }

    //insertar por objetos
    public void insertarContacto(Contacto contacto) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues datos = new ContentValues();

            datos.put("nombre", contacto.getNombre());
            datos.put("telefono", contacto.getTelefono());
            datos.put("direccion", contacto.getDireccion());
            datos.put("email", contacto.getE_mail());
            datos.put("foto", contacto.getImagen());
            db.insert("contactos", null, datos);
            db.close();
        }
    }

    public void modificarContacto(int id, String nom, String tlf,String dir, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", id);
        valores.put("nombre", nom);
        valores.put("telefono", tlf);
        valores.put("direccion", dir);
        valores.put("email", email);
        db.update("contactos", valores, "id + = " + id, null);
        db.close();
    }

    public void borrarContacto(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("contactos", "id="+id, null);
        db.close();
    }

    public void borrarContacto() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("contactos", null, null);
        db.close();
    }
    public Contacto recuperarContacto(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"nombre", "telefono","direccion", "email","foto"};
        Cursor c = db.query("contactos", valores_recuperar, "id=" + id, null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Contacto contacto = new Contacto(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getBlob(4));
        db.close();
        c.close();
        return contacto;
    }

    //Leer todos los contactos
    public List<Contacto> listarContactos() {
        SQLiteDatabase db = getReadableDatabase();
        List<Contacto> alListaContactos = new ArrayList<Contacto>() {

        };

        String[] valoresRecuperar = {"nombre", "telefono","direccion", "email", "foto"};
        Cursor c = db.query("contactos", valoresRecuperar,null, null, null, null, null);
        c.moveToFirst();
        do {    //VER AQUI*****************************************************************
             if(c == null){
                 Contacto contactos = null;
             }else{
                 Contacto contactos = new Contacto(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getBlob(4));
                // insertarContacto(contactos);
                 alListaContactos.add(contactos);
             }
        } while (c.moveToNext());
        db.close();
        c.close();
        return alListaContactos;
    }
}

