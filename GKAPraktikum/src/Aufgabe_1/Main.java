package Aufgabe_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("US-ASCII");
		Path file = Paths.get(new File("Graphs/graph01.gka").getAbsolutePath());
		
		String s = "Hellomjm File!";
	

		// try (BufferedWriter writer = Files.newBufferedWriter(file, charset))
		// {
		// writer.write(s, 0, s.length());
		// } catch (IOException x) {
		// System.err.format("IOException: %s%n", x);
		// }
		
		
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

	}

}
