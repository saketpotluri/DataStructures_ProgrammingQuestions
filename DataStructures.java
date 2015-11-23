
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saketp on 11/12/2015.
 */
public class DataStructures {
    public void bubbleSort(int[] array) {
        int length = array.length;
        for(int i = 0; i < length; i++) {
            for(int j =  i ; j < length; j++) {
                if(array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }

    public int numWaysStaircase(int n) {
        int[] vals = new int[n + 1];
        return numWays(n,vals);
    }

    private int numWays(int goal, int[] dp) {
        /*5: 13
        7: 44
        11: 504
        12: 927
        3: 4*/
      if(goal < 0) {
          return 0;
      } if(goal == 0) {
            return 1;
        } else if(dp[goal] > 0) {
            return dp[goal];
        } else {

            dp[goal] =  numWays(goal - 1, dp) + numWays(goal - 2, dp) + numWays(goal - 3, dp);
            return dp[goal];
        }
    }

    public void radixSort(int[] num) {
        //1) find max number
        int max = getMax(num);
        int numDigits = (max + "").length();
        for(int i = 0; i < numDigits - 1; i++) {
            //todo
        }
    }

     public List<List<Integer>> threeSum(int[] nums) {
         mergeSort(nums);
         //1) [5 -2 -4 3 2]
         //2) [-4 -2 2 3 5]
         //3)      i j k


         //[1 2 3 4 5]
         // ^   ^     ^
         ArrayList<List<Integer>> list = new ArrayList<>();
         for(int i = 0; i < nums.length;i++) {
             int val = nums[i];
             if(val > 0) {
                 break;
             }
             int j = i + 1;
             int k = nums.length - 1;
             ArrayList<Integer> comb = new ArrayList();
             int sum = 0;
             while(j < k) {
                 int num1 = nums[j];
                 int num2 = nums[k];
                 sum = val + num1 + num2;
                 if(sum == 0) {
                     comb.add(val);
                     comb.add(num1);
                     comb.add(num2);
                     j++;
                 } else {
                     if(sum > 0) {
                         k--;
                     } else {
                         //sum < 0
                         j++;
                     }
                 }

             }
             list.add(comb);
         }
         return list;
     }
    //O(n^2)

    public int getMax(int[] num) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < num.length; i++) {
            if(num[i] > max) {
                max = num[i];
            }
        }
        return max;
    }

    public void bucketSort(int[] num) {
        //find max number in num. Create an array of num + 1 integers.
        int max = getMax(num);
        int[] buckets = new int[max + 1];
        for(int i: num) {
            buckets[i]++;
        }
        printArray(buckets);
        int count = 0;
        for(int i = 0; i < buckets.length; i++) {
            if(buckets[i] != 0) {
                for(int j = 0; j < buckets[i]; j++) {
                    num[count] = i;
                    count++;
                }
            }
        }
    }

    public void quickSort(int[] array) {
        int lo = 0;
        int hi = array.length - 1;
        quickSort(array, lo, hi);
    }

    private void quickSort(int[] array, int lo, int hi) {
      if(lo < hi) {
          int p = partition(array, lo, hi);
          quickSort(array, lo, p - 1);
          quickSort(array, p + 1, hi);
      }
    }

    private int partition(int[] array, int lo, int hi) {
        int pivot = array[hi];
        int i = lo;
        for(int j = lo; j < hi; j++) {
            if(array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, hi);
        return i;
    }

    /** s1 is a substring of s2 if if there exists an index i in s2 such that s2[i] = s1.charAt(0) ---- s2[i + n] = s.charAt(n)
     *
     *  1) find the first incidence of when s1.charAt(0) occurs in s2. Assume that s2 is of length j, s1 is of length i
     *   for i = 0 to j - 1 - k.
     *  2) If we find that the first character matches, iterate through all the next characters to see if there is a match
     *
     *
     */
    //check if s1 is a substring of s2
    public boolean substring(String s1, String s2) {
        int i = s1.length();
        int j = s2.length();

        for(int k = 0; k < j - k; k++) {
            boolean substr = true;
            for(int l = k; l < k + i; l++) {
                if(s1.charAt(l - k) != s2.charAt(l)) {
                    substr = false;
                    break;
                }
            }
            if(substr)
                return true;
        }
        return false;
    }

    //remove a single instance of a node in a linkedlist
    //to remove, we can remove by setting node.next = node.next.next
    //first, need to check if front is not null
    //then, create a temporary variable (current) pointing to the front of the node
    //handle case when front.data = data -> if front.next = front, then front = null. Otherwise, front = front.next
    //while current.next != front (doubly linked list)
    // if(current.next.data == val) {
        //current.next = current.next.next

    public boolean isAdditiveNumber(String num) {
        int length = num.length();
        //need to split string by adding 2 to length - 1 spaces. Can insert spaces after index 0 to length -2
        //need to explore all possibilities
        ArrayList<List<String>> nums = new ArrayList();
        for(int i = 2; i < length - 1; i++) {
            ArrayList<String> list = new ArrayList<String>();
            list = isAdditiveNumber(num, i, 0, length, list);
            //process list here
            System.out.println(list);
            nums.add(list);
        }
        for(int i = 0; i < nums.size(); i++) {
            for(String s: nums.get(i)) {
                String[] comps = s.split(" +");
                System.out.println(s);
                for(String s1: comps) {
                    System.out.print(s1);
                }
                System.out.println();
                boolean valid = true;
                for(int j = 2; j < comps.length; j++) {
                    int val1 = Integer.parseInt(comps[j - 1]);
                    int val2 = Integer.parseInt(comps[j - 2]);
                    int val3 = Integer.parseInt(comps[j]);
                    if(val3 != val1 + val2) {
                        valid = false;
                        break;
                    }
                }
                if(valid) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> isAdditiveNumber(String num, int numSpaces, int start, int length, ArrayList<String> list) {
        if(numSpaces == 0) {
            list.add(num);
        }
        if(numSpaces > 0) {
            for (int i = start; i < num.length() - 1; i++) {
               list = isAdditiveNumber(num.substring(0, i + 1) + " " + num.substring(i + 1, length), numSpaces - 1, start + 1, length, list);
            }
        }
        return list;

    }

    public int lengthOfLIS(int[] nums) {
        int maxLength = 0;
        for(int i = 0; i < nums.length - 1; i++) { //i goes from 0 to len - 2, which is the second to last number
            int current = 0;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[j - 1]) {
                    current++;
                } else {
                    break;
                }
            }
            if(current > maxLength) {
                maxLength = current;
            }
        }
        return maxLength;
    }

    public void summary(int[] array) {
        //int[] a7 = {0,1,2,4,5,7};

        ArrayList<String> notations = new ArrayList<>();

        if(array.length > 1) {
            int start = array[0];
            int currentVal = 0;
            for (int i = 1; i < array.length; i++) {
                 currentVal = array[i];
                int prevVal = array[i - 1];
                if(currentVal != prevVal + 1) {
                    fill(start, prevVal, notations);
                    start = currentVal;
                }
            }

            fill(start, currentVal, notations);

        } else {
            fill(1, 1, notations);
        }
        System.out.println(notations.toString());
    }

    private void fill(int start, int end, ArrayList<String> list) {
        String result = start + " -> " + end;
        list.add(result);
    }



    public int lengthOfLIS2(int[] nums) {
        int maxGreater = 0;
        for(int i = 0; i < nums.length; i++) {
            int numGreater = 1;
            int value = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] > value) {
                    numGreater++;
                    value = nums[j];
                }
            }
            if(numGreater > maxGreater){
                maxGreater = numGreater;
            }
        }
        return maxGreater;
    }

