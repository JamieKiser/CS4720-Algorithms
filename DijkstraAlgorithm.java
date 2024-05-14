package com.mycompany.homework2;

/**
 * Jamie Kiser
 * Homework 2
 * Dijkstra's Algorithm
 */

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        // Each of these arrays make up the graph:
        
        // array of vertexes
        // each character will represnt 0-n
        // s = 0, t = 1, v = 2, w = 3
        //char V[] = {'s','t','v','w'};
        
        // test 3
        char V[] = {'s','t','v','w', 'u'};
        
        
        // second data set:
        //char V[] = {'s','t','v','w', 'x', 'u'};
        
        // array of edges
        int E[] = {1, 2, 3, 4, 5};
        
        // second data set:
        //int E[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        // array to store the lengths or weights of each edge
        //int LenE[] = {1, 2, 3, 4, 5};
        
        // test 3
        int LenE[] = {3, 5, 1, 2, 4, 6, 2};
        // second data set:
        //int LenE[] = {3, 2, 1, 7, 4, 3, 4, 6, 5, 8};
        
        // FromToE, not sure how to store, will just implement manually
        // FromToE ={1(s,v), 2(v,w), 3(w,t), 4(s,w), 5(v,t)}
        // storing in adjacency matrix
	int adjMat[][] = new int[V.length][V.length];
        
        // 1 at 0-2, 2 at 2-3, 3 at 3-1, 4 at 0-3, 5 at 2-1
        // 1 at s-v, 2 at v-w, 3 at w-t, 4 at s-w, 5 at v-t
        // couldn't figure out how to do this recursively
        //adjMat[0][2] = LenE[0];
        //adjMat[2][3] = LenE[1];
        //adjMat[3][1] = LenE[2];
        //adjMat[0][3] = LenE[3];
        //adjMat[2][1] = LenE[4];
        
        // test 3
        adjMat[0][2] = LenE[0];
        adjMat[2][3] = LenE[1];
        adjMat[3][1] = LenE[2];
        adjMat[0][3] = LenE[3];
        adjMat[2][1] = LenE[4];
        adjMat[3][4] = LenE[5];
        adjMat[1][4] = LenE[6];
        // second data set:
        // 3 at s-v, 2 at s-w, 1 at s-x, 7 at v-t, 4 at v-w, 3 at x-w, 4 at x-u, 
        // 6 at w-t, 5 at w-u, 8 at t-u
        // 3 at 0-2, 2 at 0-3, 1 at 0-2, 7 at 2-1, 4 at 2-3, 3 at 4-3, 4 at 4-5,
        // 6 at 3-1, 5 at 3-5, 8 at 1-5
        
        //adjMat[0][2] = LenE[0];// 3 at s-v
        //adjMat[0][3] = LenE[1];// 2 at s-w
        //adjMat[0][4] = LenE[2];// 1 at s-x
        //adjMat[2][1] = LenE[3];// 7 at v-t
        //adjMat[2][3] = LenE[4];// 4 at v-w
        //adjMat[4][3] = LenE[5];// 3 at x-w
        //adjMat[4][5] = LenE[6];// 4 at x-u
        //adjMat[3][1] = LenE[7];// 6 at w-t
        //adjMat[3][5] = LenE[8];// 5 at w-u
        //adjMat[1][5] = LenE[9];// 8 at t-u
        
        
        // array to store complete distance of edge lengths
	int[] distance = new int[adjMat.length];
        
        // fills array with values other than zero, excluding value 1
        distance = fillDist(distance, adjMat.length);
        
	// array to store whether vertex has been visisted
	boolean[] visited = new boolean[adjMat.length];
	
        // calls to print method using vertex array and uses the
        // getMinDist method to get the shortest distances to each node
        printDist(V, getMinDist(distance, adjMat, visited));
    }
    
    // fills distance array with placeholder values to be replaced with
    // actual distances
    public static int[] fillDist(int[] distance, int length) {
        // must be unexpected value which isn't 0 or negative as will be
        // compared with actual weights of edges
	for (int i = 0; i < length; i++) {
            if(i == 0) continue;
            distance[i] = 10000;
	}
        return distance;
    }
    
    public static int[] getMinDist(int[] distance, int[][] adjMat, 
            boolean[] visited ) {
        // finds the vertex closest to current node
        for(int i = 0; i < adjMat.length; i++) {
            // compares minimum distances between unexplored nodes
            int minDistVertex = getMinDistVertex(distance, visited);
			
            // updates current node to be visited
            visited[minDistVertex] = true;
			
            // goes to each neighboring vertex and updates with shortest path
            // so long as there is a valid edge, loop
            for(int j = 0; j < adjMat.length; j++) {
                // if current slot in adjacency matrix isn't empty and hasn't
                // been visited and isn't the placeholder value, gets distance
                if(adjMat[minDistVertex][j] != 0 && visited[j] == false 
                        && distance[minDistVertex] != 10000) {
                    // stores the new distance value 
                    int newDist = distance[minDistVertex] + 
                            adjMat[minDistVertex][j];
                        // if new distance value less than currently placed 
                        // distance, update with current
                        if(newDist < distance[j]) {
                            distance[j] = newDist;
			}
		}
            }
        }
        return distance;
    }
    
    // compares edges/paths in order to find shortest path
    public static int getMinDistVertex(int[] distance, boolean[] visited) {
	// initialize source counter
        int minVertex = -1;
		
	//traversing through the distance array and finding the least distance vertex whose visited is also false
	for(int i = 0; i < distance.length; i++) {
            // if current node not visited and is either the source or the 
            // distance of the current vertex is less than the next vertex,
            // updates the min vertex to be current, otherwise continue
            if(visited[i] == false && 
                    (minVertex == -1 || distance[i] < distance[minVertex])) {
		minVertex = i;
            }
	}
        return minVertex;
    }
    
    // prints results
    public static void printDist(char[] V, int[] distance) {
        // print out distances
	for(int i = 0; i < V.length; i++) {
            System.out.println("Shortest distance from source node " + V[0] + 
                    " to vertex " + V[i] + " is : " + distance[i]);
        }
    }
}