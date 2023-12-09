import util.FileReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(8);

        //System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }


    private static int reponse1(List<String> inputs) {

        Map<String, Node> mapNodes = new HashMap<>();

        List<String> instructions = Arrays.asList(inputs.get(0).split(""));


        for (int i = 2; i < inputs.size(); i++) {
            // AAA = (BBB, CCC)
            String[] array1 = inputs.get(i).split(" = ");
            String[] array2 = array1[1].replace("(", "").replace(")", "").split(", ");

            mapNodes.put(array1[0], new Node(array2[0], array2[1]));
        }

        boolean zzzFound = false;


        String current = "AAA";

        int nbStep = 0;
        while (!zzzFound) {
            for (String instruction : instructions) {
                if ("L".equals(instruction)) {
                    current = mapNodes.get(current).left;
                } else {
                    current = mapNodes.get(current).right;
                }

                nbStep++;
                if ("ZZZ".equals(current)) {
                    zzzFound = true;
                    break;
                }
            }
        }


        return nbStep;
    }

    private static int reponse2(List<String> inputs) {

        Map<String, Node> mapNodes = new HashMap<>();

        List<String> instructions = Arrays.asList(inputs.get(0).split(""));


        for (int i = 2; i < inputs.size(); i++) {
            // AAA = (BBB, CCC)
            String[] array1 = inputs.get(i).split(" = ");
            String[] array2 = array1[1].replace("(", "").replace(")", "").split(", ");

            mapNodes.put(array1[0], new Node(array2[0], array2[1]));
        }

        boolean zzzFound = false;


        List<String> currents = mapNodes.keySet().stream().filter(s -> s.endsWith("A")).collect(Collectors.toList());

        int nbStep = 0;
        while (!zzzFound) {
            for (String instruction : instructions) {
                for (int i = 0; i < currents.size(); i++) {
                    if ("L".equals(instruction)) {
                        currents.set(i, mapNodes.get(currents.get(i)).left);
                    } else {
                        currents.set(i, mapNodes.get(currents.get(i)).right);
                    }
                }

                nbStep++;
                if (currents.stream().allMatch(s -> s.endsWith("Z"))) {
                    zzzFound = true;
                    break;
                }
            }
        }


        return nbStep;
    }

    static class Node {
        String left;
        String right;

        public Node(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
}
