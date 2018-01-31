package testutilities;

public enum TestGraphData {

	TestGraph0 (new Character[]  {'A','B','C','D','E','F','G','H'},
			new Character[] {'A','A','A','B','C','D','D','E','F','G','H','H'},
			new Character[] {'B','E','D','E','E','H','G','F','C','H','E','F'},
			new Integer[]   { 4,  7,  2,  2,  4,  4,  1,  2,  1,  2,  5,  1}),
	
	TestGraph1 (new Character[]  {'A','B','C','D','E','F'},
			new Character[]	{'A','D','A','B','B','F','C','C','E','E'},
			new Character[]	{'B','A','C','C','E','B','D','E','F','D'},
			new Integer[]	{ 5,  3,  20, 15, 5,  8,  35, 25, 7,  7});

	private final Character[] vertex;
	private final Character[] source;
	private final Character[] sink;
	private final Integer[] weight;
	
	TestGraphData(Character[] vertices, Character[] source, Character[] sink, Integer[] weight){
		this.vertex = vertices;
		this.source = source;
		this.sink = sink;
		this.weight = weight;
	}
	
	public Character[] getVerticies(){
		return vertex;
	}
	public Character[] getSource(){
		return source;
	}
	public Character[] getSink(){
		return sink;
	}
	public Integer[] getWeights(){
		return weight;
	}
}
