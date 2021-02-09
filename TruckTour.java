/*
Suppose there is a circle. There are N petrol pumps on that circle. Petrol pumps are numbered 0 to N-1 (both inclusive). You have two pieces of information corresponding to each of the petrol pump: (1) the amount of petrol that particular petrol pump will give, and (2) the distance from that petrol pump to the next petrol pump.

Initially, you have a tank of infinite capacity carrying no petrol. You can start the tour at any of the petrol pumps. Calculate the first point from where the truck will be able to complete the circle. Consider that the truck will stop at each of the petrol pumps. The truck will move one kilometer for each litre of the petrol.

Input Format

The first line will contain the value of N.
The next N lines will contain a pair of integers each, i.e. the amount of petrol that petrol pump will give and the distance between that petrol pump and the next petrol pump.

Constraints:

1 <= N <= 10^5
1 <= amount of petrol, distance <= 10^9

Output Format

An integer which will be the smallest index of the petrol pump from which we can start the tour.

Sample Input

3
1 5
10 3
3 4
Sample Output

1
*/

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    static class Node {
        int index, litre, distance;
        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
    /*
     * Complete the truckTour function below.
     */
    static int truckTour(int[][] petrolpumps) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>( 
                new Comparator<Node>() {
                    public int compare(Node a, Node b) 
                    { 
                        if (a.index < b.index) 
                            return -1; 
                        if (a.index > b.index) 
                            return 1; 
                        return 0; 
                    } 
                });
        /*
         * Write your code here.
         */
        int index = -1, max = -1;
        for(int i = 0; i< petrolpumps.length; i++ ) {
            int u[] = petrolpumps[i];
            int diff = u[0]-u[1];
            if(diff >= 0) {
                Node n = new Node(i, diff);
                pq.add(n);
            }
        }
        
        for(Node n : pq) {
            System.out.println(n.index + " -> " + n.distance);
            int petrol = 0, k = n.index, cnt = 0, flag = 0;
            while(cnt < petrolpumps.length) {
                int u[] = petrolpumps[k];
                int totalPetrol = petrol + u[0];
                int mindis = u[1];
                if(totalPetrol < mindis) {
                    flag = 1;
                    break;
                } else {
                    petrol =  totalPetrol - mindis;
                }
                k++;
                if(k == petrolpumps.length){
                    k = 0;
                }
                cnt++;
            }
            if(flag == 0) {
                index = n.index;
                break;
            }
        }
        return index;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] petrolpumps = new int[n][2];

        for (int petrolpumpsRowItr = 0; petrolpumpsRowItr < n; petrolpumpsRowItr++) {
            String[] petrolpumpsRowItems = scanner.nextLine().split(" ");

            for (int petrolpumpsColumnItr = 0; petrolpumpsColumnItr < 2; petrolpumpsColumnItr++) {
                int petrolpumpsItem = Integer.parseInt(petrolpumpsRowItems[petrolpumpsColumnItr].trim());
                petrolpumps[petrolpumpsRowItr][petrolpumpsColumnItr] = petrolpumpsItem;
            }
        }

        int result = truckTour(petrolpumps);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
