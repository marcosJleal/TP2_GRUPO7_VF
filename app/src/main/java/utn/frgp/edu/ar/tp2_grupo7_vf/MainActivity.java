package utn.frgp.edu.ar.tp2_grupo7_vf;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombres, et_apellidos, et_telefono, et_email, et_direccion,fechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nombre y casteo de variables de la parte grafica a codigo
        et_nombres = (EditText) findViewById(R.id.txt_nombres);
        et_apellidos = (EditText) findViewById(R.id.txt_apellidos);
        et_telefono = (EditText) findViewById(R.id.txt_telefono);
        et_email = (EditText) findViewById(R.id.txt_email);
        et_direccion = (EditText) findViewById(R.id.txt_direccion);
        fechaNac = (EditText) findViewById(R.id.date_fechanac);
    }


    //Metodo para el menu que aparezca arriba en el header de la app
    @Override public boolean onCreateOptionsMenu (Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_contactos, mimenu);
        return true;
    }



    //metodo para agregar contactos a la agenda

    public void agregarContactos(View vw){
        if(validarCampos()){
            Intent i = new Intent(this,DatosExtra.class);
            i.putExtra("nombre",et_nombres.getText().toString());
            i.putExtra("apellido",et_apellidos.getText().toString());
            i.putExtra("telefono",et_telefono.getText().toString().trim());
            i.putExtra("email",et_email.getText().toString().trim());
            i.putExtra("direccion",et_direccion.getText().toString());
            i.putExtra("fechaNac",fechaNac.getText().toString().trim());
            startActivity(i);
        }
    }

    public boolean validarCampos(){
        String direccion = et_direccion.getText().toString();


        if(!validarNombreApellido()){
            Toast.makeText(this, "Ingrese un Nombre y un Apellido valido.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!validarEmail()){
            Toast.makeText(this, "Ingrese un Email valido.", Toast.LENGTH_SHORT).show();
            return false;
        }

        /*
        if(!validarTelefono()){
            Toast.makeText(this, "Ingrese un Telefono valido.", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if(!(direccion.length() > 0)){
            Toast.makeText(this, "Ingrese una Direccion valida.", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(!validarFecha()){
            Toast.makeText(this, "Ingrese una Fecha valida.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean validarFecha(){
        String fecha = fechaNac.getText().toString().trim();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date objFecha = null;
        Date fechaHoy = Calendar.getInstance().getTime();
        String fechaHoyString = formato.format(fechaHoy);

        if(!(fecha.length() > 0))
            return false;
        try {
            objFecha = formato.parse(fecha);
            fechaHoy = formato.parse(fechaHoyString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        if(objFecha.after(fechaHoy)){
            return false;
        }

        return true;
    }

    public boolean validarNombreApellido(){
        String nombre = et_nombres.getText().toString();
        String apellido = et_apellidos.getText().toString();
        String patronNumerico = "[0-9]";

        if(nombre.matches(patronNumerico) || apellido.matches(patronNumerico) || !(apellido.length() > 0) || !(nombre.length() > 0)){
            return false;
        }

        return true;
    }

    public boolean validarTelefono(){
        String telefono = et_email.getText().toString().trim();
        String patronTelefono = "[0-9\\-]{9,11}";

        if (telefono.length() > 0)
        {
            int numero = Integer.parseInt(telefono);
            return true;
        }
        return false;
    }

    public boolean validarEmail(){
        String email = et_email.getText().toString().trim();

        if (email.length() > 0)
        {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }


    //Metodo para poder seleccionar y direccionar a los diferentes items del menu
    @Override public boolean onOptionsItemSelected (MenuItem opcion_menu){

        int id=opcion_menu.getItemId();

        if (id==R.id.agregar_contactos){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }

        if (id==R.id.listado_contactos){
            Intent in = new Intent(this, ListadoContactos.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}