package com.desarrollo.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int CAMERA_PIC_REQUEST=1;

    EditText edt_msj11;
    EditText edt_msj22;
    EditText edt_msj33;
    EditText edt_msj44;
    EditText edt_msj55;
    ImageView img1;
    Bitmap bitmap;
    Button btn_enviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_msj11=(EditText) findViewById(R.id.edt_msj1);
        edt_msj22=(EditText) findViewById(R.id.edt_msj2);
        edt_msj33=(EditText) findViewById(R.id.edt_msj3);
        edt_msj44=(EditText) findViewById(R.id.edt_msj4);
        edt_msj55=(EditText) findViewById(R.id.edt_msj5);
        img1=(ImageView)findViewById(R.id.img_prev);

        btn_enviar=(Button) findViewById(R.id.btn_enviar);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre=edt_msj11.getText().toString();
                String apellido=edt_msj22.getText().toString();
                String telefono=edt_msj33.getText().toString();
                String sexo=edt_msj44.getText().toString();
                String direccion=edt_msj55.getText().toString();
                String imagen  =  img1.toString();


                enviaardatos(nombre,apellido,telefono,sexo,direccion,imagen);
            }
        });


        Button btn_camera=(Button) findViewById(R.id.btn_camera);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            if (resultCode == RESULT_OK) {
               Bitmap imagen = (Bitmap) data.getExtras().get("data");
                ImageView foto = (ImageView) findViewById(R.id.img_prev);
              foto.setImageBitmap(imagen);
               // bitmap    = (Bitmap)data.getExtras().get("data");

             //   img1.setImageBitmap(bitmap);
            }
        }
    }


    private void enviaardatos(String nombre, String apellido, String telefono, String sexo, String direccion,String imagen) {


        String datos =nombre+","+apellido+","+telefono+","+sexo+","+direccion+","+imagen;

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, datos);
            shareIntent.setType("text/plain");

        if(shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(shareIntent,getResources().getText(R.string.send_to)));
        }


    }
}
