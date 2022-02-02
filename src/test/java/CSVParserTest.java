package test.java;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testP1.CSVParser;

public class CSVParserTest {

	@Before
	public void setUp() throws Exception {
		CSVParser parser;
	}

	//test to make sure cognates are correctly identified as both languages
	@Test
	public void testCognate() {
		CSVParser parser = new CSVParser("global,total");
		ArrayList<String> temp = parser.translateCSV();
		String[] answer = {"global,en,es", "total,en,es"};
		assertTrue(temp.get(0).equals(answer[0]) && temp.get(1).equals(answer[1]));
	}
	
	//test that numbers are correctly identified as unknown
	@Test
	public void testNumber() {
		
		final int upperbound = 100000;
		Random rand = new Random();
		int[] randInt = {rand.nextInt(upperbound), rand.nextInt(upperbound)};
		String parseString = "" + randInt[0] + "," + randInt[1];
		CSVParser parser = new CSVParser(parseString);
		
		String[] answer = {"" + randInt[0] + ",Unknown", "" + randInt[1] + ",Unknown"};
		ArrayList<String> temp = parser.translateCSV();
		
		assertTrue(temp.get(0).equals(answer[0]) && temp.get(1).equals(answer[1]));
		
	}
	
	//test that the translate function correctly identifies words as a certain language
	@Test
	public void testTranslate() {
		
		CSVParser parser = new CSVParser("University,Dónde");
		ArrayList<String> temp = parser.translateCSV();
		String[] answer = {"University,en", "Dónde,es"};
		
		assertTrue(temp.get(0).equals(answer[0]) && temp.get(1).equals(answer[1]));
		
	}

	
	@Test
	public void nonExistantFile() throws IOException {
		String path;
		
			
			try {
				String path1 = new File(".").getCanonicalPath();
				CSVParser parser = new CSVParser(new File(path1 + "/src/test/java/testcsv/testss.csv"));
				assertTrue(false);
				
			} catch (FileNotFoundException e) {
				
				assertTrue(true);
				
			}

	
		
	}
	@Test
	public void realFile() throws IOException {
		String path;
		
			
			try {
				String path1 = new File(".").getCanonicalPath();
				CSVParser parser = new CSVParser(new File(path1 + "/src/test/java/testcsv/testOrder.csv"));
				assertTrue(true);
				
			} catch (FileNotFoundException e) {
				
				assertTrue(false);
				
			}

	
		
	}
	@Test
	public void testFileTranslate() throws IOException {
		String path1 = new File(".").getCanonicalPath();
		File file1 = new File(path1 + "/src/test/java/testcsv/RYANTEST.csv");
		CSVParser parser = new CSVParser(file1);
		
		ArrayList<String> temp1 = parser.translateCSV();
		String[] answer1 = {"house,en", "deportes,es", "gracias,es", "station,en", "tower,en", "pink,en"};
		
		int x = 1;
		
		for(int i = 0; parser.hasNext(); ++i) {
			if(!temp1.get(i).equals(answer1[i])) {
				x = 0;
			}
		
		}
		
		assertTrue(x == 1);  
		
	}

	//test that the order is maintained when parsing a CSV
	@Test
	public void testOrder() throws IOException {
		String path = new File(".").getCanonicalPath();
		File file = new File(path + "/src/test/java/testcsv/testOrder.csv");
		CSVParser parser = new CSVParser(file);
		String[] answer = {"Talent","Purpose","Guatemala","Tower","Television","1024","Hatsune Miku","Keyboard","Por qué"};
		int bool = 1;
		
		for(int i = 0; parser.hasNext(); ++i) {
			if(!parser.next().equals(answer[i])) {
				bool = 0;
			}
		
		}
		
		assertTrue(bool == 0);
		
		
	}
	

}