package edu.cmu.cs.relab.tortoise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DataSource {
	private TreeMap<String,Integer> headers = new TreeMap<String,Integer>();
	private ArrayList<String[]> data = new ArrayList<String[]>();
	
	private DataSource(String[] header) {
		for (int i = 0; i < header.length; i++) {
			headers.put(header[i], i);
		}
	}
	
	public String[] getHeaders() {
		return headers.keySet().toArray(new String[headers.size()]);
	}
	
	public String get(int row, String header) {
		if (row < data.size()) {
			return data.get(row)[headers.get(header)];
		}
		else {
			return null;
		}
	}
	
	public static DataSource read(File file) throws IOException {
		return read(file, "\t");
	}
	
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
	
	public int size() {
		return data.size();
	}
	
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
