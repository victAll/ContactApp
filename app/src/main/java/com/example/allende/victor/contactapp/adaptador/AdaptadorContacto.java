package com.example.allende.victor.contactapp.adaptador;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allende.victor.contactapp.Contacto;
import com.example.allende.victor.contactapp.R;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by allen on 13/02/2017.
 */

public class AdaptadorContacto extends ArrayAdapter implements Serializable {

    Activity context;
    ArrayList<Contacto> alContacto = new ArrayList();

    public AdaptadorContacto(Activity context, ArrayList alContacto){
        super(context, R.layout.adaptador_lista, alContacto);
        this.context = context;
        this.alContacto = alContacto;

    }

    public View getView(int posicion, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptador_lista, null);

        TextView tvNombreContacto = (TextView) item.findViewById(R.id.tvNombreContacto);
        tvNombreContacto.setText(alContacto.get(posicion).getNombre().toString());

        TextView tvNumeroContacto = (TextView) item.findViewById(R.id.tvNumeroContacto);
        tvNumeroContacto.setText(alContacto.get(posicion).getTelefono());

        TextView tvDirContacto = (TextView) item.findViewById(R.id.tvDirContacto);
        tvDirContacto.setText(alContacto.get(posicion).getDireccion());

        TextView tvEmailContacto = (TextView) item.findViewById(R.id.tvEmailContacto);
        tvEmailContacto.setText(alContacto.get(posicion).getE_mail());



        ImageView ivImagenContacto = (ImageView) item.findViewById(R.id.ivImagenContacto);
        //alContacto.get(posicion).getImagen() es un byte[]
        Bitmap bitmap = BitmapFactory.decodeByteArray(alContacto.get(posicion).getImagen(), 0, alContacto.get(posicion).getImagen().length);
        ivImagenContacto.setImageBitmap(bitmap);
        //ivImagenContacto.setImageBitmap((Bitmap)alContacto.get(posicion).getImagen());
        return item;
    }

}
