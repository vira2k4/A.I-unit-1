import java.util.*;

public class TowerOfHanoiBFS {

    // Convert pegs to a unique string for hashing
    static String encode(int[][] pegs) {
        return Arrays.deepToString(pegs);
    }

    public static void solveBFS(int n) {

        // Initial state: all disks on peg A (0)
        int[][] start = { {3,2,1}, {}, {} };
        int[][] goal  = { {}, {}, {3,2,1} };

        Queue<int[][]> q = new LinkedList<>();
        Map<String, String> move = new HashMap<>();
        Map<String, int[][]> parent = new HashMap<>();

        q.add(start);
        parent.put(encode(start), null);

        while (!q.isEmpty()) {
            int[][] cur = q.poll();
            String curKey = encode(cur);

            if (curKey.equals(encode(goal))) {
                printPath(goal, parent, move);
                return;
            }

            // Try all moves
            for (int from = 0; from < 3; from++) {
                if (cur[from].length == 0) continue;

                int disk = cur[from][cur[from].length - 1];

                for (int to = 0; to < 3; to++) {
                    if (from == to) continue;

                    if (cur[to].length == 0 || cur[to][cur[to].length - 1] > disk) {

                        // Create new state
                        int[][] next = new int[3][];
                        for (int i = 0; i < 3; i++)
                            next[i] = cur[i].clone();

                        next[from] = Arrays.copyOf(cur[from], cur[from].length - 1);
                        next[to]   = Arrays.copyOf(cur[to], cur[to].length + 1);
                        next[to][next[to].length - 1] = disk;

                        String key = encode(next);
                        if (!parent.containsKey(key)) {
                            parent.put(key, cur);
                            move.put(key, "Move disk " + disk + " from " + (char)('A'+from) + " to " + (char)('A'+to));
                            q.add(next);
                        }
                    }
                }
            }
        }
    }

    static void printPath(int[][] goal, Map<String,int[][]> parent, Map<String,String> move) {
        List<String> result = new ArrayList<>();
        String key = encode(goal);

        while (parent.get(key) != null) {
            result.add(move.get(key));
            key = encode(parent.get(key));
        }

        Collections.reverse(result);

        System.out.println("BFS Solution:");
        for (String m : result) System.out.println(m);
    }

    public static void main(String[] args) {
        solveBFS(3);
    }
}