package edu.cmu.cs.relab.tortoise.privacyrisk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Provides a naive reader for CSV files to acquire privacy risk score data.
 * 
 * @author CMU RELAB
 * @version 1.0
 *
 */

public class DataSource {
	private TreeMap<String,Integer> headers = new TreeMap<String,Integer>();
	private ArrayList<String[]> data = new ArrayList<String[]>();
	
	private DataSource(String[] header) {
		for (int i = 0; i < header.length; i++) {
			headers.put(header[i], i);
		}
	}
	
	/**
	 * Returns the list of header names for this data source.
	 * @return the list of header names.
	 */
	
	public String[] getHeaders() {
		return headers.keySet().toArray(new String[headers.size()]);
	}
	
	/**
	 * Returns the desired value in the given row and header name.
	 * 
	 * @param row the row containing the desired value
	 * @param header the header name describing the desired value
	 * @return the desired value
	 */
	
	public String get(int row, String header) {
		if (row < data.size()) {
			return data.get(row)[headers.get(header)];
		}
		else {
			return null;
		}
	}
	
	/**
	 * Returns a data source that assumes a comma delimiter. The delimiter
	 * is assumed to separate values in each row of the file.
	 * 
	 * @param file the file containing the data
	 * @return the data source acquired from the given file
	 * @throws IOException thrown, if an error occurs reading the given file
	 */
	
	public static DataSource read(File file) throws IOException {
		return read(file, ",");
	}
	
	/**
	 * Returns a data source using the given delimiter. The delimiter
	 * is assumed to separate values in each row of the file.
	 * 
	 * @param file the file containing the data
	 * @param delim the delimiter to use when reading the file
	 * @return the data source acquired from the given file
	 * @throws IOException thrown, if an error occurs reading the given file
	 */
	
	public static DataSource read(File file, String delim) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		
		// read the first row as the data source headers
		line = in.readLine();
		if (line == null) {
			in.close();
			return null;
		}
		DataSource src = new DataSource(split(line, delim));
		
		// read each record until the file ends
		while ((line = in.readLine()) != null) {
			String[] record = split(line, delim);
			src.data.add(record);
		}
		in.close();
		
		return src;
	}
	
	/**
	 * Returns the number of rows in this data source.
	 * @return the numer of rows in this data source.
	 */
	
	public int size() {
		return data.size();
	}
	
	/**
	 * Splits a line from the file using the given delimiter. This method
	 * attempts to split values that may contain the delimiter and that
	 * may contain quotes, or be quoted.
	 * 
	 * @param line the line from the file to split
	 * @param delim the delimiter that separates values
	 * @return the separated values
	 */
	
	private static String[] split(String line, String delim) {
		ArrayList<String> splits = new ArrayList<String>();
		String[] split = line.split(delim);
		for (int i = 0; i < split.length; i++) {
			if (split[i].startsWith("\"")) {
				String join = split[i].substring(1);
				int j = i;
				while (!split[j].endsWith("\"")) {
					j++;
					join += delim + split[j];
				}
				i = j;
				join = join.substring(0, join.length() - 1).replaceAll("\"\"", "\"");
				splits.add(join);
			}
			else {
				splits.add(split[i]);
			}
		}
		return splits.toArray(new String[splits.size()]);
	}
}
