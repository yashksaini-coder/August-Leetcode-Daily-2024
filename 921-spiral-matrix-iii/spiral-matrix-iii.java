import java.util.*;

class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int numSteps = 1;
        int totalCells = rows * cols;
        List<int[]> result = new ArrayList<>();
        int r = rStart, c = cStart;
        int d = 0;

        while (result.size() < totalCells) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < numSteps; j++) {
                    if (0 <= r && r < rows && 0 <= c && c < cols) {
                        result.add(new int[]{r, c});
                    }
                    if (result.size() == totalCells) {
                        return convertListToArray(result);
                    }
                    r += directions[d][0];
                    c += directions[d][1];
                }
                d = (d + 1) % 4;
            }
            numSteps++;
        }

        return convertListToArray(result);
    }

    private int[][] convertListToArray(List<int[]> list) {
        int[][] array = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] result = solution.spiralMatrixIII(5, 6, 1, 4);
        for (int[] coords : result) {
            System.out.println(Arrays.toString(coords));
        }
    }
}