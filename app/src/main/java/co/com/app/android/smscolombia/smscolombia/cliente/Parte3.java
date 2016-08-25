package co.com.app.android.smscolombia.smscolombia.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import co.com.app.android.smscolombia.smscolombia.R;

public class Parte3 extends AppCompatActivity implements View.OnClickListener{

    Button bManual, bAutomatica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ayuda!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bManual = (Button) findViewById(R.id.bManual);

        bManual.setOnClickListener(this);

        bAutomatica = (Button) findViewById(R.id.bAutomatica);

        bAutomatica.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bManual:

                Intent intent = new Intent(Parte3.this, Parte4.class);
                //       intent.putExtra("pedido", pedido);
                //     intent.putExtra("productos", (Serializable) productos);
                startActivity(intent);
                break;

            case R.id.bAutomatica:

                Intent intent1 = new Intent(Parte3.this, Parte4.class);
                //       intent.putExtra("pedido", pedido);
                //     intent.putExtra("productos", (Serializable) productos);
                startActivity(intent1);
                break;
        }

    }

}
