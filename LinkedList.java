public class LinkedList<T>{
    
            // class Node
            class Node<T> {
                public T data;
                public Node<T> next;
                public Node () {
                    data = null;
                    next = null;
                }
                public Node (T da) {
                    data = da;
                    next = null;
                }
                // Setters/Getters...

                public T getData() {
                    return data;
                }

                public Node<T> getNext() {
                    return next;
                }
                
                public void setData(T da) {
                    this.data = da;
                }

                public void setNext(Node<T> next) {
                    this.next = next;
                }

            }

    // class Linked List
    private Node<T> head;
    private Node<T> curr;
    int size;
    
    public LinkedList () {
        head = curr = null;
        size = 0 ;
    }
    public boolean empty () {
        return head == null;
    }
    public int size ()
    {
        return size;
    }
    
    public boolean last () {
        return curr.next == null;
    }
    public boolean full () {
            return false;
    }
    public void findFirst () {
            curr = head;
    }
    public void findNext () {
            curr = curr.next;
    }
    public T retrieve () {
            return curr.data;
    }
    public void update (T da) {
            curr.data = da;
    }
    
    public void insert (T da) {
            Node<T> tmp;
            if (empty()) {
                    curr = head = new Node<T> (da);
            }
            else {
                    tmp = curr.next;
                    curr.next = new Node<T> (da);
                    curr = curr.next;
                    curr.next = tmp;
            }
            size++ ;
    }

    public void remove () {
            if (curr == head) {
                    head = head.next;
            }
            else {
                    Node<T> tmp = head;

                    while (tmp.next != curr)
                            tmp = tmp.next;

                    tmp.next = curr.next;
            }

            if (curr.next == null)
                    curr = head;
            else
                    curr = curr.next;
            size--;
    }
    
    
}
