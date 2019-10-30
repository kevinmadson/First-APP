package com.example.aula01.aula01;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    public class DBHelper extends SQLiteOpenHelper {



        public static final String DATABASE_NAME = "cadastrosdb.sqlite";
        public static final String CADASTRO_TABLE_NOME = "cadastros";
        public static final String CADASTRO_COLUMN_ID = "id";
        public static final String CADASTRO_COLUMN_NOME = "nome";
        public static final String CADASTRO_COLUMN_EMAIL = "email";
        public static final String CADASTRO_COLUMN_SENHA = "senha";
        public static final String CADASTRO_COLUMN_TIPO = "tipo";
        public static final String CADASTRO_COLUMN_CURSO = "curso";

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME ,null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "create table cadastros " +
                            "(id integer primary key autoincrement, nome, email, senha)"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS cadastros");
            onCreate(db);
        }

        public boolean addCadastro(){

            String nome = campoDeTextNome.getText().toString();
            String email = campoDeTextEmail.getText().toString();
            String senha = campoDeTextSenha.getText().toString();

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contantValues = new ContentValues();
            contantValues.put("nome",nome);
            contantValues.put("email",email);
            contantValues.put("senha",senha);
            db.insert("cadastros", null, contantValues);
            db.close();
            return true;

        }}

    private EditText campoDeTextNome;
    private EditText campoDeTextEmail;
    private EditText campoDeTextSenha;
    private CheckBox aceitaTermos;
    private Button botaoEnviar;
    private Button botaoCancelar;

    private RadioButton RadioButtonAluno;
    private RadioButton RadioButtonProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Cadastro UNIFAVIP")
                        .setContentText("O aplicativo está aberto.");
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(" UNIFAVIP | Devry");


        campoDeTextNome = (EditText) findViewById(R.id.campo_de_texto_nome);
        campoDeTextEmail = (EditText) findViewById(R.id.campo_de_texto_email);
        campoDeTextSenha = (EditText) findViewById(R.id.campo_de_texto_senha);
        botaoEnviar = (Button) findViewById(R.id.botao_enviar);
        botaoCancelar = (Button) findViewById(R.id.botao_cancelar);
        aceitaTermos = (CheckBox) findViewById(R.id.checkbox_termos);

        RadioButtonAluno = (RadioButton) findViewById(R.id.radio_aluno);
        RadioButtonAluno.setChecked(true);
        RadioButtonAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButtonProfessor.setChecked(false);
            }
        });

        RadioButtonProfessor = (RadioButton) findViewById(R.id.radio_professor);
        RadioButtonProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButtonAluno.setChecked(false);
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.cursos_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cursos_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (aceitaTermos.isChecked()) {
                    // voce marcou que concorda com os termos de uso

                    String nome = campoDeTextNome.getText().toString();
                    String email = campoDeTextEmail.getText().toString();
                    String senha = campoDeTextSenha.getText().toString();
                    String spinnervalor = spinner.getSelectedItem().toString();

                    if(nome.equals("") || email.equals("") || senha.equals("")){
                        Toast.makeText(getBaseContext(), "Ops, preencha todos os campos.", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(getBaseContext(), SegundaActivity.class);
                        intent.putExtra("EXTRA_NOME", nome);
                        intent.putExtra("EXTRA_EMAIL", email);
                        intent.putExtra("EXTRA_SENHA", senha);
                        intent.putExtra("EXTRA_SPINNER", spinnervalor);
                        String tipo = RadioButtonAluno.isChecked() ? "Aluno" : "Professor";
                        intent.putExtra("EXTRA_TIPO", tipo);
                        startActivity(intent);
                    }
                }else {
                    // voce nao marcou que concorda com os termos de uso
                    Toast.makeText(getBaseContext(), "Por favor, concorde com os termos de uso.", Toast.LENGTH_LONG).show();
                }

            }
        });


        botaoCancelar.setOnClickListener(new View.OnClickListener() {

            public void onClick (View view){
                finish();
            }
        });
    }

}