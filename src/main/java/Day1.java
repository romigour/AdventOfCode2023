import util.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class Day1 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(1);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int sum = 0;
        for (String input: inputs) {
            String[] inputArray = input.replaceAll("[^\\d.]", "").split("");
            sum += Integer.parseInt(inputArray[0] + inputArray[inputArray.length - 1]);
        }
        return sum;
    }

    private static int reponse2(List<String> inputs) {
        List<String> numbers = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        int sum = 0;
        for (String input: inputs) {
            String output = "";
            String[] arrayInput = input.split("");
            for (int i = 0; i < input.length(); i++) {
                if (isNumeric(arrayInput[i])) {
                    output += arrayInput[i];
                } else {
                    for (int n = 0; n < numbers.size(); n++) {
                        if (input.substring(i).startsWith(numbers.get(n))) {
                            output += (n+1);
                            break;
                        }
                    }
                }
            }

            String[] outputArray = output.split("");
            sum += Integer.parseInt(outputArray[0] + outputArray[outputArray.length - 1]);
        }
        return sum;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches();
    }
}
