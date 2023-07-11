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
 * MorseTree class that involves encoding a binary search tree that has encoded letters as dots and dashes.
 * @author lucassimpson33
 *
 */

@SuppressWarnings("unused")
public class MorseTree {
	private Node root;
	
	public MorseTree()
	{
		root = new Node();
	}
	
	
	
	/**
	 * @param User entered character that we're going to assign to the character field.
	 * @param User entered code that we're going to assign to the code field.
	 * 
	 */
	public void add(char character, String code)
	{
		Node letter = new Node(character, code);
		Node temp = root;
		
		add(character ,code,letter, temp);
		
	}
	
	/**
	 * @param character - This is the 
	 * @param code - This is the morse code message to be decoded.
	 * @param letter - The letter that we are going to insert which is the new node.
	 * @param temp - A temporary traversal node used to traverse the tree and add the next character from the list of character encoding.
	 */
	private void add(char character, String code, Node letter, Node temp) {
		if(code.equals(null)) {
			System.out.println("Already Entered");
			return;
		}
		
		else if(temp.left == null && code.charAt(0) == '.' && code.length() == 1){
			temp.left = letter;
		}
		else if(temp.right == null && code.charAt(0) == '-' && code.length() == 1){
			temp.right = letter;
		}
		else if(code.charAt(0) == '-' && temp.right != null) {
			String sub = code.substring(1);
			add(character,sub, letter, temp.right);
		}
		else if(code.charAt(0) == '.' && temp.left != null) {
			String sub = code.substring(1);
			add(character, sub, letter, temp.left);
			
		}
		
		
		
		
		
	}
	/**
	 * @param code
	 * @return
	 */
	public char decode(String code){
		Node temp = root;
		char letter = 0;
		return decode(code, temp);
	}
	
	/**
	 * @param code- This was the message that I decipered using morse code with '-' and '.'.
	 *
	 * @param temp - this was a temporary node that I used to traverse the tree. I didn't want to change root reference to the first node.
	 * @return
	 */
	private char decode(String code, Node temp) {
		if(code.length() == 1 && code.charAt(0) == '.' && temp.left != null) {
			return temp.left.letter;
		}
		else if(code.length() == 1 && code.charAt(0) == '-' && temp.right != null) {
			return temp.right.letter;
		}
		else if(code.charAt(0) == '.' && temp.left != null) {
			String sub = code.substring(1);
			return decode(sub, temp.left);
		}
		else if(code.charAt(0) == '-' && temp.right != null) {
			String sub = code.substring(1);
			return decode(sub, temp.right);
		}
		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Builds a tree from the file that I created, MorseTree.txt in the project folder.
	 */
	public void buildTree()
	{
		Scanner scan = null;
		try {
			scan = new Scanner(new File("MorseCode.txt"));
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner parser = new Scanner(line);
				char character = parser.next().charAt(0);
				String code = parser.next();
				add(character, code);
			}
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class Node{
	
		public char letter;//Letter than represents a letter in a morse code tree.
		public String morseCode;//String representing a sequence of morsecode symbols.
		Node left;
		Node right;
		
		public Node()
		{
			
		}
		
		//constructor
		public Node(char letter, String morseCode) {
			this.letter = letter;
			this.morseCode = morseCode;
		}
		
	}
}
