package com.example.allende.victor.contactapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.allende.victor.contactapp.adaptador.AdaptadorContacto;
import java.util.ArrayList;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {


    private ListView lvListaContacto;
    private DataBaseHelper  dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListaContacto = (ListView) findViewById(R.id.lvContactos);
        dbh = new DataBaseHelper(getApplicationContext());
        mostrarContactos();
        lvListaContacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int posicion, long id) {
                String nombre = ((Contacto)av.getAdapter().getItem(posicion)).getNombre();
                String telefono = ((Contacto)av.getAdapter().getItem(posicion)).getTelefono();
                String eMail = ((Contacto)av.getAdapter().getItem(posicion)).getE_mail();
                String direccion = ((Contacto)av.getAdapter().getItem(posicion)).getDireccion();
                byte[] byteImagen= ((Contacto)av.getAdapter().getItem(posicion)).getImagen();
                //int id = ((Contacto)av.getAdapter().getItem(posicion)).getId();
                Contacto contac = new Contacto(nombre,telefono, eMail, direccion, byteImagen);
                Intent intent = new Intent(MainActivity.this, EditorContacto.class);
                //intent.set("contacto", contac);

                startActivity(intent);

                ver(nombre).show();
            }
        });
    }

    public Toast ver(String c){
        Toast t = makeText(this,c, Toast.LENGTH_LONG);
        return t;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_lista_contacto; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_lista_contacto, menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AgregarContacto.class);
        Toast t = makeText(this,"nuevo", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        switch (item.getItemId()) {
            case R.id.itNuevoContacto:
                t.show();
                this.startActivity(intent);
                return true;
            case R.id.nuevoContacto:
                t.show();
                this.startActivity(intent);
                return true;
            /////AQUI VA LA INFO
            case  R.id.informacion:
                mostrarInfo();
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void mostrarContactos(){
        ArrayList<Contacto> alContacto;
        alContacto = (ArrayList<Contacto>) dbh.listarContactos();
        if(alContacto.isEmpty()){
            Intent intent = new Intent(this, AgregarContacto.class);
            startActivity(intent);
        }else{
            AdaptadorContacto adaptadorContacto = new AdaptadorContacto(this, alContacto);
            lvListaContacto.setAdapter(adaptadorContacto);
        }
    }

    public void mostrarInfo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("AppContact: creada poc Víctor Allende para asignatura de Móviles 2º Dam. copyright@mio")
                .setTitle("Información")
                .setCancelable(false)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
