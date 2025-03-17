import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MergeSortAddition {

    public static int[] createRandomArray(int length) {
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("There is an error in writing to the file");
        }
    }

    public static int[] readArrayFromFile(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            String line;
            int[] array = new int[0];
            while ((line = reader.readLine()) != null) {
                int[] temp = new int[array.length + 1];
                System.arraycopy(array, 0, temp, 0, array.length);
                temp[array.length] = Integer.parseInt(line);
                array = temp;
            }
            return array;

        } catch (IOException e) {
            System.out.println("Error reading from file");
            return new int[0];
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] data) {
        if (data.length < 2) {
            return;
        }
        int mid = data.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[data.length - mid];
        
        System.arraycopy(data, 0, leftHalf, 0, mid);
        System.arraycopy(data, mid, rightHalf, 0, data.length - mid);
        
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        
        mergeArrays(leftHalf, rightHalf, data);
    }

    private static void mergeArrays(int[] left, int[] right, int[] merged) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("#1 Generate a random array and store it in a file.");
        System.out.println("#2 Read an existing file then sort it and then store the sorted array in a new file");
        System.out.println("#3 Read a file and sort it using MergeSort, then save the sorted array to a file");
        
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (option == 1) {
            System.out.print("Write the length of the array: ");
            int arrayLength = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Write the name of the file to save the array to: ");
            String name = scanner.nextLine();

            int[] numbers = createRandomArray(arrayLength);
            System.out.println("Original array:");
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            System.out.println();
            writeArrayToFile(numbers, name);
            System.out.println("Array has been saved to " + name);
        } 
        else if (option == 2) {
            System.out.print("Enter the name of the file you made that you want to read the array from: ");
            String name = scanner.nextLine();
            System.out.print("Enter the name of the file to save the sorted array: ");
            String outputFile = scanner.nextLine();
            
            int[] numbersFromFile = readArrayFromFile(name);
            System.out.println("Original array from file:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            bubbleSort(numbersFromFile);
            System.out.println("Sorted array:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            writeArrayToFile(numbersFromFile, outputFile);
            System.out.println("Sorted array has been saved to " + outputFile);
        } 
        else if (option == 3) {
            System.out.print("Enter the name of the file to read from: ");
            String name = scanner.nextLine();
            System.out.print("Enter the name of the file to save the sorted array: ");
            String outputFile = scanner.nextLine();
            
            int[] numbersFromFile = readArrayFromFile(name);
            System.out.println("Original array from file:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            mergeSort(numbersFromFile);
            System.out.println("Sorted array using MergeSort:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            writeArrayToFile(numbersFromFile, outputFile);
            System.out.println("The sorted array has been saved to " + outputFile);
        } 
        else {
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }
}


/*
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MergeSortAddition {

    public static int[] createRandomArray(int length) {
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("There is an error in writing to the file");
        }
    }

    public static int[] readArrayFromFile(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            String line;
            int[] array = new int[0];
            while ((line = reader.readLine()) != null) {
                int[] temp = new int[array.length + 1];
                System.arraycopy(array, 0, temp, 0, array.length);
                temp[array.length] = Integer.parseInt(line);
                array = temp;
            }
            return array;

        } catch (IOException e) {
            System.out.println("Error reading from file");
            return new int[0];
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] data) {
        if (data.length < 2) {
            return;
        }
        int mid = data.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[data.length - mid];
        
        System.arraycopy(data, 0, leftHalf, 0, mid);
        System.arraycopy(data, mid, rightHalf, 0, data.length - mid);
        
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        
        mergeArrays(leftHalf, rightHalf, data);
    }

    private static void mergeArrays(int[] left, int[] right, int[] merged) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("#1 Generate a random array and store it in a file.");
        System.out.println("#2 Read an existing file then sort it and then store the sorted array in a new file");
        System.out.println("#3 Read a file and sort it using MergeSort, then save the sorted array to a file");
        int option = scanner.nextInt();

        if (option == 1) {
            System.out.print("Write the length of the array ");
            int arrayLength = scanner.nextInt();
            System.out.print("Write the name of the file to save the array to ");
            scanner.nextLine();
            String name = scanner.nextLine();

            int[] numbers = createRandomArray(arrayLength);
            System.out.println("Original array:");
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            System.out.println();
            writeArrayToFile(numbers, name);
            System.out.println("Array has been saved to " + name);
        } 
        else if (option == 2) {
            System.out.print("Enter the name of the file you made that you want to read the array from: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.print("Enter the name of the file to save the sorted array: ");
            String outputFile = scanner.nextLine();
            
            int[] numbersFromFile = readArrayFromFile(name);
            System.out.println("Original array from file:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            bubbleSort(numbersFromFile);
            System.out.println("Sorted array:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            writeArrayToFile(numbersFromFile, outputFile);
            System.out.println("Sorted array has been saved to " + outputFile);
        } 
        else if (option == 3) {
            System.out.print("Enter the name of the file to read from: ");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.print("Enter the name of the file to save the sorted array: ");
            String outputFile = scanner.nextLine();
            
            int[] numbersFromFile = readArrayFromFile(name);
            System.out.println("Original array from file:");
            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            mergeSort(numbersFromFile);
            System.out.println("Sorted array using MergeSort:");

            for (int num : numbersFromFile) {
                System.out.print(num + " ");
            }

            System.out.println();
            writeArrayToFile(numbersFromFile, outputFile);
            System.out.println("The sorted array has been saved to " + outputFile);

        } else {
            System.out.println("Your input is not valid, please input 1, 2, or 3");
        }
    }
}

*/