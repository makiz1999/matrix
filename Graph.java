import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//
public class Graph {
    Scanner scanner;
    int vertices;
    List<LinkedList> vertexList;
    Vertex vertex;
    boolean isDirect;
    int edgeCount = 0;


    //-----CONSTRUCTORS----////
    public Graph(){
        vertexList = new ArrayList<>();
        vertices = 0;
        isDirect = false;
    }

    public Graph(int n){
        vertices = n;
        vertexList = new ArrayList<>();

        scanner = new Scanner(System.in);
        for( int i = 0; i < n; i++){
            System.out.print("Add vertex: ");
            char v = scanner.next().charAt(0);
            vertex = new Vertex(v);
            vertexList.add(new LinkedList());
            vertexList.get(i).AppendAtStart(vertex);

        }
        isDirect = false;
    }

    public Graph(int n, boolean isDirect){
        vertices = n;
        vertexList = new ArrayList<>();

        scanner = new Scanner(System.in);
//        for( int i = 0; i < n; i++){
//            System.out.print("Add vertex: ");
//            char v = scanner.next().charAt(0);
//            vertex = new Vertex(v);
//            vertexList.add(new LinkedList());
//            vertexList.get(i).AppendAtStart(vertex);
//        }
        this.isDirect = isDirect;
    }

    public boolean isDirect(){
        if(isDirect){
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdjacent(char v, char u){
        Node found = null;
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1){
            found = vertexList.get(findVertexByChar(v)).Search(u);
        }
        if(found != null){
            return true;
        } else {
            return false;
        }
    }

    public void neighbors(char v){
        if(findVertexByChar(v) != -1){
            vertexList.get(findVertexByChar(v)).ShowLinkedList();
        }
    }

    public boolean addVertex(char v){
        if(findVertexByChar(v) == -1){
            Vertex vertex = new Vertex(v);
            vertexList.add(new LinkedList());
            vertexList.get(vertexList.size()-1).AppendAtStart(vertex);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeVertex(char v){
        if(findVertexByChar(v) != -1){
            edgeCount = edgeCount - degree(v);
            for(int i = 0; i < vertexList.size(); i++){
                if(vertexList.get(i).start.vertex.vertex == 'h'){
                    vertexList.remove(findVertexByChar(v));
                    continue;
                }
                removeEdge(vertexList.get(i).start.vertex.vertex, 'h');
            }
            //vertexList.remove(findVertexByChar(v));
            return true;
        } else {
            return false;
        }
    }

    public void addEdge(char v, char u){
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1){
            if(isDirect){
                vertexList.get(findVertexByChar(v)).AppendAtEnd(new Vertex(u, 0));
                edgeCount++;
            } else {
                vertexList.get(findVertexByChar(v)).AppendAtEnd(new Vertex(u, 0));
                vertexList.get(findVertexByChar(u)).AppendAtEnd(new Vertex(v, 0));
                edgeCount = edgeCount + 2;
            }
        } else {
            System.out.println("Vertex was not found");
        }
    }

    public void addEdge(char v, char u, int weight){
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1){
            if(isDirect) {
                vertexList.get(findVertexByChar(v)).AppendAtEnd(new Vertex(u, weight));
                edgeCount++;
            } else {
            vertexList.get(findVertexByChar(v)).AppendAtEnd(new Vertex(u, weight));
            vertexList.get(findVertexByChar(u)).AppendAtEnd(new Vertex(v, weight));
            edgeCount = edgeCount + 2;
        }
        } else {
            System.out.println("Vertex was not found");
        }
    }

    public void removeEdge(char v, char u){
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1
                && vertexList.get(findVertexByChar(v)).Search(u) != null){
            if(isDirect) {
                vertexList.get(findVertexByChar(v)).Delete(u);
                edgeCount--;
            } else {
                vertexList.get(findVertexByChar(v)).Delete(u);
                vertexList.get(findVertexByChar(u)).Delete(v);
                edgeCount = edgeCount - 2;
            }
        } else {

        }
    }

    public int getWeight(char v, char u){
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1){
//            System.out.println("Edge weight of " + v + " -> " + u + " is: " +
//                    vertexList.get(findVertexByChar(v)).Search(u).vertex.weight);
            return vertexList.get(findVertexByChar(v)).Search(u).vertex.weight;
        } else {
            System.out.println("Vertex was not found");
            return -1;
        }
    }

    public void setWeight(char v, char u, int weight){
        if(findVertexByChar(v) != -1 && findVertexByChar(u) != -1){
            vertexList.get(findVertexByChar(v)).Search(u).vertex.weight = weight;
        } else {
            System.out.println("Vertex was not found");
        }
    }

    //Using terminology - Graph is empty when it has no edges
    public boolean isEmpty(){
        if(edgeCount == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean isComplete(){
        if(vertexList.size()*(vertexList.size()-1)/2 == edgeCount){
            return true;
        } else {
            return false;
        }
    }

    public void vertices(){
        for(int i = 0; i < vertexList.size(); i++){
            System.out.print(vertexList.get(i).start.vertex.vertex + " ");
        }
    }

    public void edges(){
        System.out.println("Printing Edges:");
        for(int i = 0; i < vertexList.size(); i++){
            vertexList.get(i).printEdges();
        }
    }

    public int degree(char v){
        int count = 0;
        if(findVertexByChar(v) != -1){
            for(int i = 0; i < vertexList.size(); i++){
                if(vertexList.get(i).start.vertex.vertex == v){
                    continue;
                } else if (vertexList.get(i).Search(v) != null){
                    count++;
                }
            }
            return count + vertexList.get(findVertexByChar(v)).size-1;
        } else {
            return -1;
        }
    }

    public int size(){
        return vertexList.size();
    }

    public int nEdges(){
        return edgeCount;
    }

    public void clear(){
        for(int i = 0; i<vertexList.size(); i++){
            vertexList.get(i).start.next = null;
        }
    }

    public boolean vertexExist(char v){
        if(findVertexByChar(v) != -1){
            return true;
        } else {
            return false;
        }
    }

    public void hasEdge(char v, char u){
        if(vertexList.get(findVertexByChar(v)).Search(u) != null){
            System.out.println("There is a link from " + v + " to " + u);
        } else {
            System.out.println("There is no link from " + v + " to " + u);
        }
    }


    public void print(){
        System.out.println("\nPrinting Graph\nVertex | Edge/Weight ( In unweighted graph weight is set to be 0 )");
        for(int i = 0; i < vertexList.size(); i++){
            vertexList.get(i).ShowLinkedList();
        }
    }

    public void printGraph(){
        System.out.println(size());
        vertices();
        System.out.println();
        for(int i = 0; i < vertexList.size(); i++){
            vertexList.get(i).print();
        }

    }


    public int findVertexByChar(char x){
        for(int i = 0; i < vertexList.size(); i++){
            if(vertexList.get(i).start.vertex.vertex == x){
                return i;
            }
        }
        return -1;
    }

    public int vertexPosition(Vertex v){
        for(int i = 0; i < vertexList.size(); i++){
            if(vertexList.get(i).start.vertex.vertex == v.vertex){
                return i;
            }
        }
        return -1;
    }
}
