package co.com.app.android.smscolombia.smscolombia.cliente;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.Documented;

import co.com.app.android.smscolombia.smscolombia.R;
import co.com.app.android.smscolombia.smscolombia.config.UserLocalStore;

public class Recibo extends AppCompatActivity {

    private static final String NOMBRE_CARPETA_APP = "co.com.app.android.smscolombia.smscolombia.cliente";
    private static final String GENERADOS = "MisArchivos";

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLocalStore = new UserLocalStore(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarPDFOnClick(view);
            }
        });
    }

    public void generarPDFOnClick(View v){

        Document document = new Document(PageSize.LETTER);

        String NOMBRE_ARCHIVO = "ReciboPDF.pdf";
        String tarjetaSD= Environment.getExternalStorageDirectory().toString();
        File pdfDir = new File(tarjetaSD + File.separator + NOMBRE_CARPETA_APP);

        if (!pdfDir.exists()){
            pdfDir.mkdir();
        }

        File pdfSubDir = new File(pdfDir.getPath() + File.separator + GENERADOS);
        if (!pdfSubDir.exists()){
            pdfSubDir.mkdir();
        }

        String nombre_completo = Environment.getExternalStorageDirectory() + File.separator + NOMBRE_CARPETA_APP + File.separator
                + GENERADOS + File.separator + NOMBRE_ARCHIVO;

        File outputfile = new File(nombre_completo);
        if(outputfile.exists()){
            outputfile.delete();
        }


        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(nombre_completo));

            //Crear el documento

            document.open();
            document.addAuthor("Gustavo Cárdenas");
            document.addCreator("Gustavo Cárdenas");
            document.addSubject("Importante");
            document.addCreationDate();
            document.addTitle("Titulo");

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            String htmlToPDF="<html><head></head><body><h1>Hola que tal</h1><p> prueba </p></body></html>";
            try {
                worker.parseXHtml(pdfWriter, document, new StringReader(htmlToPDF));
                document.close();
                Toast.makeText(this, "PDF esta generado", Toast.LENGTH_LONG).show();
                muestraPDF(nombre_completo, this);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void muestraPDF(String archivo,Context context){
        Toast.makeText(context, "Leyendo el archivo", Toast.LENGTH_LONG).show();
        File file = new File(archivo);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{

            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(context,"No tiene una app para abrir este tipo de archivo", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userLocalStore.getUserLoggedIn() == true) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_main2, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_Sesion) {
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            startActivity(new Intent(this, MainActivity.class));
        }

        if (id == R.id.iniciar_Sesion) {

            startActivity(new Intent(this, InicioSesion.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
