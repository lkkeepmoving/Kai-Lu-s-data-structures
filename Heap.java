import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap
{     
    private ArrayList<Integer> numbers;
    private int type;
    
    // Constructor
    public Heap(int type) 
    {
        numbers = new ArrayList<Integer>();
        this.type = type;
    }
    
    private Boolean chooseType(int a, int b){
        if(type == 1){
            if(a > b){
                return true;
            }
            else 
                return false;
        }
        if(type == 2){
            if(a > b){
                return false;
            }
            else 
                return true;
        }
        return false;
    }
    //
    private void siftUp() 
    {
        int k = numbers.size() - 1;
        while (k > 0) 
        {
            int p = (k - 1) / 2;
            int num = numbers.get(k);
            int parent = numbers.get(p);
            if (chooseType(num, parent)) 
            {
                // swap
                numbers.set(k, parent);
                numbers.set(p, num);
                 
                // move up one level
                k = p;
            } 
            else 
            {
                break;
            }
        }
    }
     
    public void insert(int num) 
    {
        numbers.add(num);
        siftUp();
    }
     
    private void siftDown() 
    {
        int k = 0;
        int l = 2 * k + 1;
        while (l < numbers.size()) 
        {
            int max=l, r=l+1;
            if (r < numbers.size()) 
            { // there is a right child
                if (chooseType(numbers.get(r),numbers.get(l))) 
                {
                    max++;
                }
            }
            if (chooseType(numbers.get(max),numbers.get(k))) 
            {
                // switch
                int temp = numbers.get(k);
                numbers.set(k, numbers.get(max));
                numbers.set(max, temp);
                k = max;
                l = 2*k+1;
            } 
            else 
            {
                break;
            }
        }
    }
     
    public int delete() throws NoSuchElementException 
    {
        if (numbers.size() == 0) 
        {
            throw new NoSuchElementException();
        }
        if (numbers.size() == 1) 
        {
            return numbers.remove(0);
        }
        int hold = numbers.get(0);
        numbers.set(0, numbers.remove(numbers.size()-1));
        siftDown();
        return hold;
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