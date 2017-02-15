package com.example.allende.victor.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;

public class AgregarContacto extends AppCompatActivity implements View.OnClickListener {

    private Contacto contacto;
    private DataBaseHelper dbh = new DataBaseHelper(this);
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etDireccion;
    private EditText etEmail;
    private String nombre;
    private String telefono;
    private String direccion;
    private String eMail;
    private byte[] foto;
    private int id;
    private ImageButton btCamara;
    private ImageView ivImagen;
    private Button btnAgregar;
    private Intent intent;
    private final static int cons = 0;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        cargarFoto();
        inicializarCamposTextos();
        btnAgregar.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick (View view){
              if(contacto!=null){
                  dbh.insertarContacto(contacto);
                  mostarContactosAgregados();
              }else{
                 avisoCamposVacios();
              }
          }
        });
    }

    public Contacto agregarContacto() {

        nombre = etNombre.getText().toString();
        telefono = etTelefono.getText().toString();
        direccion = etDireccion.getText().toString();
        eMail = etEmail.getText().toString();

        if(verificarCampos(nombre) && verificarCampos(telefono) && verificarCampos(direccion) && verificarCampos(eMail)) {
            contacto = new Contacto(nombre, telefono, direccion, eMail, foto);
            Toast t = Toast.makeText(this, nombre + " " + telefono + " " + direccion + " " + eMail, Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            t.show();
        }
        return contacto;
    }

    public void avisoCamposVacios(){
        Toast t = Toast.makeText(this, "Debes rellenar los campos o hacer la fotografía", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        t.show();
    }

    public void inicializarCamposTextos() {
        etNombre.setText("");
        etTelefono.setText("");
        etDireccion.setText("");
        etEmail.setText("");
    }

    public boolean verificarCampos(String texto) {
        if (texto.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void mostarContactosAgregados() {

        Toast t = Toast.makeText(this, "Contacto agregado con éxito", Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
        //Mirar aqui el startActivity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void cargarFoto() {
        btCamara = (ImageButton) findViewById(R.id.ibtCamara);
        btCamara.setOnClickListener(this);

        ivImagen = (ImageView) findViewById(R.id.ivImagen);

    }




    @Override
    public void onClick(View view) {
        int id;
        id = view.getId();
        switch (id) {
            case R.id.ibtCamara:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, cons);
                break;
        }
    }

    //guarda la imagen en un bitmap
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (resultCode == Activity.RESULT_OK) {
            Bundle ext = data.getExtras();
            bitmap = (Bitmap) ext.get("data");
            ivImagen.setImageBitmap(bitmap);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            foto = baos.toByteArray();

           Contacto c = agregarContacto();

            //AQUI LE PASO LA FOTO
            if(foto !=  null){
                contacto.setImagen(foto);
            }else{
                avisoCamposVacios();
            }

        }
    }

}