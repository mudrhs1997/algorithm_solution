import java.util.*;

public class 백준_16235 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int year = scanner.nextInt();
        int food[][] = new int[n+1][n+1];
        int dead[][] = new int[n+1][n+1];
        int fill[][] = new int[n+1][n+1];
        int dx[] = {1, 1, 0, -1, -1, -1, 0, 1};
        int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};

        LinkedList<Integer>[][] tree = new LinkedList[n+1][n+1];
        LinkedList<Integer>[][] newTree = new LinkedList[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                tree[i][j] = new LinkedList<>();
                newTree[i][j] = new LinkedList<>();
                fill[i][j] = scanner.nextInt();
                food[i][j] = 5;
                dead[i][j] = 0;
            }
        }

        for (int i = 1; i <= m; i++) {
            int x,y,z;

            x = scanner.nextInt();
            y = scanner.nextInt();
            z = scanner.nextInt();

            tree[x][y].add(z);
            Collections.sort(tree[x][y]);
        }



        while(year>0){

            // 봄 + 여름 + 겨울
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (tree[j][k].size() != 0) {
                        int size = tree[j][k].size();
                        for (int i = 0; i < size; i++) {
                            Integer f = tree[j][k].poll();
                            int g = food[j][k];
                            if (g >= f) {
                                food[j][k] -= f;
                                tree[j][k].add(f + 1);
                            } else {
                                dead[j][k] += f/2;
                            }
                        }
                    }
                    if (dead[j][k] != 0) {
                        food[j][k] += dead[j][k];
                        dead[j][k] = 0;
                    }
                    food[j][k] += fill[j][k];
                }
            }

            // 가을

            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    newTree[j][k]=tree[j][k];
                }
            }
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (tree[j][k].size() != 0) {
                        for (int i = 0; i < tree[j][k].size(); i++) {
                            if (tree[j][k].get(i) % 5 == 0) {
                                for (int p = 0; p < 8; p++) {
                                    int a = j + dx[p];
                                    int b = k + dy[p];
                                    if (a >= 1 && a <= n && b >= 1 && b <= n) {
                                        newTree[a][b].addFirst(1);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    tree[j][k]=newTree[j][k];
                }
            }

            year--;
        }

        int size = 0;

        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                if (tree[j][k].size() != 0) {
                    size += tree[j][k].size();
                }
            }
        }

        System.out.println(size);

    }

}