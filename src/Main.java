import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static boolean isSubset(String[] keys1, String[] keys2) {
        HashMap<String, Integer> mapSet = new HashMap<String, Integer>();
        String[] minKeys = new String[0];
        String[] maxKeys = new String[0];
        if (keys1.length < keys2.length) {
            maxKeys = keys2;
            minKeys = keys1;
        } else {
            minKeys = keys2;
            maxKeys = keys1;
        }

        for (int i = 0; i < maxKeys.length; i++) {
            String key1 = keys1[i];
            if(mapSet.containsKey(key1)){
                int value = mapSet.get(key1);
                mapSet.put(key1, value + 1);
            } else {
                mapSet.put(key1, 1);
            }
        }

        for(int j = 0; j < minKeys.length; j++) {
            String key2 = minKeys[j];
            if(mapSet.containsKey(key2)) {
                int value = mapSet.get(key2);
                if(value > 0) {
                    mapSet.put(key2, value);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static void swap (int[] tab, int i , int j) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }

    private static int partition(int[] arr, int start, int end) {
        //choose the rightmost element as the pivot in the array
        int pivot = arr[end];
        // The elements lower than pivot will be pushed in left of partIndex
        // The elements rightmost pivot will be pushed in right of partIndex
        // The equals elements can to go in the double sens
        int partIndex = start;
        //  Everytime we find up a lower and equals element than pivot, partIndex is incremented and
        //  this element would be placed before pivot
        for(int i = start; i < end; i++) {
            if(arr[i] < pivot) {
                swap(arr, i, partIndex);
                partIndex ++;
            }
        }
        // Exchange partIndex with pivot
        swap(arr, end, partIndex);
        // Resend partIndex with pivot
        return partIndex;
    }

    public static void quickSort(int[] tab, int start, int end) {
        if(start >= end) {
            return;
        }
        //rearrange the elements on the pivot
        int pivot = partition(tab, start, end);
        //Appears on the subarray containing elements less than the pivot
        quickSort(tab, start, pivot -1);
        // Appears on the subarray containing most elements than the pivot
        quickSort(tab, pivot + 1, end);
    }

    public static int quickSelected(int [] arr, int start, int end, int k) {
        // find the partition
        int partition = partition(arr, start, end);

        // If partition value is equal to the position, return value at k
        if(partition == k-1) {
            return arr[partition];
        }
        //If partition value is less than position, search right side of array
        else if(partition < k-1) {
            return quickSelected(arr, partition + 1, end, k);
        }
        // If partition value is more than position, search left side of the array
        else
            return quickSelected(arr, start, partition - 1, k);
    }

    public static void main(String[] args) {

        System.out.println("Method test of Insert, Search and Autocomplete");
        Trie trie = new Trie();

        trie.insert("Hello");
        trie.insert("World");
        trie.insert("Help");
        trie.insert("Helicopter");
        trie.insert("Zebra");

        System.out.println("Search show Memory address of Help :" + trie.search("Help"));
        System.out.println("Search show Memory address of Helix :" + trie.search("Helix"));

        ArrayList<String> autoComplete = trie.autoComplete("Hel");
        for(String word: autoComplete) {
            System.out.println("Word autoComplete " + word);
        }

        System.out.println("Hello world!");
        String[] tableau1 = {"a", "b", "c", "d", "f"};
        String[] tableau2 = {"a", "b"};
        int[] a = { 0, 5, 2, 1, 6, 3 };
        int [] array = {10, 4, 5, 8, 6, 11, 26};

        quickSelected(array, 0, array.length - 1, array.length - 3);
        System.out.println("Array quick selected: " + Arrays.toString(array));
        quickSort(a, 0, a.length - 1);
        // Show array sorted
        System.out.println("Array quick sorted: " + Arrays.toString(a));

        if (isSubset(tableau1, tableau2)) {
            System.out.println("Le tableau1 contient toutes les données du tableau2.");
        } else {
            System.out.println("Le tableau1 ne contient pas toutes les données du tableau2.");
        }
    }

}