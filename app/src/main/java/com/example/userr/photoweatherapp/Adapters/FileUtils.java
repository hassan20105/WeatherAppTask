package com.example.userr.photoweatherapp.Adapters;

import android.content.Context;
import android.widget.Toast;

import com.example.userr.photoweatherapp.Activities.NewWeatherActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileUtils {

  public static void saveToFile(String text_to_intenal,Context context){
      StringBuilder stringBuilder = new StringBuilder();
      FileOutputStream fileOutputStream = null;
      File file ;
      try {
         // Toast.makeText(context, ""+context.finalFile.getPath(), Toast.LENGTH_SHORT).show();
          File directory = context.getFilesDir();
          file = new File(directory, "mydata5");
          fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_APPEND);
          stringBuilder.append(text_to_intenal);
          PrintWriter writer = new PrintWriter(new OutputStreamWriter(fileOutputStream));
          Toast.makeText(context, "Data Saved Successfully" , Toast.LENGTH_SHORT).show();
          writer.println(stringBuilder);
          writer.close();


      } catch (FileNotFoundException e) {
          e.printStackTrace();
          try {
              fileOutputStream.close();
          } catch (IOException e1) {
              e1.printStackTrace();
          }
      } catch (IOException e) {
          e.printStackTrace();
          try {
              fileOutputStream.close();
          } catch (IOException e1) {
              e1.printStackTrace();
          }
      } finally {
          try {
              fileOutputStream.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }
}
