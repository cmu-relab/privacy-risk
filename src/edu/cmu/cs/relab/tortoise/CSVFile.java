package edu.cmu.cs.relab.tortoise;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Reads and writes data to and from a comma-separate values (CSV) file.
 * 
 * @author Travis Breaux
 *
 */

public class CSVFile {
	private ArrayList<String[]> records;
	private String[] header;
	
	/**
	 * The name of the CSV file.
	 */
	
	public final String name;
	
	private CSVFile(String name, String[] header, ArrayList<String[]> records) {
		this.name = name;
		this.records = records;
		this.header = header;
	}
	
	/**
	 * Returns a CSV file read from the given file.
	 * 
	 * @param file the file to read as a CSV file
	 * @return the CSV file content
	 * @throws IOException if an exception occurs while reading the given file
	 */
	
	public static CSVFile read(File file) throws IOException {
		return read(new FileReader(file), file.getName());
	}
	
	/**
	 * Returns a CSV file read from the given reader.
	 * 
	 * @param r the reader to read as a CSV file
	 * @param name the name of the CSV file
	 * @return the CSV file content
	 * @throws IOException if an exception occurs while reading the given file
	 */
	
	public static CSVFile read(Reader r, String name) throws IOException {
		ArrayList<String[]> records = new ArrayList<String[]>();
		
		CSVReader reader = new CSVReader(r);
		String[] header = reader.readNext();
		
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        records.add(nextLine);
	    }
	    reader.close();
		
		return new CSVFile(name, header, records);
	}
	
	/**
	 * Returns the number of records in this CSV file
	 * @return the number of records in this CSV file
	 */
	
	public int size() {
		return records.size();
	}

	/**
	 * Writes this CSV file to the given file name
	 * @param fileName the name of the file to write this CSV file to
	 * @throws IOException if an exception occurs while writing to the given file name
	 */
	
	public void saveAs(String fileName) throws IOException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.println(String.join(",", this.header));
		for (String[] row : records) {
			writer.println(String.join(",", row));
		}
		writer.close();
	}

	/**
	 * Returns the value of the given row and column. The column is indicated by the given header name.
	 * @param header the name indicated the desired column
	 * @param row the numbered row, excluding the header row
	 * @return the value of the given row and column
	 */
	
	public String getField(String header, int row) {
		if (row >= records.size()) {
			return null;
		}
		for (int i = 0; i < this.header.length; i++) {
			if (this.header[i].equals(header)) {
				return records.get(row)[i];
			}
		}
		return null;
	}
}
