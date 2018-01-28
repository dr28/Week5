package week5.challenges;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*Challenge 2 - Capture Regions on Board
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/
public class Challenges2 {
    public void solve(ArrayList<ArrayList<Character>> a) {

        if (a == null || a.size() == 0) {
            return;
        }

        int M = a.size();
        int N = a.get(0).size();

        for (int j = 0; j < N; j++) {
            if (a.get(0).get(j) == 'O') {
                bfs(a, 0, j);
            }
        }

        for (int i = 0; i < M; i++) {
            if (a.get(i).get(N-1) == 'O') {
                bfs(a, i, N-1);
            }
        }

        for (int j = 0; j < N; j++) {
            if (a.get(M-1).get(j) == 'O') {
                bfs(a, M-1, j);
            }
        }

        for (int i = 0; i < M; i++) {
            if (a.get(i).get(0) == 'O') {
                bfs(a, i, 0);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (a.get(i).get(j) == 'O') {
                    a.get(i).set(j, 'X');
                } else if (a.get(i).get(j) == '#') {
                    a.get(i).set(j, 'O');
                }
            }
        }
    }

    private void bfs(ArrayList<ArrayList<Character>> a, int row, int col) {
        int M = a.size();
        int N = a.get(0).size();

        int ind = row*N + col;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(ind);
        a.get(row).set(col, '#');

        while (!queue.isEmpty()) {
            ind = queue.remove();
            int r = ind / N;
            int c = ind % N;

            if (r > 0 && a.get(r-1).get(c) == 'O') {
                a.get(r-1).set(c, '#');
                queue.add((r-1)*N + c);
            }

            if (c < N-1 && a.get(r).get(c+1) == 'O') {
                a.get(r).set(c+1, '#');
                queue.add(r*N + (c+1));
            }

            if (r < M-1 && a.get(r+1).get(c) == 'O') {
                a.get(r+1).set(c, '#');
                queue.add((r+1)*N + c);
            }

            if (c > 0 && a.get(r).get(c-1) == 'O') {
                a.get(r).set(c-1, '#');
                queue.add(r*N + (c-1));
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<ArrayList<Character>> a = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> temp = new ArrayList<Character>();
        temp.add('X');
        temp.add('X');
        temp.add('X');
        temp.add('X');
        a.add(new ArrayList<Character>(temp));
        temp.clear();

        temp.add('X');
        temp.add('O');
        temp.add('0');
        temp.add('X');
        a.add(new ArrayList<Character>(temp));
        temp.clear();

        temp.add('X');
        temp.add('X');
        temp.add('0');
        temp.add('X');
        a.add(new ArrayList<Character>(temp));
        temp.clear();


        temp.add('X');
        temp.add('0');
        temp.add('X');
        temp.add('X');
        a.add(new ArrayList<Character>(temp));
        temp.clear();

        new Challenges2().solve(a);
        System.out.println();
        for(ArrayList<Character> list : a){
            System.out.println(list);
        };

    }

}
