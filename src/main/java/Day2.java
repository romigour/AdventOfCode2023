import util.FileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(2);

//        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        Map<Integer, Map<String, Integer>> mapGlobal = new HashMap<>();

        int sum = 0;
        for (String input: inputs) {
            String[] inputArray = input.split(": ");
            String[] gameArray = inputArray[0].split(" ");
            String[] valuesArray = inputArray[1].split("[,;]");

            int numGame = Integer.parseInt(gameArray[1]);
            mapGlobal.put(numGame, new HashMap<>());

            boolean valid = true;
            for (String value: valuesArray) {
                String[] keyVal = value.trim().split(" ");

                if ((keyVal[1].equals("red") && Integer.parseInt(keyVal[0]) > 12) ||
                        (keyVal[1].equals("green") && Integer.parseInt(keyVal[0]) > 13) ||
                                (keyVal[1].equals("blue") && Integer.parseInt(keyVal[0]) > 14)) {
                    valid = false;
                }
            }
            if (valid) {
                sum += numGame;
            }
        }

        return sum;
    }

    private static int reponse2(List<String> inputs) {
        Map<Integer, Map<String, Integer>> mapGlobal = new HashMap<>();

        int sum = 0;
        for (String input: inputs) {
            String[] inputArray = input.split(": ");
            String[] gameArray = inputArray[0].split(" ");
            String[] valuesArray = inputArray[1].split("[,;]");

            int numGame = Integer.parseInt(gameArray[1]);
            mapGlobal.put(numGame, new HashMap<>());

            int red = 0;
            int green = 0;
            int blue = 0;
            for (String value: valuesArray) {
                String[] keyVal = value.trim().split(" ");

                if (keyVal[1].equals("red") && red < Integer.parseInt(keyVal[0])) {
                    red = Integer.parseInt(keyVal[0]);
                } else if (keyVal[1].equals("green") && green < Integer.parseInt(keyVal[0])) {
                    green = Integer.parseInt(keyVal[0]);
                } else if (keyVal[1].equals("blue") && blue < Integer.parseInt(keyVal[0])) {
                    blue = Integer.parseInt(keyVal[0]);
                }
            }

            int powerGame = red * green * blue;

            sum += powerGame;
        }

        return sum;
    }
}
