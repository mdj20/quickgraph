package com.mdj20.quickgraph.quickgraph.testutilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.mdj20.quickgraph.quickgraph.main.Edge;
import com.mdj20.quickgraph.quickgraph.main.DefaultEdge;



public class UnivAvengersGraphData {
	
	public final static String  baseURL = "http://www.info.univ-angers.fr/pub/porumbel/graphs/";
	public final static String dsjc5001col = "dsjc500.1.col";
	public final static char edgeLinePrefix = 'e';
	
	public static BufferedReader getGraphData(String urlSuffix) {
		BufferedReader file = null;
		URL url;
		try {
			url = new URL(baseURL+urlSuffix);
			file = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;	
	}
	
	private static List<com.mdj20.quickgraph.quickgraph.main.Edge<Integer>> chomp(BufferedReader bReader) throws IOException {
	
		LinkedList<Edge<Integer>> edgeList = new LinkedList<Edge<Integer>>();
		String line;
		while((line=bReader.readLine())!=null) {
			if(line.charAt(0)==edgeLinePrefix) {
				String splitLine[] =line.split(" ");
				edgeList.add(new DefaultEdge<Integer>(Integer.valueOf(splitLine[1]),Integer.valueOf(splitLine[2])));
			}
		}
		return edgeList;
	}
	
	public static List<Edge<Integer>> getEdgeList(String urlSuffix){
		BufferedReader reader = getGraphData(urlSuffix);
		List<Edge<Integer>> list = new LinkedList<Edge<Integer>>();
		try {
			list = chomp(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private static void printEdge(List<Edge<Integer>> list) {
		for(Edge<Integer> edge:list) {
			System.out.println(edge.getVertex(0)+" "+edge.getVertex(1));
		}
	}
	
	
	public static void main(String args[]) {
		printEdge(getEdgeList(dsjc5001col));
	}
	
	

}
