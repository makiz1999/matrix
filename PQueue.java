public class PQueue {
    public MinHeap PQ;
    public PQueue(int size)
    {
        PQ = new MinHeap(size);
    }

    public Vertex ExtractHighestPriority()
    {
        return PQ.extractMin();
    }
    public void Insert(Vertex v)
    {
        PQ.heapInsert(v);
    }

    public void PrintPriorityQueue()
    {
        PQ.displayHeap();
    }

}