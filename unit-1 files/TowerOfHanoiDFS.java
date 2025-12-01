public class TowerOfHanoiDFS {

    public static void solveDFS(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        solveDFS(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        solveDFS(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("DFS Solution:");
        solveDFS(n, 'A', 'B', 'C');
    }
}