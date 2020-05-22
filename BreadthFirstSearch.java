public class BreadthFirstSearch {
    public static void BFS(Graph G, char s){
        int startPosition = G.findVertexByChar(s);
        for(int i = 0; i<G.size(); i++){
            Node current = G.vertexList.get(i).start;
                while(current != null) {
                    current.vertex.color = "white";
                    current.vertex.d = 9999;
                    current.vertex.parent = '-';
                    current = current.next;
                }
        }
        G.vertexList.get(startPosition).start.vertex.color = "gray";
        G.vertexList.get(startPosition).start.vertex.d = 0;
        G.vertexList.get(startPosition).start.vertex.parent = '-';


        LinkedList queue = new LinkedList();
        queue.AppendAtStart(G.vertexList.get(startPosition).start.vertex);
        while(queue.size != 0){
            Vertex u = queue.RemoveFromEnd().vertex;
            Node current = G.vertexList.get(G.findVertexByChar(u.vertex)).start.next;
            while(current != null){
                if (G.vertexList.get(G.findVertexByChar(current.vertex.vertex)).start.vertex.color.equals("white")) {
                    current.vertex.color = "gray";
                    G.vertexList.get(G.findVertexByChar(current.vertex.vertex)).start.vertex.color = "gray";
                    current.vertex.d = G.vertexList.get(G.findVertexByChar(u.vertex)).start.vertex.d + 1;
                    G.vertexList.get(G.findVertexByChar(current.vertex.vertex)).start.vertex.d = u.d+1;
                    current.vertex.parent = G.vertexList.get(G.findVertexByChar(u.vertex)).start.vertex.parent;
                    G.vertexList.get(G.findVertexByChar(current.vertex.vertex)).start.vertex.parent = u.vertex;
                    queue.AppendAtStart(G.vertexList.get(G.findVertexByChar(current.vertex.vertex)).start.vertex);
                }
                current = current.next;
            }
            u.color = "black";
        }
        System.out.println("Vertex | Parent");
        for(int i = 0; i < G.vertexList.size(); i++){
            System.out.println(G.vertexList.get(i).start.vertex.vertex + " | " + G.vertexList.get(i).start.vertex.parent);
        }
    }

    public static void printPath(Graph G, char s, char v){
        if( v == s){
            System.out.print(s);
        } else if (G.vertexList.get(G.findVertexByChar(v)).start.vertex.parent == '-'){
            System.out.println("There is no path from " + s + " to " + v);
        }else {
            printPath(G, s, G.vertexList.get(G.findVertexByChar(v)).start.vertex.parent);
            System.out.print(" -> " + v);
        }
    }
}
