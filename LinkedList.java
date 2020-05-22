public class LinkedList {
    public Node start;
    public Node end;
    public int size;

    public LinkedList() {
        start = null;
        end = null;
        size = 0;
    }

    public final Node Search(char key) {
        Node current = start;
        while (current != null && current.vertex.vertex != key) {
            current = current.next;
        }
        return current;
    }

    public final void AppendAtStart(Vertex newItem) {
        Node temp = new Node(newItem);
        if(start == null){
            start = temp;
            end = temp;
        } else {
            start.prev = temp;
            temp.next = start;
            start = temp;
        }
        size++;
    }

    public void AppendAtEnd(Vertex newItem){
        Node temp = new Node(newItem);
        if(start == null){
            start = temp;
            end = temp;
        } else {
            temp.prev = end;
            end.next = temp;
            end = temp;
        }
        size++;
    }

    public Node RemoveFromStart(){
        Node temp = start;
        if(start != null){
            if(start == end){
                start = null;
                end = null;
                size--;
            } else {
                start = start.next;
                start.prev = null;
                size--;
            }
        }
        return temp;
    }

    public Node RemoveFromEnd(){
        Node temp = end;
        if(end != null){
            if(start == end){
                start = null;
                end = null;
                size--;
            } else {
                end.prev.next = null;
                end = end.prev;
                size--;
            }
        } else {
            System.out.println("Empty List");
        }
        return temp;
    }


    public void Delete(char x){
        Node temp = Search(x);
        if(temp == null){
            return;
        } else if (temp != start && temp != end){
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        } else if (temp == start && temp == end){
            start = null;
            end = null;
        } else if (temp == start && temp != end){
            start = start.next;
            start.prev = null;
        } else {
            temp.prev.next = null;
            end = temp.prev;
        }
        size--;
    }

    public final void printEdges(){
        if(start.next != null){
            Node current = start.next;
            while (!(current == null))
            {

                System.out.print(start.vertex.vertex + " -> " + current.vertex + " | " );
                current = current.next;
            }
        }
    }
    public final void ShowLinkedList()
    {
        if (start == null)
            System.out.println("The list is empty!");
        else
        {
            Node current = start;
            System.out.print("Vertex: " + current.vertex.vertex + " | Edge/Weight: ");
            while (!(current.next == null))
            {
                current = current.next;
                System.out.print(current.vertex + " | " );

            }
            System.out.println();
        }
    }

    public final void print(){
        Node current = start;
        while (!(current.next == null))
        {
            current = current.next;
            System.out.print(start.vertex.vertex + " " + current.vertex.vertex + " " + current.vertex.weight );
            System.out.println();

        }
    }
    public void iterate(){
        if(start.next != null) {
            Node current = start.next;
            while (current != null) {
                if (current.vertex.color.equals("white")) {
                    current.vertex.color = "gray";
                    current.vertex.d = start.vertex.d + 1;
                    current.vertex.parent = start.vertex.parent;
                    AppendAtStart(current.vertex);
                }
            }
        }
    }
}

