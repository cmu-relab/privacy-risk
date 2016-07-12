package edu.cmu.cs.relab.tortoise;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.cmu.cs.relab.tortoise.privacyrisk.DataSource;

public class DataSourceTest {
	
	@Test
	public void test_test() throws IOException {
		DataSource src = DataSource.read(new File("data/test.csv"), ",");
		String[] header = src.getHeaders();
		
		assertEquals(3, header.length);
		assertEquals("header1", header[0]);
		assertEquals("header2", header[1]);
		assertEquals("header3", header[2]);
		
		// test row with value containing space
		assertEquals("item1", src.get(0, header[0]));
		assertEquals("value1", src.get(0, header[1]));
		assertEquals("value 1", src.get(0, header[2]));
		
		// test row with delimiter in value
		assertEquals("item2", src.get(1, header[0]));
		assertEquals("value2", src.get(1, header[1]));
		assertEquals("value,2", src.get(1, header[2]));
		
		// test row with quoted value
		assertEquals("item3", src.get(2, header[0]));
		assertEquals("value3", src.get(2, header[1]));
		assertEquals("value \"3\"", src.get(2, header[2]));
	}
	
	@Test
	public void test_surverydata() throws IOException {
		DataSource src = DataSource.read(new File("data/surveydata.csv"), ",");
		String[] header = src.getHeaders();
		String[] expected_header = { "level", "estimate", "stderror", "tvalue", "pvalue" };
		
		assertEquals(expected_header.length, header.length);
		assertEquals(22, src.size());
	}
	
	@Test
	public void test_surverydata_ratings() throws IOException {
		DataSource src = DataSource.read(new File("data/surveydata_ratings.csv"), ",");
		String[] header = src.getHeaders();
		String[] expected_header = { 
				"gender", "agegroup", "education", "ethnicity",
				"income", "shopping", "risk", "harm", "infotype", 
				"willingness-", "willingness"
		};
		
		assertEquals(expected_header.length, header.length);
		assertEquals(4680, src.size());
	}
}
