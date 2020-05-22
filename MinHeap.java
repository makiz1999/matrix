public class MinHeap {
    public int heapSize;
    public int size;
    public Vertex[] Arr;
    public MinHeap(int size) {
        heapSize = 0;
        this.size = size;
        Arr = new Vertex[size];
    }


    public void heapify(int parent) {
        int Lson, Rson, smallest;
        Vertex temp;
        Lson = 2 * parent + 1;
        Rson = 2 * parent + 2;
        /* find the largest among A[parent], A[lson] and A[rson] */
        if ( Lson <= heapSize - 1 && Arr[Lson].d < Arr[parent].d ) {
            smallest = Lson;
        } else {
            smallest = parent;
        }
        if (Rson <= heapSize - 1 && Arr[Rson].d < Arr[smallest].d ) {
            smallest = Rson;
        }
        if (smallest != parent) {
            temp = Arr[parent];
            Arr[parent] = Arr[smallest];
            Arr[smallest] = temp;
            heapify(smallest);
        }

    }

    public void buildHeap() {
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }


    public void displayHeap() {
        for(int i = 0; i < heapSize; i++){
            System.out.println(Arr[i]);
        }
    }


    public Vertex extractMin() {
        if (heapSize == 0) {
            return null;
        } else {
            Vertex min = Arr[0];
            Arr[0] = Arr[heapSize-1];
            heapSize = heapSize - 1;
            heapify(0);
            return min;
        }
    }

    public void heapInsert(Vertex v) {
        if(heapSize == Arr.length)
            return;
        int parent;
        heapSize = heapSize + 1;
        int i = heapSize - 1;
        parent = (i - 1) / 2;
        while (i > 0 && Arr[parent].d > v.d)
        {
            Arr[i] = Arr[parent];
            i = parent;
            parent = (i - 1) / 2;
        }
        Arr[i] = v;
    }
}
