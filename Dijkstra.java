public class Dijkstra {
    public static void Dijkstra(Graph G, char s){
        InitializeSingleSource(G, s);

        PQueue pQueue = new PQueue(G.size());
        for(int i = 0; i < G.size(); i++){
            pQueue.Insert(G.vertexList.get(i).start.vertex);
        }

        while(pQueue.PQ.heapSize != 0){
            Vertex u = pQueue.ExtractHighestPriority();
                Node current = G.vertexList.get(G.findVertexByChar(u.vertex)).start;
                Node next = G.vertexList.get(G.findVertexByChar(u.vertex)).start.next;
                while (next != null) {
                    Relax(G, G.vertexList.get(G.findVertexByChar(u.vertex)).start.vertex
                            , G.vertexList.get(G.findVertexByChar(next.vertex.vertex)).start.vertex);
                    next = next.next;
                }

        }
    }
    public static void InitializeSingleSource(Graph G, char s){
        int startPosition = G.findVertexByChar(s);
        for(int i = 0; i<G.size(); i++){
            Node current = G.vertexList.get(i).start;
            while(current != null) {
                current.vertex.d = 9999;
                current.vertex.parent = '-';
                current = current.next;
            }
        }
        G.vertexList.get(startPosition).start.vertex.d = 0;
        G.vertexList.get(startPosition).start.vertex.parent = '-';
    }


    public static void Relax(Graph G, Vertex u, Vertex v){
        if(v.d > u.d + G.getWeight(u.vertex,v.vertex)){
            if(v.d == 9999){
                G.vertexList.get(G.findVertexByChar(v.vertex)).start.vertex.d = 0 + G.getWeight(u.vertex,v.vertex);
                G.vertexList.get(G.findVertexByChar(v.vertex)).start.vertex.parent = u.vertex;
            } else {
                G.vertexList.get(G.findVertexByChar(v.vertex)).start.vertex.d =
                        G.vertexList.get(G.findVertexByChar(u.vertex)).start.vertex.d + G.getWeight(u.vertex,v.vertex);
                G.vertexList.get(G.findVertexByChar(v.vertex)).start.vertex.parent = u.vertex;
            }
        } else {
            return;
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
    public static void printTable(Graph G){
        for(int i = 0; i < G.size(); i++){
            System.out.println(G.vertexList.get(i).start.vertex.vertex + " | " + G.vertexList.get(i).start.vertex.parent + " | "
                    + G.vertexList.get(i).start.vertex.d);
        }

    }
}
