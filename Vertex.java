public class Vertex {
    char vertex;
    int weight;
    String color;
    int d;
    char parent;

    public Vertex(char vertex){
        this.vertex = vertex;
        weight = 0;
    }

    public Vertex(char vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return vertex + "/" + weight;
    }
}
