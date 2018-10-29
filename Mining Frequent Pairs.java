package Project1;

import java.io.File;  
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 

public class Project1 {

	public static void main(String[] args) throws Exception {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 1; i <= 3; i++){
			String num = Integer.toString(i);
			String pathname = "input/input_3.txt";
			String out = "output/output"+ ".txt";
			clear(out);
			Datamining(pathname,out);
		}
		
//		
//		String pathname = "/Users/WBQ/Documents/workspace/JustForTest/src/input_1.txt";
//		String out = "output/output"+ ".txt";
//		clear(out);
	
        long endTime = System.currentTimeMillis(); 
        System.out.println("");
        System.out.println("Running time：" + (endTime - startTime) + "ms"); 
	}	
	
	public static void clear(String path) throws Exception{
		
		FileWriter fileWriter = new FileWriter(path);
		BufferedWriter bf = new BufferedWriter(fileWriter);
		bf.write("");
		bf.close();
	}
	public static void Log(String path,String Message) throws Exception{
		
		FileWriter fileWriter = new FileWriter(path,true);
		BufferedWriter bf = new BufferedWriter(fileWriter);
		bf.write(Message + "\n");
		bf.close();
	}
	
	public static int[][] Datamining(String input,String output) throws Exception{
		String out = "";
		int support = 0;
		String Line = "";
		String[] number;
		int[] itemset = new int[10];
		int[][] pairs = new int[100][100];
		ArrayList<Integer> arraysort = new ArrayList<>();
		ArrayList <Integer> FrequentItem = new ArrayList<Integer>();
		
		File filename = new File(input);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(reader);
		Line = br.readLine();
        support = Integer.parseInt(Line); 
        
        while((Line = br.readLine()) != null){
      
         		number = Line.split(",");
         		for(int j = 1; j < number.length; j ++){
         			arraysort.add(Integer.parseInt(number[j]));
             		}
         		Collections.sort(arraysort);
     			for(int m = 0; m < arraysort.size() - 1; m ++){
         			for(int n = m + 1; n < arraysort.size(); n ++){
         				pairs[arraysort.get(m)][arraysort.get(n)] ++;
         			}
     			}
     			arraysort.clear();	
        }
    
  	
        for(int i = 0; i < 99; i++){
        	for(int j =i + 1; j < 100;j ++ ){
        		if (pairs[i][j] >= support ){
        			out = out + "(" + i + "," + j + ")" + pairs[i][j] + "\n";
        		}
        	}
        }
        Log(output,out);
        return pairs;
	}
	
	
}
