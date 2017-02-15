package com.example.allende.victor.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class EditorContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_contacto);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_lista_contacto; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compartir_eliminar_contacto, menu);
        return true;

       // Contacto contacto = (Contacto)getIntent().getExtras().getSerializable("contacto");

    }
}
