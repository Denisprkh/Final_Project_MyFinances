package by.prokhorenko.util;

import by.prokhorenko.util.exception.UtilException;

import java.io.*;
import java.util.ArrayList;

public class FileUtilDAO {

    public static String readFile(String link) throws UtilException {

        File file = new File(link);
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader(file)) {

            int smb;

            while ((smb = fileReader.read()) != -1) {
                stringBuilder.append((char) smb);
            }
        } catch (IOException e) {
            String mes = "File reading error";
            throw new UtilException(mes,e);
        }
        return stringBuilder.toString();
    }

    public static boolean writeToFile(String link, String text) throws  UtilException {

        File file = new File(link);
        long fileLengthBeforeRight = file.length();

        try(FileWriter fileWriter = new FileWriter(file,true)){
            fileWriter.append(text);
            fileWriter.append("\n");


        }catch (IOException e){
            String mes = "File writing error";
            throw new UtilException(mes,e);
        }

        return fileLengthBeforeRight != file.length();

    }

    public static void cleanFile(String link) throws UtilException {
        File file = new File(link);

        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.append(" ");
        }catch (IOException e){
            String mes = "File cleaning error";
            throw new UtilException(mes,e);
        }
    }




}
