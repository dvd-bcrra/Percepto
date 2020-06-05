package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.percepto.model.Participant;
import com.example.percepto.session.Session;

public class Create_account extends AppCompatActivity {
    CheckBox maceptcond;

    EditText editNombre;
    EditText editApellido;
    EditText editCURP;
    EditText editEdad;
    EditText editMeses;
    EditText editNivel;
    EditText editCI;
    EditText editReexperimentacion;
    EditText editEvitacion;
    EditText editActivacion;
    EditText editAnsiedad;
    EditText editDepresion;
    Spinner spGrupo;

    DBHelper dbHelper;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //CAMPOS
        maceptcond=findViewById(R.id.accept_condition);
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editCURP = findViewById(R.id.editCurp);
        editEdad = findViewById(R.id.editEdad);
        editMeses = findViewById(R.id.editTiempo);
        editNivel = findViewById(R.id.editNivelEdu);
        editCI = findViewById(R.id.editCI);
        editReexperimentacion = findViewById(R.id.editReexperimentacion);
        editEvitacion = findViewById(R.id.editEvitacion);
        editActivacion = findViewById(R.id.editActivacion);
        editAnsiedad = findViewById(R.id.editAnsiedad);
        editDepresion = findViewById(R.id.editDepresion);
        spGrupo = findViewById(R.id.spGroup);

        dbHelper = new DBHelper(this);
        session = new Session(this);



    }

    private final static String[] names = { "Ninguna", "TEPT-ASI", "CH Control Hogar",
            "CF Contol Familia" };

    public void createaccount(View view) {

        Participant participant = new Participant();

        if (editNombre.getText().toString().equals("") ||
                editApellido.getText().toString().equals("") ||
                editCURP.getText().toString().equals("") ||
                editEdad.getText().toString().equals("")
        ){
            maceptcond.setChecked(false);
            Toast.makeText(this,"Introduzca todos los campos obligatorios",Toast.LENGTH_SHORT).show();
        }else{

            participant.setFIRSTNAME(editNombre.getText().toString());
            participant.setLASTNAME(editApellido.getText().toString());
            participant.setCURP(editCURP.getText().toString());
            participant.setAGE(Integer.parseInt(editEdad.getText().toString()));
            participant.setGROUP(spGrupo.getSelectedItem().toString());
            participant.setTIME(editMeses.getText().toString());
            participant.setLEVEL(editNivel.getText().toString());
            participant.setIQ(editCI.getText().toString());
            participant.setREEXP(editReexperimentacion.getText().toString());
            participant.setAVOID(editEvitacion.getText().toString());
            participant.setACTIVE(editActivacion.getText().toString());
            participant.setANX(editAnsiedad.getText().toString());
            participant.setDPRS(editDepresion.getText().toString());

            if(maceptcond.isChecked()){
                dbHelper.AddParticipant(participant);
                Toast.makeText(this,"Participante Agregado",Toast.LENGTH_SHORT).show();
                session.setCurrentParticipantCurp(participant.getCURP());

                Intent Phase1Launcher = new Intent(this, Phase1Launcher.class);
                startActivity(Phase1Launcher);
            }else{
                Toast.makeText(this,"Firmala Gio",Toast.LENGTH_SHORT).show();
            }
        }
    }
}