package com.example.aula01.aula01;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

    public class SegundaActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        Intent intent = getIntent();

        String s = intent.getStringExtra("EXTRA_NOME");
        String s1 = intent.getStringExtra("EXTRA_EMAIL");
        String s2 = intent.getStringExtra("EXTRA_SENHA");
        String s3 = intent.getStringExtra("EXTRA_TIPO");
        String s4 = intent.getStringExtra("EXTRA_SPINNER");



        TextView textView = (TextView) findViewById(R.id.texto_variavel);
        textView.setText("Nome: "+s);

        TextView textView2 = (TextView) findViewById(R.id.texto_variavel2);
        textView2.setText("Email: "+s1);

        TextView textView3 = (TextView) findViewById(R.id.texto_variavel3);
        textView3.setText("Senha: "+s2);

        TextView textTipo = (TextView) findViewById(R.id.texto_tipo);
        textTipo.setText("Tipo: "+s3);

        TextView textSpinner = (TextView) findViewById(R.id.texto_spinner);
        textSpinner.setText("Curso: "+s4);

    }
}
