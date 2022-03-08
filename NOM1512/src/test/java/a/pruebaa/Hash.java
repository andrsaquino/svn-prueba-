package a.pruebaa;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
public class Hash
{
   public static void main(String [] args)
   {
	  if (args.length > 0) 
	  {
		File archivo = new File(args[0]);
		if (!archivo.exists()) 
		{
			System.out.println("No existe el archivo");
		}
		else
		{
			if (args.length>1) 
			{
				String[] values = {"SHA-1","SHA-256","SHA-384","SHA-512","SHA1","SHA256","SHA384","SHA512"};
				boolean contains = Arrays.stream(values).anyMatch(args[1]::equals);
				if (contains) 
				{
					procesarHash(args);
				}
				else
				{
					System.out.println("Valores hash permitidos: SHA1, SHA256, SHA384 y SHA512");
				}
			}
			else
			{
				Base64aWSQ.Nom(args);
			}
		}
	  }
	  else
	  {
		  System.out.println("Debe recibir parametros \n\n *********** Instrucciones ***********");
		  System.out.println("Obtener hash: \n 1. Pasar como parametro ruta del archivo \n 2. Pasar como parametro algoritmo (SHA1, SHA256, SHA384 o SHA512)");
		  System.out.println("Ejemplo: java -jar NOM151.jar  C:\\Users\\Administrador\\Documents\\Prueba.docx SHA256 \n");
		  System.out.println("Obtener archivo.nom:");
		  System.out.println("1. Pasar como parametro ruta de archivo constancia (Base64)");
		  System.out.println("Ejemplo: java -jar NOM151.jar C:\\Users\\Administrador\\Documents\\Constancia.txt");
	  }
   }
   
   public static void procesarHash(String args[])
   {
	   try{
		      MessageDigest messageDigest = MessageDigest.getInstance(obtenerDescripcionHash(args[1]));
		      //leer fichero byte a byte
		      
		         try{
		            InputStream archivo = new FileInputStream(args[0]);
		            byte[] buffer = new byte[1];
		            int fin_archivo = -1;
		            int caracter;
		      
		            caracter = archivo.read(buffer);
		      
		            while( caracter != fin_archivo ) {
		         
		               messageDigest.update(buffer); // Pasa texto claro a la función resumen
		               caracter = archivo.read(buffer);
		            }   
		      
		            archivo.close();
		            byte[] resumen = messageDigest.digest(); // Genera el resumen MD5
		            
		            //Pasar los resumenes a hexadecimal
		            String  hexadecimal= "";
		            for (int i = 0; i < resumen.length; i++)
		            {
		            	hexadecimal += Integer.toHexString((resumen[i] >> 4) & 0xf);
		            	hexadecimal += Integer.toHexString(resumen[i] & 0xf);
		            }
		         
		            try {
		            	
						byte[] encoded = Base64.getEncoder().encode(hexStringToByteArray(hexadecimal));
						String str = new String(encoded, java.nio.charset.StandardCharsets.UTF_8);
						System.out.println("Hash "+args[1]+": "+hexadecimal);
						System.out.println("Base64: "+str);
					} catch (Exception e) {
						System.out.println("Error en obtener bytes");
					}
		         }
		         //lectura de los datos del fichero
		         catch(java.io.FileNotFoundException fnfe) {
		        	System.out.println("Archivo no existe"); 
		         }
		         catch(java.io.IOException ioe) {
		        	 System.out.println("Error "+ioe); 
		         }
		      
		      }   
		      //declarar funciones resumen
		      catch(java.security.NoSuchAlgorithmException nsae) {
		    	  System.out.println("Error "+nsae);
		      }
		      
   }
   
   
   private static String obtenerDescripcionHash(String hash) 
   {
	if (hash.equalsIgnoreCase("SHA-1") || hash.equalsIgnoreCase("SHA1")) 
	{
			return "SHA-1";
	}
	else if (hash.equalsIgnoreCase("SHA-256") || hash.equalsIgnoreCase("SHA256")) 
	{
		return "SHA-256";
	}
	else if (hash.equalsIgnoreCase("SHA-384") || hash.equalsIgnoreCase("SHA384")) 
	{
		return "SHA-384";
	}
	else if (hash.equalsIgnoreCase("SHA-512") || hash.equalsIgnoreCase("SHA512")) 
	{
		return "SHA-512";
	}
	return "";  
   }

public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}


