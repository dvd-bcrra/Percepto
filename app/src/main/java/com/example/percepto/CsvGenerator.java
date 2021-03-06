package com.example.percepto;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.percepto.model.Evaluation1;
import com.example.percepto.model.Evaluation2;
import com.example.percepto.model.Participant;
import com.example.percepto.model.Record1;
import com.example.percepto.model.Record2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CsvGenerator {
    Context context;
    private File folder;
    private Participant participant;
    private Evaluation1 evaluation1;
    private Evaluation2 evaluation2;
    private String NombreArchivo;
    public CsvGenerator(String NombreArchivo, Participant participant, Evaluation1 evaluation1, Context context) {
        this.participant = participant;
        this.evaluation1 = evaluation1;
        this.NombreArchivo = NombreArchivo;
        this.context = context;
    }

    public CsvGenerator(String NombreArchivo, Participant participant, Evaluation2 evaluation2, Context context) {
        this.participant = participant;
        this.evaluation2 = evaluation2;
        this.NombreArchivo = NombreArchivo;
        this.context = context;
    }

    public void GenerarEvaluacion1(){

        try {

            FileWriter writer = new FileWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),NombreArchivo));

            //Almacenar datos personales
            writer.append("Nombre").append(",").append(participant.getFIRSTNAME()).append("\n");
            writer.append("Apellido").append(",").append(participant.getLASTNAME()).append("\n");
            writer.append("Curp").append(",").append(participant.getCURP()).append("\n");
            writer.append("Edad").append(",").append(Integer.toString(participant.getAGE())).append("\n");
            writer.append("Grupo").append(",").append(participant.getGROUP()).append("\n");
            writer.append("Meses en la institucion").append(",").append(participant.getTIME()).append("\n");
            writer.append("Nivel educativo").append(",").append(participant.getLEVEL()).append("\n");
            writer.append("Coeficiente intelectual").append(",").append(participant.getIQ()).append("\n");
            writer.append("Reexperimentacion").append(",").append(participant.getREEXP()).append("\n");
            writer.append("Evitacion").append(",").append(participant.getAVOID()).append("\n");
            writer.append("Activacion").append(",").append(participant.getACTIVE()).append("\n");
            writer.append("Ansiedad").append(",").append(participant.getANX()).append("\n");
            writer.append("Depresion").append(",").append(participant.getDPRS()).append("\n\n");

            //Almacenar datos de la evaluacion
            writer.append("Evaluacion 1 - ").append(evaluation1.getDATE()).append("\n");
            writer.append("Palabra").append(",").append("Puntuacion").append("\n");
            for (Record1 record1 : evaluation1.getRecords()) {
                writer.append(record1.getWORD()).append(",").append(Integer.toString(record1.getSCORE())).append("\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void GenerarEvaluacion2(){

        try {

            FileWriter writer = new FileWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),NombreArchivo),true);

            writer.append("\n\n");

            //Almacenar datos de la evaluacion
            writer.append("Evaluacion 2 - ").append(evaluation2.getDATE()).append("\n");
            writer.append("Palabra correcta").append(",").append("Palabra seleccionada").append(",").append("Tiempo transcurrido").append(",").append("Evaluacion").append("\n");
            for (Record2 record2 : evaluation2.getRecords()) {
                writer.append(record2.getWORD()).append(",");
                writer.append(record2.getSELECTED_WORD()).append(",");
                writer.append(record2.getTIME()).append(",");
                writer.append(record2.getIS_CORRECT()).append("\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
