package utn.frgp.edu.ar.tp2_grupo7_vf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DatosExtra extends AppCompatActivity {

    private String nombre,apellido,telefono,email,direccion,fechaNac;
    private RadioButton rbPrimIncomp,rbPrimCompleto,rbSecunIncom,rbSecunComp,rbOtros;
    private CheckBox chkDeporte,chkMusica,chkArte,chkTecnologia;
    private Switch DeseoInfo;
    private String [] archivos = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_extra);

        nombre=getIntent().getStringExtra("nombre");
        apellido=getIntent().getStringExtra("apellido");
        telefono=getIntent().getStringExtra("telefono");
        email=getIntent().getStringExtra("email");
        direccion=getIntent().getStringExtra("direccion");
        fechaNac=getIntent().getStringExtra("fechaNac");
        archivos = fileList();

        //Busqueda de elementos del front
        rbPrimIncomp=(RadioButton) findViewById(R.id.RbPrimarioIncompleto);
        rbPrimCompleto=(RadioButton) findViewById(R.id.RbPrimarioCompleto);
        rbSecunComp=(RadioButton) findViewById(R.id.RbSecundarioCompleto);
        rbSecunIncom=(RadioButton) findViewById(R.id.RbSecundarioIncompleto);
        rbOtros=(RadioButton) findViewById(R.id.RbOtros);
        chkDeporte=(CheckBox) findViewById(R.id.chkDeporte);
        chkMusica=(CheckBox) findViewById(R.id.chkMusica);
        chkArte=(CheckBox) findViewById(R.id.chkArte);
        chkTecnologia=(CheckBox) findViewById(R.id.chkTecnologia);
        DeseoInfo=(Switch) findViewById(R.id.switch1);
    }

    public void Guardar(View vw){
        String rbCheck="";
        String check="";
        String DeseaRecibirInfo="No";
        String infoContacto;
        String nombreArchivo;

        if(rbPrimIncomp.isChecked())rbCheck="Primario incompleto";
        if(rbPrimCompleto.isChecked())rbCheck="Primario completo";
        if(rbSecunIncom.isChecked())rbCheck="Secundario incompleto";
        if(rbSecunComp.isChecked())rbCheck="Primario completo";
        if(rbOtros.isChecked())rbCheck="Otros";
        if(chkTecnologia.isChecked())check=check+" Tecnologia";
        if(chkArte.isChecked())check=check+" Arte";
        if(chkMusica.isChecked())check=check+" Musica";
        if(chkDeporte.isChecked())check=check+" Deporte";
        if(DeseoInfo.isChecked())DeseaRecibirInfo="Si";

        try {
            //Guardo el contacto
            nombreArchivo = nombre+apellido+" - "+email+".txt";
            if(!ArchivoExiste(archivos, nombreArchivo)){
                OutputStreamWriter archivoContact= new OutputStreamWriter(openFileOutput(nombreArchivo, Activity.MODE_PRIVATE));
                infoContacto = "Nombre: "+nombre+"\n"+"Apellido: "+apellido+"\n"+"Telefono: "+telefono+"\n"+"Email: "+email+"\n"+"Direccion: "+direccion+"\n"+"Fecha de nacimiento: "+fechaNac+"\n"+"Nivel de estudio alcanzado: "+rbCheck+"\n"+"Intereses: "+check+"\n"+"Deseo recibir info: "+DeseaRecibirInfo;
                archivoContact.write(infoContacto);
                archivoContact.flush();
                archivoContact.close();
                Toast.makeText(this,infoContacto,Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Ya tiene un contacto con estos datos",Toast.LENGTH_LONG).show();
            }

        }catch(IOException ex1){
            ex1.printStackTrace();
        }
        Toast.makeText(this,"guardado",Toast.LENGTH_LONG).show();
        finish();
    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i=0;i< archivos.length;i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }





}