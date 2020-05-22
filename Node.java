public class Node
{
    public Vertex vertex;
    public Node prev;
    public Node next;

    public Node(Vertex theItem)
    {
        vertex = theItem;
        prev = null;
        next = null;
    }
}