    public int sumMultiples35(int n) {
        int sum = 0;
        for(int i = 3; i < n; i+= 3) { //n/3 iterations
            sum += i;
        }

        for(int i = 5; i < n; i += 5) { //n/5 iterations
            if(i % 15 != 0) { //not divisible by 15, safe to add the value
                sum += i;
            }
        }

        //O(n) algorithm

        //sum of the first j numbers is j(j+1)/2 from 1 to j
        //in this case j = (n-1)/3

        return sum;
    }
//s = abcd
    //charArray(s) = [a, b, c, d]
    //                j  i
    //result = dcb

    //s1 = aedeaf
    /*

    charArray(s1) = [a, e, d, e, a, f]
                          ji
     result = f
     */
    public static String shortestPalindrome(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;
        int j = 0;

        while (i > j) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            else {
                result.append(s.charAt(i));
                i--;
            }
        }
        return result.append(s).toString();
    }
    public int rob(int[] array) {
        //array = [a1, a2, --, an]
        //if we rob ai then we cannot rob either ai+1 or ai-1.
        //Therefore, select highest out of each tuple a(i-1), a(i), a(i+1)
        //however won't work in all cases
        //consider the case [1 2 4 100 2 4]
        //first tuple = [1 2 4] -> max = 4
        //second tuple = [100 2 4] -> max = 100
        //cant select 100 because adjacent to 4, which we burglarized

        //Thus there is an edge case:
        //Let n be the highest tuple n, m be highest tuple of n + 1
        //if m = n + 1, then select 2nd least tuple of n, allows us to select m


        //recursive algorithm

        //essentially want to try and see if we can explore all combinations
        //let a = [1, 2, 4, 5, 11, 12]
        //want to explore all combinations:
        //[1, 4, 11]
        //[2, 5, 12]


        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i < array.length; i++) {
            int val = array[i];
            if(i % 2 == 0) {
                sum1 += val;
            } else {
                sum2 += val;
            }
        }
        return Math.max(sum1, sum2);
    }


    public String shortestPali(String s) {
        char[] values = s.toCharArray();
        //definition: n = length of s
        //charArray = array of chars such that charArray[i] = s.charAt(i)
        //palindrome: charArray[i] = charArray[n - i - 1]. I.e. charArray[0] = charArray[n - 1].
        // want to find the shortest longest Palindrome for a substring of s that already exists
        //want to start at middle charArray[n/2] of s, then start checking charArray[n/2 - i] and charArray[n/2 + i].
        //break the loop as soon we find that charArray[n/2 - i] and charArray[n/2 + i] != each other

        //append to either the front or end of the String
        //consider an example: let s = kjdeaed. s.length = 7, start at charArray[7/2] or charArray[3]
        //
       /*
        charArray[] = [k, j, d, e, a, e, d] -> want to just add j and k to the end of charArray to make it a palindrome
                             l ^   l


        algorithm:
        1) add an arbitrary character at the end the start of array
        2) check if s is a palindrome (1/26) chance of getting a palindrome in this case
        3) if not, check if the failure of palindrome is caused by the character we added by starting at th middle and working down
        4) this is because char[0] != char[s.length - 1]
        5) if the failure is not caused by this, consider ex.  j = [ e, a, f, d]. want to add a
        /

                                  0  1  2  3
                  charArray[] = [ e, a, e, d]  ca[0] = ca[2] ->
                  charArray[] = [d, e, a, e, a, e, d]
        */


return null;
    }



    public static void main(String[] args) {
        DataStructures pq = new DataStructures();
        int[] a1 = {1, 0, 0, 2};
        int[] a2 = {0, 1, 3, 2};
        int[] a3 = {1, 2, 7, 2};
        int[] a4 = {7, 3, 1, 0};
        int[] a5 = {-2, 2, 0, 3, 1, -4, -1, 9, 12, -6, 4, 7, 13};
       int[] a7 = {1, 2, 3, 4, 5, 6} ;

        String s1 = "deaedf";
        //System.out.println(pq.isAdditiveNumber(s1));
/*        System.out.println(pq.lengthOfLIS(a1));
        System.out.println(pq.lengthOfLIS(a2));
        System.out.println(pq.lengthOfLIS(a3));
        System.out.println(pq.lengthOfLIS(a4));
        System.out.println(pq.lengthOfLIS(a5));
        System.out.println(pq.lengthOfLIS2(a6));*/
        System.out.println(pq.shortestPalindrome(s1));


        System.out.println(pq.rob(a1));
        System.out.println(pq.rob(a2));
        System.out.println(pq.rob(a3));
        System.out.println(pq.rob(a4));
        System.out.println(pq.rob(a5));



 /*       System.out.println(pq.threeSum(a5));

        String s2 = "coolio";
        String s1 = "cool";

        System.out.println(pq.substring(s1, s2));*/


      /*  System.out.println("5: " + pq.numWaysStaircase(5));
        System.out.println("7: " + pq.numWaysStaircase(7));

        System.out.println("11: " + pq.numWaysStaircase(11));
        System.out.println("12: " + pq.numWaysStaircase(12));
        System.out.println("3: " + pq.numWaysStaircase(3));*/








       /* *//*------------------------------Merge Sort Case------------------------------*//*
        int[] a6 = new int[100000000];
        for(int i = 0; i < a6.length; i++) {
            int num = (int) (Math.random() * 10000); //random number between 0 and 99
            a6[i] = num;
        }
        long start2 = System.currentTimeMillis();
        pq.mergeSort(a6);
        long end2 = System.currentTimeMillis();
        long diff2 = end2 - start2;
        System.out.println("The time to sort the array using merge sort took: " + diff2 + " ms.");


        int[] a7 = new int[100000000];
        for(int i = 0; i < a7.length; i++) {
            int num = (int) (Math.random() * 100000); //random number between 0 and 99
            a7[i] = num;
        }
        long start3 = System.currentTimeMillis();
        pq.mergeSort(a7);
        long end3 = System.currentTimeMillis();
        long diff3 = end3 - start3;
        System.out.println("The time to sort the array using quick sort took: " + diff3 + " ms.");

        int[] a8 = new int[100000000];
        for(int i = 0; i < a8.length; i++) {
            int num = (int) (Math.random() * 100); //random number between 0 and 99
            a8[i] = num;
        }
        long start4 = System.currentTimeMillis();
        pq.bucketSort(a8);
        long end4 = System.currentTimeMillis();
        long diff4 = end4 - start4;
        System.out.println("The time to sort the array using bucket sort took: " + diff4 + " ms.");


*/
    }



    public void insertionSort(int[] array) {
        for(int i = 1; i < array.length; i++) {
            int j = i;
            while(j > 0 && array[j-1] > array[j]) {
                swap(array, j - 1, j);
                j = j - 1;
            }
        }
    }


    public void mergeSort(int[] array) {
        int[] temp = new int[array.length];

        merge(array, temp, 0, array.length - 1);

    }

    private void merge(int[] array, int[] temp, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            merge(array, temp, start, mid);
            merge(array, temp, mid + 1, end);
          /*  System.out.print("Start: " + start + " Mid: " + mid + " End: " + end + "\t");
            for(int i = start; i <= end; i++) {
                System.out.print(array[i] + " ");
            }*/
            merge(array, temp, start, mid + 1, end);
        }
    }
    private void merge(int[] a, int[ ] tmp, int left, int right, int rightEnd )
    {
       int leftEnd = right - 1;
        int i = left;
        int j = right;
        int k = left;
        while(i <= leftEnd && j <= rightEnd) {
            if(a[i] < a[j]) { //select from lowest, which is a[i]
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        //copy over additional terms
        while(i <= leftEnd) {
            tmp[k++] = a[i++];
        }
        while(j <= rightEnd) {
            tmp[k++] = a[j++];
        }
        for(int l = left; l <= rightEnd; l++) {
            a[l] = tmp[l];
        }
    }




    public void selectionSort(int[] array) {
        //find the smallest number in the unknown part of the array
        //iterate through the arrray
        int startUnsorted = 0;
        for(int i = 0; i < array.length; i++) {
            int smallest = array[startUnsorted];
            int index = startUnsorted;
            for(int j = startUnsorted + 1; j < array.length; j++) {
                if(smallest > array[j]) {
                    smallest = array[j];
                    index = j;
                }
            }
            if(index != startUnsorted) {
                swap(array, index, startUnsorted);
            }
        }
    }

    public void heapSort(int[] array) {
        array = heapify(array);
        System.out.println("Heaped array: ");
        printArray(array);
        System.out.println("----------------------------------------------------------");
        int numNodes = array.length - 1;
        for(int i = 1; i < array.length - 1; i++) {
            //swap min and last in array
            int min = deleteMin(array, array.length - i);
            System.out.println("Iteration: "+ i);
            printArray(array);
        }
        System.out.println("Sorted array: ");
        printArray(array);
    }

    public void siftDown(int[] array, int end) {
        int current = 1;
        int left = 2*current;
        int right = left + 1;
        while(left < end) {
            int newCurrent = -1;

            if(right > end) {
                if(array[left] < array[current]) {
                    System.out.println("Swapping " + array[left] + " and " + array[right]);
                    swap(array, left, current);
                    current = left;
                }
            } else { //if there are both left and right values

                int index = -1;
                int leftVal = array[left];
                int rightVal = array[right];
                int parent = array[current];
                if(leftVal >= parent && rightVal >= parent) {
                    System.out.println("********************************************");
                    printArray(array);
                    System.out.println(parent + " " + leftVal + " " + rightVal);
                    System.out.println("Terminating");
                    System.out.println("********************************************");

                    break;
                }
                if(leftVal <= rightVal) {
                    if(leftVal < parent) {
                        index = left;
                    }
                    newCurrent = left;
                } else {
                    if(rightVal < parent)
                        index = right;
                    newCurrent = right;
                }
                if(index != -1) {
                    printArray(array);
                    System.out.println("swapping indexes: " + current + " and " + index);
                    System.out.println();
                    swap(array, current, index);
                    printArray(array);
                }
            }
            current = newCurrent;
            left = current * 2;
            right = left + 1;

            //update current, left, right
        }
    }

    public int deleteMin(int[] array, int endOfHeap) {
        System.out.println("The minimum element is " + array[1]);
        int min = array[1];
        swap(array, 1, endOfHeap);
        siftDown(array, endOfHeap - 1);

        return min;
    }

    public void printHeap(int[] heap) {
        //start at i, go until heap.length/2
        //make checks to see if left/right are in the array

        //general algorithm
        //1) find out the level of the heap

        int numNodes = heap.length - 1; //since heap[0] is not used
    }



    public int[] heapify(int[] array) {
        int start = array.length/2;
        //Let array = {1, 0, 4, 5, 2}.
        //array.length = 5
        //start at index 2 -> array.length/2, which is the integer 4
        //the left child of the integer at index 2 of the array is at index 4 -> 2
        //the right child of the itneger at index 2 of the array is at index 5 -> undefined
        //so the right child can be unstable whenever you access array.length/2
        //case 1: array.length is even
        //start is also even
        //Let array = {1, 0, 4, 5, 2, 3}.
        //array.length = 6
        //start at index 3 -> array.length/2
        //left child is at index 3 ->
        int[] heapArray = new int[array.length + 1];
        for(int i = 1; i < heapArray.length; i++) {
            heapArray[i] = array[i - 1];
        }
        for(int i = heapArray.length/2; i > 0; i--) {
            int val = heapArray[i];
            int index = percolateDown(heapArray, i, array.length);
            heapArray[index] = val;
        }
        printArray(heapArray);
        return heapArray;
    }



    public int percolateDown(int[] array, int hole,  int limit) {
       while(2*hole < limit) { //safe condition
           int targ = -1; //represents the smaller of the children
           int left = 2 * hole;
           int right = left + 1;
           //select the smaller of the children
           if(right >= limit || array[left] < array[right]) {
               targ = left;
           } else { //select right in case of array[left] = array[right]
               targ = right;
           }
           if(array[targ] < array[hole]) { //if the smaller of left or right child is bigger than the parent
                array[hole] = array[targ];
                hole = targ;
           } else {
               break;
           }
       }
        return hole;
    }

    private void printArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private void swap(int[] array, int i, int j) {
        if(i < array.length && j < array.length) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
         } else {
            System.out.println("invalid numbers");
        }

    }


}
