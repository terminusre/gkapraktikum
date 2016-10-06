package Aufgabe_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {

	public static void main(String[] args) {
		Graph graph = importGraph(Paths.get(new File("Graphs/graph01.gka").getAbsolutePath()), "graph01");
		graph.display();
	}

	private static Graph importGraph(Path path, String graphId) {
		Graph graph = new SingleGraph(graphId);
		List<String> lines = new LinkedList<String>();

		// read Data
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		// retrieve nodes and edges from file
		Iterator<String> it = lines.iterator();
		Set<String> nodes = new HashSet<String>();
		Set<String> edges = new HashSet<String>();
		Matcher matcher = null;
		String node1;
		String node2;

		while (it.hasNext()) {
			matcher = Pattern.compile("(\\w) -> (\\w);").matcher(it.next());
			if (matcher.matches()) {
				if (!nodes.contains(matcher.group(1))) {
					nodes.add(matcher.group(1));
					System.out.println(matcher.group(1));
					graph.addNode(matcher.group(1));
				}
				if (!nodes.contains(matcher.group(2))) {
					nodes.add(matcher.group(2));
					System.out.println(matcher.group(2));
					graph.addNode(matcher.group(2));
				}
				graph.addEdge(matcher.group(1) + matcher.group(2), matcher.group(1), matcher.group(2));
			} else
				System.out.println(matcher.toString());
		}

		// for (String node : nodes)
		// graph.addNode(node);

		// put edges into Graph
		// for (String edge : edges)
		// graph.addEdge("", "", "");

		return graph;
	}

	public void testio() {
		Charset charset = Charset.forName("US-ASCII");
		Path file = Paths.get(new File("text.txt").getAbsolutePath());

		String s = "Hellomjm File!";

		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
			writer.write(s, 0, s.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

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
