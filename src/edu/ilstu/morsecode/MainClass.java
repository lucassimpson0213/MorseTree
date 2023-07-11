package edu.ilstu.morsecode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * Created on : May 6, 2023
 * ULID : lssimp1
 * Class: IT179
 */

/**
 * 
 * 
 * main class that runs the whole program.
 * @author lucassimpson33
 *
 */

public class MainClass {

	public static void main(String [] args) {
		MorseTree mt = new MorseTree();
		mt.buildTree();
		takeUserInput(mt);
	}
	
	
	/**
	 * @param mt
	 */
	public static void takeUserInput(MorseTree mt) {
		System.out.println("Please enter an encoded message or stop to exit: ");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		StringBuilder sb = new StringBuilder();//Concatenates the characters using StringBuilder
		String message = decode(sb, line, mt);
		if(!line.equals("stop") &&  message.charAt(0) != 0) {
			System.out.println("Decoded message: " + message);
		}
		else if(message.charAt(0) == 0){
			System.out.println("Please enter a valid Morse Code! ");
		}
		
		while(!line.equals("stop")) {
			
			System.out.println("Please enter an encoded message or stop to exit:");
			line = scan.nextLine();
			StringBuilder str = new StringBuilder();
			String code = decode(str, line, mt);
			if(!line.equals("stop") && code.charAt(0) != 0) {
				System.out.println("Decoded message: " + code);
			}
			else if(code.charAt(0) == 0){
				System.out.println("Please enter a valid Morse Code! ");
			}
			
			
		}
		
		System.out.println("Thank you for using our Decoder! ");
		
		
	}
	
	
	/**
	 * 	* @param using the null MorseTree, like a binary search tree.
	 * 	returns the Morse encoded characters with '-' > '.'.

	 */
	public MorseTree buildTree(MorseTree mt)
	{
		Scanner scan = null;
		try {
			scan = new Scanner(new File("MorseCode.txt"));// reads a file from MorseCode.txt, using the Scanner and the File Class.
			
			while(scan.hasNextLine()) { 
				String line = scan.nextLine();
				Scanner parser = new Scanner(line);
				char character = parser.next().charAt(0);
				String code = parser.next();
				mt.add(character, code);
				
			}
			
			
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		catch(NullPointerException e) {
			System.out.println("Null pointer exception");
			e.printStackTrace();
		}
		finally
		{
			if(scan != null)
			{
				scan.close();
			}
			
		}
		return mt;
	}
	
	
	
	
	public static String decode(StringBuilder sb, String code, MorseTree mt)
	{
		Scanner decoder = new Scanner(code);
		while(decoder.hasNext()) {
			String s = decoder.next();
			char letter = mt.decode(s);
			sb.append(letter);
		}
		
		return sb.toString();
		
	}
}
