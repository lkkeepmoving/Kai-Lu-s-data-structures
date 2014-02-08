import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap
{     
    private ArrayList<Integer> numbers;
    
    // Constructor
    public MinHeap() 
    {
        numbers = new ArrayList<Integer>();
    }
    
    // after insert
    private void moveUp() 
    {
        int k = numbers.size() - 1;
        while (k > 0) 
        {
            int p = (k - 1) / 2;
            int num = numbers.get(k);
            int parent = numbers.get(p);
            if (num < parent) 
            {
                numbers.set(k, parent);
                numbers.set(p, num);
                k = p;
            } 
            else 
            {
                k = 0;
            }
        }
    }
     
    public void push(int num) 
    {
        numbers.add(num);
        moveUp();
    }
     
    private void moveDown() 
    {
        int k = 0;
        int l = 2 * k + 1;
        while (l < numbers.size()) 
        {
            int min = l;
            int r = l + 1;
            if (r < numbers.size()) 
            { 
                // there is a right child
                if (numbers.get(r) < (numbers.get(l))) 
                {
                    min++;
                }
            }
            if (numbers.get(k) > (numbers.get(min))) 
            {
                // switch
                int temp = numbers.get(k);
                numbers.set(k, numbers.get(min));
                numbers.set(min, temp);
                k = min;
                l = 2 * k + 1;
            } 
            else 
            {
                l = numbers.size();
            }
        }
    }
     
    public int pop() throws NoSuchElementException 
    {
        if (numbers.size() == 0) 
        {
            throw new NoSuchElementException();
        }
        if (numbers.size() == 1) 
        {
            return numbers.remove(0);
        }
        int top = numbers.get(0);
        numbers.set(0, numbers.remove(numbers.size() - 1));
        moveDown();
        return top;
    }
    
    public int peek()
    {
        return numbers.get(0);
    }
 
    public int size() 
    {
        return numbers.size();
    }
     
    public boolean isEmpty() 
    {
        return numbers.isEmpty();
         
    }
     
    public String toString() 
    {
        return numbers.toString();
    }
}