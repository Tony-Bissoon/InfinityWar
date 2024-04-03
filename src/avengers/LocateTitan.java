package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. generators (int): number of generators (vertices in the graph)
 *    2. generators lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. generators lines, each with generators (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for generators generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {

    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        StdIn.setFile(inputFile);
        StdOut.setFile(outputFile);

        int generators = StdIn.readInt();
        int[] generatorNum = new int[generators];
        double[] funcionalityValues = new double[generators];
        for(int i = 0; i < generators; i++){
            generatorNum[i] = StdIn.readInt();
            funcionalityValues[i] = StdIn.readDouble();
        }

        int[][] energyCost = new int[generators][generators];
        for(int i =0; i < generators; i++){
            for(int j = 0; j < generators; j++){
                energyCost[i][j] = StdIn.readInt();
                energyCost[i][j] /= (funcionalityValues[i]* funcionalityValues[j]);
                
                //StdOut.print(energyCost[i][j] + " ");

            }
            //StdOut.println();
        }

        int[] minCost = new int[generators];
        boolean[] DijkstraSet = new boolean[generators];

        for (int i = 0; i < generators; i++) {
            if (i == 0) {
                minCost[i] = 0;
            } else {
                minCost[i] = Integer.MAX_VALUE;
            }
        }


        int currents = 0;

        for(int i = 0; i< generators - 1 ; i++){
            
            currents = gMinCostN(minCost,DijkstraSet);
            
            DijkstraSet[currents] = true;

            for(int w =0; w < generators; w++){
                if((minCost[w] > energyCost[currents][w] + minCost[currents]) && (DijkstraSet[w] == false) && (minCost[currents] != Integer.MAX_VALUE) && (energyCost[currents][w] != 0))
                    minCost[w] = minCost[currents] + energyCost[currents][w];
                } 

            }

            StdOut.print(minCost[generators - 1]);
            }

            public static int gMinCostN(int [] p, boolean [] c)
            {
            int mind = 0; 
            int mval = Integer.MAX_VALUE;
            for(int x = 0; x < p.length; x++)
                {
                if(c[x] == false && p[x] <= mval)
                    {
                    mval = p[x];
                    mind = x;
                    }
                }
            return mind;
            }

        }

