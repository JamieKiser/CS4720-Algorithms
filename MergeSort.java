package cs4720.homework1;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Jamie Kiser
 * CS4720
 * Homework 1
 */
public class MergeSort {

    public static void main(String[] args) throws IOException{
        //int[] testData = {54044, 14108, 79294, 29649, 25260, 60660, 2995, 
           // 53777, 49689, 9083}; //IT WAS BROKEN FOR SO LONG BECAUSE I TYPED
                                 // A NUMBER WRONG
        
        //int[] testData = {30, 28, 26, 24, 22, 40, 50, 100, 120, 119, 118};
        // sets the inversion counter to whatever happens in mergesortcountinv
        int[] testData = fillArray("bigList.txt");
        double invCount = mergeSortCountInv(testData);
        printResults(testData, invCount); // prints results
    }
    
    public static int countRows(String fileName) throws IOException {
        // initialize rows variable
        int rows = 0;
        
        // create new scanner for matrix file
        Scanner matrixScanner = new Scanner(Paths.get(fileName));
        
        // while file has another line, add one to the amount of rows
        while (matrixScanner.hasNextLine()) {
            rows++;
            matrixScanner.nextLine();
        }
        
        // return rows
        return rows;
    }
    
    public static int[] fillArray(String fileName) 
            throws IOException {
        
        // initializes rows and columns by calling to methods
        int rows = countRows(fileName);
        
        // initializes matrix to be filled
        int[] array = new int [rows];
        
        // creates new scanner for matrix file
        Scanner matrixScanner = new Scanner(Paths.get(fileName));
        
        // nested for loop to fill array
        for (int i = 0; i < rows; i++) {
            array[i] = matrixScanner.nextInt();
        }
        
        // returns the matrix
        return array;
    }
    
    public static double mergeSortCountInv(int array[]) {
        // initilize remaining length
        int length = array.length;
        // initialize invCount
        double invCount = 0;
        // merge sort divides array by 2 each iterration
        // if length is greater than 1, repeat
        if (length > 1) {
            // stores middle position of array
            int middle = length / 2;
            // arrays to store left array and right array
            int[] left = new int[middle];
            int[] right = new int [length - middle];
        
            // stores index for left array
            int leftIndex = 0;
            // stores index for right array
            int rightIndex = 0;
        
            // can use leftIndex as variable for loop
            for (; leftIndex < length; leftIndex++) {
                // if left index < middle, takes value from original array
                if (leftIndex < middle) {
                    left[leftIndex] = array[leftIndex];
                } // otherwise goes into right array
                else {
                    right[rightIndex] = array[leftIndex];
                    rightIndex++; // must incriment
                }
            }
            // recursion starts here
            // divides left array in two until unable
            invCount = mergeSortCountInv(left);
            // divides right array in two until unable
            invCount += mergeSortCountInv(right);
            // merges arrays back together
            invCount += merge(left, right, array);
            // adds inversions from left, right, and merged array
        }
        // returns inversion count
        return invCount;
    }
    
    public static double merge(int[] left, int[] right, int[] array) {
        // variable to store left and right size
        // left side is array length divided by 2
        int leftLength = array.length / 2;
        // right side is array length minus the left length
        int rightLength = array.length - leftLength;
        
        
        // variables to hold indexes for counter, left and right indexes
        int i = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int invCount = 0;
        
        // while there are elements in left and right array, add element to 
        // original array
        while (leftIndex < leftLength && rightIndex < rightLength) {
            // comparing number in left and right
            // whichever is smaller is added to orignal array
            if(left[leftIndex] < right[rightIndex]) {
                array[i] = left[leftIndex];
                i++;
                leftIndex++;
            }
            else {
                array[i] = right[rightIndex];
                // inversion will be adding itself to n/2 which is stored in 
                // leftLength and subtracted by the current left index
                invCount += (leftLength - leftIndex);
                i++;
                rightIndex++;
            }
        }
        // these loops handle if there's one element left that cannot
        // be compared to another element
        while(leftIndex < leftLength) {
            array[i] = left[leftIndex];
            i++;
            leftIndex++;
        }
        while(rightIndex < rightLength) {
            array[i] = right[rightIndex];
            i++;
            rightIndex++;
        }
        // returns inversion amounts
        return invCount;
    }
    
    // prints out results
    public static void printResults(int array[], double invCount) {
        
        for (int i = 0; i < array.length; i++) {
            //System.out.printf("%d ", array[i]);
        }
        System.out.println();
        System.out.printf("Inversions: %f",invCount);
    }
}
