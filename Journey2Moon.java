package week5.intq;

import java.io.*;
import java.util.*;
/*The member states of the UN are planning to send 2 people to the Moon. But there is a problem. In line with their
principles of global unity, they want to pair astronauts of 2 different countries.

There are N trained astronauts numbered from 0 to N-1. But those in charge of the mission did not receive information
about the citizenship of each astronaut. The only information they have is that some particular pairs of astronauts
belong to the same country.

Your task is to compute in how many ways they can pick a pair of astronauts belonging to different countries. Assume
that you are provided enough pairs to let you identify the groups of astronauts even though you might not know their
country directly. For instance, if 1,2,3 are astronauts from the same country; it is sufficient to mention that (1,2)
and (2,3) are pairs of astronauts from the same country without providing information about a third pair (1,3).

Input Format

The first line contains two integers, N and P, separated by a single space. P lines follow. Each line contains integers
separated by a single space A and B such that

0 <= A,B <= N-1
and A and B are astronauts from the same country.

Constraints

1 <= N <= 10^5
1 <= P <= 10^4

Output Format

An integer that denotes the number of permissible ways to choose a pair of astronauts.

Sample Input 0

5 3
0 1
2 3
0 4
Sample Output 0

6
Explanation 0

Persons numbered 0, 1 and 4 belong to the same country, and those numbered 2 and 3 belong to the same country, but
different from the previous one. All in all, the UN has 6 ways of choosing a pair:

persons 0 and 2
persons 0 and 3
persons 1 and 2
persons 1 and 3
persons 2 and 4
persons 3 and 4

Sample Input 1

4 1
0 2
Sample Output 1

5
Explanation 1

Persons numbered 0 and 2 belong to the same country, and persons 1 and 3 don't share countries with anyone else, so they
belong to unique countries on their own. All in all, the UN has 5 ways of choosing a pair:

persons 0 and 1
persons 0 and 3
persons 1 and 2
persons 1 and 3
persons 2 and 3

*/
public class Journey2Moon {

    static long journeyToMoon(int n, List<List<Integer>> astronauts) {

        //Visit each "country" (group of astronauts) and get the size
        boolean[] isVisited = new boolean[n];
        List<Integer> countrySizes = new ArrayList<Integer>();

        for (int i = 0; i < n; ++i) {
            int countrySize = 0;
            final Queue<Integer> q = new LinkedList<Integer>();
            q.add(i);

            while (!q.isEmpty()) {
                int astronautId = q.poll();

                if (!isVisited[astronautId]) {
                    ++countrySize;
                    isVisited[astronautId] = true;
                    q.addAll(astronauts.get(astronautId));

                }
            }

            if (countrySize > 0) {
                countrySizes.add(countrySize);
            }

        }

        //Get number of possible pairs
        long numPairs = 0L;
        long numPartners = n;
        for (int countrySize : countrySizes) {
            numPairs += countrySize * (numPartners -= countrySize);
        }

        return numPairs;
    }


    public static void main(String[] args) throws IOException {
        try {
            Scanner in = new Scanner(new File(System.getProperty("user.home") + "/IdeaProjects/Level/src/week5/intq/"
                    + "text2.txt"));
            //Get number of astronauts
            final int N = in.nextInt();

            //Initialize each astronaut's country
            final List<List<Integer>> astronauts = new ArrayList<List<Integer>>(N); //[[1, 4], [0], [3], [2], [0]]
                                                                                     //[[2], [], [0], []]
            for (int i = 0; i < N; ++i) {
                astronauts.add(new ArrayList<Integer>());
            }

            //Get each astronaut's country
            for (int L = in.nextInt(); L > 0; --L) {

                final int A = in.nextInt();
                final int B = in.nextInt();
                astronauts.get(A).add(B);
                astronauts.get(B).add(A);
            }

            System.out.print(journeyToMoon(N, astronauts));
        }
        catch (FileNotFoundException e) {

        }
    }
}
