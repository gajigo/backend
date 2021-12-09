package DAO;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerDAO {
    private String operationFileName;
    private String errorFileName = "error.txt";

    public LoggerDAO(String operationFileName) {
        this.operationFileName = operationFileName;
    }

    public LoggerDAO(String operationFileName, String errorFileName) {
        this(operationFileName);
        this.errorFileName = errorFileName;
    }

    public void logError(String message) {
        this.writeLog(errorFileName, message);
    }

    public void logOperation(String operationType, String operationMessage) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String message = dateFormat.format(date) + ": " + operationType + " " + operationMessage;
        this.writeLog(operationFileName, message);
    }

    public boolean writeLog(String filename, String message) {
        // Salva a lista de modelos para um arquivo
        try {
            PrintWriter file = new PrintWriter(filename);

            file.println(message);

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean clean(String filename) {
        // Limpa o arquivo de usuarios
        // e recarrega a lista para jogar fora os dados
        File file = new File(filename);

        return file.delete();
    }
}
