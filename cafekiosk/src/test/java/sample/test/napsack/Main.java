package sample.test.napsack;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] dy;


    public static void main(String[] args) {
        Main main = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        m = kb.nextInt();
        dy = new int[m + 1];
        System.out.println(main.solution(arr));
    }

    private int solution(int[] coin) {
        Arrays.fill(dy, Integer.MAX_VALUE);

        dy[0] = 0;
        for (int co : coin) {
            for (int i = co; i < dy.length; i++) {
                dy[i] = Math.min(dy[i], dy[i-co] + 1);
            }
        }
        return dy[m];
    }
}
