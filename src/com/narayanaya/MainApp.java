package com.narayanaya;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

public static void main(String[] args) {
	final Path myFile = Paths.get("C:\\Users\\212711129\\Desktop\\uaa4.3\\UPDATED_uaac-create-clients-and-users_300.bat");
	int lineNumWhereToInsert = 0;
	//final String stringToInsert = "call uaac user add bm_operator_1 -p test --emails bm_operator_1@xx.com";

	try {
	    final List<String> lines = Files.lines(myFile).collect(Collectors.toList());
	    if(lines.contains("call uaac member add historian_rest_api.write bm_operator_1")) {
	    	lineNumWhereToInsert=lines.indexOf("call uaac member add historian_rest_api.write bm_operator_1")+1;
	    }
	    for(int i=0;i<300;i++) {
	    	
	    	lines.add(Math.min(lineNumWhereToInsert, lines.size()), "call uaac member add historian_rest_api.write bm_operator_"+(i+2));
	    	System.out.println("bm_operator_"+(i+2));
	    }
	    
	   
	    try (final BufferedWriter out = Files.newBufferedWriter(myFile, Charset.forName("UTF-8"))) {
	        for (final String line : lines) {
	            out.append(line).append(System.lineSeparator());
	        }
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	}	 
}

}
