import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Initializing graph from graph.txt
        File file = new File("src/graph.txt");
        Scanner scanner = new Scanner(file);
        int nVertices = Integer.parseInt(scanner.nextLine());

        Graph graph = new Graph(nVertices, true);
        String vertices = scanner.nextLine();

        vertices = vertices.replaceAll("\\s+","");
        char[] verticesArr = vertices.toCharArray();
        for(int i = 0; i<verticesArr.length; i++){
            graph.addVertex(verticesArr[i]);
        }

        while(scanner.hasNextLine()){
            char[] edge = scanner.nextLine().replaceAll("\\s+","").toCharArray();
            graph.addEdge(edge[0], edge[1], Character.getNumericValue(edge[2]));
        }

        //Initializing BFS
        System.out.println("Breadth First Search");
        BreadthFirstSearch.BFS(graph, 'a');
        System.out.println("Path from a to e");
        BreadthFirstSearch.printPath(graph, 'a', 'e');
        System.out.println("\n---------------------------------");

        //Initializing Dijkstra
        Dijkstra.Dijkstra(graph, 'a');
        Dijkstra.printPath(graph, 'a', 'e');
    }
}
