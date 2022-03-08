package a.pruebaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.axis.encoding.Base64;
import org.apache.commons.io.FilenameUtils;

public class Base64aWSQ {

   public static void Nom(String[] args) {
      // TODO Auto-generated method stub

      try {
    	  
         StringBuilder base64 = new StringBuilder();
         String line = null;
         File file = new File(args[0]);
         String fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8));
         while ((line = br.readLine()) != null) {
            base64.append(line);
         }
         br.close();

         FileOutputStream archivo = new FileOutputStream(fileNameWithOutExt+".nom");
         archivo.write(Base64.decode(base64.toString()));
         archivo.close();
         System.out.println("Se creo archivo "+fileNameWithOutExt+".nom");
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      
   }

}
