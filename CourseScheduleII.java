package week5.intq;

import java.util.*;
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as
a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order
is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1
and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering
is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph
is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological
ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological
Sort.
Topological sort could also be done via BFS.

*/
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] indegree = new int[numCourses];

        //To adjacent list
        Map<Integer, List<Integer>> adjac = new HashMap<Integer, List<Integer>> ();
        for (int[] edge : prerequisites) {
            int prev = edge[0];
            int dep = edge[1];
            if (adjac.containsKey(prev)) {
                adjac.get(prev).add(dep);
            } else {
                List<Integer> list = new ArrayList<Integer> ();
                list.add(dep);
                adjac.put(prev, list);
            }
            indegree[dep] ++;
        }

        // track the order of each nodes
        int count = numCourses - 1;
        int[] order = new int[numCourses];

        // bfs to get the order of all the nodes
        Queue<Integer> queue = new LinkedList<Integer> ();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (queue.size() != 0) {
            int course = queue.poll();
            order[count--] = course;
            if (adjac.containsKey(course)) {
                List<Integer> deps = adjac.get(course);
                for (int dep : deps) {
                    indegree[dep] --;
                    if (indegree[dep] == 0) {
                        queue.offer(dep);
                    }
                }
            }
        }

        if (count != -1) {
            return new int[0];
        }

        return order;
    }


    public static void main(String[] args) {
        int [][] c = new int[1][2];
        c[0][0] = 1;
        c[0][1] = 0;
        /*c[1][0] = 2;
        c[1][1] = 0;
        c[2][0] = 3;
        c[2][1] = 1;
        c[3][0] = 3;
        c[3][1] = 2;*/

        int [] result = new CourseScheduleII().findOrder(2, c);

        for (int i=0;i<result.length; i++)
            System.out.print(result[i]+" ");
    }
}
