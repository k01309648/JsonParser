package main;
import java.io.IOException;
import java.util.ArrayList;

import airplane.ACDataBaseLine;
import jsonReader.*;
import readStaticData.*;

public class Main {
	
	

	public static void main(String[] args) throws IOException {
		
		
		ArrayList<ACDataBaseLine> db = null;
		System.out.println("Type 1 for Static / 2 For Dynamic Data");
		int mode = System.in.read();
		if(mode =='1') {
			db = ParseStaticData.readStaticData();
		}
		else if(mode == '2') {
			ReadFromJson.transformToRDF();
				ReadFromJson.transformToRDF();
		}
		else {
			System.out.println("ERROR!");
		}
		
		
	
		
		
		

	}

}
