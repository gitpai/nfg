package com.flower.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FlowerUtil {
	public static String Readsh() {  
		Process process = null; 
	   String line="";
	        //List<String> processList = new ArrayList<String>();  
	   try {  
		   process = Runtime.getRuntime().exec("sh /home/zhangtong/caffe/examples/flowers/ResNet/class.sh");  
	      BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));  
	      line = input.readLine();  
	      /*while ((line = input.readLine()) != null) {  
	        	processList.add(line);  
	        }  */
	      input.close(); 
	      } catch (IOException e) {  
	    	  e.printStackTrace();  
	       	}  	  
	      /*for (String line : processList) {  
	          System.out.println(line);  
	        }*/
	        return line;
	    }

}
