public class Quicksort
{
    private int[] arr;
    
    public void sort(int[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        this.arr = values;
        quickSort(0, arr.length - 1);
    }
    
    public void quickSort(int left, int right) {
        int index = partition(left, right);
        if (left < index - 1) { // sort left half
            quickSort(left, index - 1);
        }
        if (right > index) { // sort right half
            quickSort(index, right);
        }
    }
    
    private int partition(int left, int right) {
        int pivot = arr[left + (right - left) / 2];
        while (left <= right) {
            // find element on left that should be on right
            while (arr[left] < pivot) {
                left++;
            }
            
            // find element on right that should be on left 
            while (arr[right] > pivot) {
                right--;
            }
            
            // swap elements, and move left and right indices
            if (left <= right) {
                swap(left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    
    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
