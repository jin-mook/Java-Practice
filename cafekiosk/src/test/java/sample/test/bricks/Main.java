package sample.test.bricks;

import java.util.*;

public class Main {

    static int[] dy;

    static class Brick implements Comparable<Brick> {
        public int s, h, w;
        public Brick(int s, int h, int w) {
            this.s = s;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Brick o) {
            return o.s - this.s;
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Brick> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            arr.add(new Brick(a, b, c));
        }
        System.out.println(main.solution(arr));
    }

    private int solution(ArrayList<Brick> arr) {

        Collections.sort(arr);
        dy = new int[arr.size()];
        dy[0] = arr.get(0).h;

        int result = dy[0];
        for (int i = 1; i < arr.size(); i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr.get(i).s < arr.get(j).s && arr.get(i).w < arr.get(j).w) {
                    max = Math.max(max, dy[j]);
                }
            }
            dy[i] = max+arr.get(i).h;
            result = Math.max(result, dy[i]);
//            System.out.println("result = " + result);
        }
        return result;
    }
}
