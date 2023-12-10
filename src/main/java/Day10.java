import util.FileReader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Day10 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(10);

//        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {


        Node[][] carte = new Node[inputs.size()][inputs.get(0).split("").length];

        int startX = -1;
        int startY = -1;

        int y = 0;
        for (String input: inputs) {
            int x = 0;
            for (String inputArr: input.split("")) {
                carte[y][x] = new Node(inputArr, x, y);

                if ("S".equals(inputArr)) {
                    startX = x;
                    startY = y;
                }

                x++;

            }
            y++;
        }

        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                System.out.print(carte[j][k].tile + " ");
            }
            System.out.println();
        }

        carte[startY][startX].value = 0;
        carte[startY][startX].visited = true;
        Deque<Node> nextNodes = new ArrayDeque<>();
        nextNodes.add(carte[startY][startX]);
        while (!nextNodes.isEmpty()) {
            Node nextNode = nextNodes.pollFirst();
            parcourir(nextNode.x, nextNode.y, carte, nextNodes);
        }

        int bestValue = 0;
        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                if (bestValue < carte[j][k].value) {
                    bestValue = carte[j][k].value;
                }
            }
        }

        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                System.out.print(carte[j][k].value + " ");
            }
            System.out.println();
        }

        return bestValue;
    }

    private static int reponse2(List<String> inputs) {


        Node[][] carte = new Node[inputs.size()][inputs.get(0).split("").length];

        int startX = -1;
        int startY = -1;

        int y = 0;
        for (String input: inputs) {
            int x = 0;
            for (String inputArr: input.split("")) {
                carte[y][x] = new Node(inputArr, x, y);

                if ("S".equals(inputArr)) {
                    startX = x;
                    startY = y;
                }

                x++;

            }
            y++;
        }

        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                System.out.format("%3s", carte[j][k].tile);
            }
            System.out.println();
        }

        carte[startY][startX].value = 0;
        carte[startY][startX].visited = true;
        Deque<Node> nextNodes = new ArrayDeque<>();
        nextNodes.add(carte[startY][startX]);
        while (!nextNodes.isEmpty()) {
            Node nextNode = nextNodes.pollFirst();
            parcourir2(nextNode.x, nextNode.y, carte, nextNodes);
        }

        int bestValue = 0;
        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                if (bestValue < carte[j][k].value) {
                    bestValue = carte[j][k].value;
                }
            }
        }
        System.out.println();
        for (int j = 0; j < carte.length; j++) {
            for (int k = 0; k < carte[0].length; k++) {
                System.out.format("%3d", carte[j][k].value);
            }
            System.out.println();
        }

        return bestValue;
    }

    private static void parcourir(int x, int y, Node[][] nodes, Deque<Node> nextNodes) {

        if (x > 0 && ("-".equals(nodes[y][x].tile) || "7".equals(nodes[y][x].tile) || "J".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y][x-1].tile) && !nodes[y][x-1].visited) {
                if ("F".equals(nodes[y][x-1].tile) || "L".equals(nodes[y][x-1].tile) || "-".equals(nodes[y][x-1].tile)) {
                    nodes[y][x - 1].value = nodes[y][x].value + 1;
                    nodes[y][x - 1].visited = true;
                    nextNodes.add(nodes[y][x - 1]);
                }
            }
        }

        if (y > 0 && ("|".equals(nodes[y][x].tile) || "L".equals(nodes[y][x].tile) || "J".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y - 1][x].tile) && !nodes[y - 1][x].visited) {
                if ("F".equals(nodes[y - 1][x].tile) || "7".equals(nodes[y - 1][x].tile) || "|".equals(nodes[y - 1][x].tile)) {
                    nodes[y - 1][x].value = nodes[y][x].value + 1;
                    nodes[y - 1][x].visited = true;
                    nextNodes.add(nodes[y - 1][x]);
                }
            }
        }

        if (x < nodes[y].length - 1 && ("F".equals(nodes[y][x].tile) || "-".equals(nodes[y][x].tile) || "L".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y][x+1].tile) && !nodes[y][x+1].visited) {
                if ("7".equals(nodes[y][x+1].tile) || "-".equals(nodes[y][x+1].tile) || "J".equals(nodes[y][x+1].tile)) {
                    nodes[y][x + 1].value = nodes[y][x].value + 1;
                    nodes[y][x + 1].visited = true;
                    nextNodes.add(nodes[y][x + 1]);
                }
            }
        }

        if (y < nodes.length - 1 && ("|".equals(nodes[y][x].tile) || "F".equals(nodes[y][x].tile) || "7".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y + 1][x].tile) && !nodes[y + 1][x].visited) {
                if ("L".equals(nodes[y + 1][x].tile) || "J".equals(nodes[y + 1][x].tile) || "|".equals(nodes[y + 1][x].tile)) {
                    nodes[y + 1][x].value = nodes[y][x].value + 1;
                    nodes[y + 1][x].visited = true;
                    nextNodes.add(nodes[y + 1][x]);
                }
            }
        }
    }

    private static void parcourir2(int x, int y, Node[][] nodes, Deque<Node> nextNodes) {

        if (x > 0 && ("-".equals(nodes[y][x].tile) || "7".equals(nodes[y][x].tile) || "J".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y][x-1].tile) && !nodes[y][x-1].visited) {
                if ("F".equals(nodes[y][x-1].tile) || "L".equals(nodes[y][x-1].tile) || "-".equals(nodes[y][x-1].tile)) {
                    nodes[y][x - 1].value = 0;
                    nodes[y][x - 1].visited = true;
                    nextNodes.add(nodes[y][x - 1]);
                }
            }
        }

        if (y > 0 && ("|".equals(nodes[y][x].tile) || "L".equals(nodes[y][x].tile) || "J".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y - 1][x].tile) && !nodes[y - 1][x].visited) {
                if ("F".equals(nodes[y - 1][x].tile) || "7".equals(nodes[y - 1][x].tile) || "|".equals(nodes[y - 1][x].tile)) {
                    nodes[y - 1][x].value = 0;
                    nodes[y - 1][x].visited = true;
                    nextNodes.add(nodes[y - 1][x]);
                }
            }
        }

        if (x < nodes[y].length - 1 && ("F".equals(nodes[y][x].tile) || "-".equals(nodes[y][x].tile) || "L".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y][x+1].tile) && !nodes[y][x+1].visited) {
                if ("7".equals(nodes[y][x+1].tile) || "-".equals(nodes[y][x+1].tile) || "J".equals(nodes[y][x+1].tile)) {
                    nodes[y][x + 1].value = 0;
                    nodes[y][x + 1].visited = true;
                    nextNodes.add(nodes[y][x + 1]);
                }
            }
        }

        if (y < nodes.length - 1 && ("|".equals(nodes[y][x].tile) || "F".equals(nodes[y][x].tile) || "7".equals(nodes[y][x].tile) || "S".equals(nodes[y][x].tile))) {
            if (!".".equals(nodes[y + 1][x].tile) && !nodes[y + 1][x].visited) {
                if ("L".equals(nodes[y + 1][x].tile) || "J".equals(nodes[y + 1][x].tile) || "|".equals(nodes[y + 1][x].tile)) {
                    nodes[y + 1][x].value = 0;
                    nodes[y + 1][x].visited = true;
                    nextNodes.add(nodes[y + 1][x]);
                }
            }
        }
    }

    static class Node {
        String tile;
        int x;
        int y;
        boolean visited = false;
        int value = -1;
        public Node(String tile, int x, int y) {
            this.tile = tile;
            this.x = x;
            this.y = y;
        }
    }

}
