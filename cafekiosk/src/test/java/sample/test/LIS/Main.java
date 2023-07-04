package sample.test.LIS;

import java.util.Scanner;

public class Main {

    static int[] dy;


    private int solution(int[] arr) {
        dy = new int[arr.length];
        dy[0] = 1;

        int resultMax = dy[0];
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, dy[j] + 1);
                }
            }
            if (max == 0) {
                dy[i] = max + 1;
            } else {
                dy[i] = max;
            }
            resultMax = Math.max(max, resultMax);
        }
        return resultMax;
    }

    public static void main(String[] args) {
        Main lis = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }

        System.out.println(lis.solution(arr));
    }


}
