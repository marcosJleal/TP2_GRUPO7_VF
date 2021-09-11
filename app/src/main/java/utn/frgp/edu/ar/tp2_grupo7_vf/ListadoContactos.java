package utn.frgp.edu.ar.tp2_grupo7_vf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListadoContactos extends AppCompatActivity {

    private ListView listViewContactos;

    private String [] archivos  = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        listViewContactos=(ListView)findViewById(R.id.listado);
        archivos = fileList();

        Toast.makeText(this,archivos[0],Toast.LENGTH_LONG).show();
        //Hay Que cargar el arreglo contactos antes de inflar el listView


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_contacto, archivos);
        listViewContactos.setAdapter(adapter);

        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Una vez se selecciona un item del listView, se ejecuta este metodo
                detalleDeContacto(i);
            }
        });
    }

    //Metodo para el menu que aparezca arriba en el header de la app
    @Override public boolean onCreateOptionsMenu (Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_contactos, mimenu);
        return true;
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

    public void detalleDeContacto(int posicion){
        //Con la posicion obtenemos dentro del array archivos el nombre del contacto seleccionado
       String contactoSeleccionado = archivos[posicion];
       Intent i = new Intent(this,DetalleContacto.class);
       //Le agregamos a la vista del intent el nombre del contacto y se lo asignamos al key "contacto"
        i.putExtra("contacto",contactoSeleccionado);
        startActivity(i);
    }


}
