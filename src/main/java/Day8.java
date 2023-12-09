import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static long reponse2(List<String> inputs) {

        Map<String, Node> mapNodes = new HashMap<>();

        String[] instructions = inputs.get(0).split("");


        for (int i = 2; i < inputs.size(); i++) {
            // AAA = (BBB, CCC)
            String[] array1 = inputs.get(i).split(" = ");
            String[] array2 = array1[1].replace("(", "").replace(")", "").split(", ");

            mapNodes.put(array1[0], new Node(array2[0], array2[1]));
        }


        List<String> currents = mapNodes.keySet().stream().filter(s -> s.endsWith("A")).collect(Collectors.toList());

        List<Integer> listNbStep = new ArrayList<>();

        for (int i = 0; i < currents.size(); i++) {
            int nbStep = 0;
            boolean zzzFound = false;
            while (!zzzFound) {
                for (String instruction : instructions) {
                    if ("L".equals(instruction)) {
                        currents.set(i, mapNodes.get(currents.get(i)).left);
                    } else {
                        currents.set(i, mapNodes.get(currents.get(i)).right);
                    }
                    nbStep++;

                    if (currents.get(i).endsWith("Z")) {
                        zzzFound = true;
                        listNbStep.add(nbStep);
                        break;
                    }
                }
            }
        }

        return listNbStep.stream().mapToLong(a -> a).reduce(Day8::lcm).getAsLong();
    }

    // https://www.baeldung.com/java-least-common-multiple
    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
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
