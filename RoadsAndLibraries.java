package week5.intq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/*
The Ruler of HackerLand believes that every citizen of the country should have access to a library. Unfortunately,
HackerLand was hit by a tornado that destroyed all of its libraries and obstructed its roads! As you are the greatest
programmer of HackerLand, the ruler wants your help to repair the roads and build some new libraries efficiently.

HackerLand has n cities numbered from 1 to n. The cities are connected by m bidirectional roads. A citizen has access to
a library if:

Their city contains a library.
They can travel by road from their city to a city containing a library.
The following figure is a sample map of HackerLand where the dotted lines denote obstructed roads:

image

The cost of repairing any road is Croad dollars, and the cost to build a library in any city is Clib dollars.

You are given q queries, where each query consists of a map of HackerLand and value of Clib and Croad.

For each query, find the minimum cost of making libraries accessible to all the citizens and print it on a new line.

Input Format

The first line contains a single integer, q, denoting the number of queries. The subsequent lines describe each query in
the following format:

The first line contains four space-separated integers describing the respective values of n (the number of cities), m
(the number of roads), Clib (the cost to build a library), and Croad (the cost to repair a road).
Each line i of the m subsequent lines contains two space-separated integers, u1 and v1, describing a bidirectional road
connecting cities u1 and v1.
Constraints
1 <= q <= 10
1 <= n <= 10^5
0 <= m <= min(10^5, n(n-1)/2)
1 <= Clib, Croad <= 10^5
1 <= u1, v1 <= n

Each road connects two distinct cities.
Output Format

For each query, print an integer denoting the minimum cost of making libraries accessible to all the citizens on a new
line.

Sample Input

2
3 3 2 1
1 2
3 1
2 3
6 6 2 5
1 3
3 4
2 4
1 2
2 3
5 6
Sample Output

4
12
Explanation

We perform the following q = 2 queries:

HackerLand contains n = 3 cities connected by m = 3 bidirectional roads. The price of building a library is Clib = 2 and
the price for repairing a road is Croad = 1.
                1(library)
              / |
            /   |
           3----2

The cheapest way to make libraries accessible to all is to:

Build a library in city 1 at a cost of x = 2.
Repair the road between cities 1 and 2 at a cost of y = 1.
Repair the road between cities 2 and 3 at a cost of y = 1.
This gives us a total cost of 2 + 2 + 1 = 4. Note that we don't need to repair the road between cities 3 and 1 because
we repaired the roads connecting them to city 2!

In this scenario it's optimal to build a library in each city because the cost of building a library (Clib = 2) is less
than the cost of repairing a road (Croad = 5).

                1(library)                  5(library)
               /  \                         |
              /    \
   (library) 3  - -  2(library)             |
             \      /
              \    /                        6 (library)
               \ /
                4(library)

There are 6 cities, so the total cost is 6 x 2 = 12.
*/
public class RoadsAndLibraries {
    static Map<Integer, Set<Integer>> graph = new HashMap<>();

    private static long dfs(int root, boolean[] visited) {

        visited[root] = true;
        long count = 1;

        Set<Integer> neighbors = graph.get(root);

        for (Integer adj : neighbors) {
            if (!visited[adj])
                count += dfs(adj, visited);
        }

        return count;
    }

    private static long solve(int n, int m, long clib, long croad) {
        long ans = 0;

        boolean[] visited = new boolean[n + 1];
        for (int node = 1; node <= n; node++) {
            if (!visited[node]) {
                long clusterSize = dfs(node, visited);

                ans += clib;

                if (clib > croad) {
                    ans += ((clusterSize - 1) * croad);

                } else {
                    ans += ((clusterSize - 1) * clib);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream(System.getProperty("user.home") + "/IdeaProjects/Level/src/week5/intq/"
                + "text1.txt"));
        Scanner in = new Scanner(System.in);

        int queries = in.nextInt();
        for (int q = 0; q < queries; q++) {
            int n = in.nextInt(); // number of cities
            int m = in.nextInt(); // number of roads
            long clib = in.nextLong();
            long croad = in.nextLong();

            for (int node = 1; node <= n; node++) {
                graph.put(node, new HashSet<>());
            }

            for (int i = 0; i < m; i++) {
                int city1 = in.nextInt();
                int city2 = in.nextInt();

                graph.get(city1).add(city2);
                graph.get(city2).add(city1);
            }

            System.out.println(solve(n, m, clib, croad));
        }
    }
}
