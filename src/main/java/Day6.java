import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(6);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        List<Integer> possibilities = new ArrayList<>();
        List<Integer> times = Arrays.stream(inputs.get(0)
                        .replaceAll("\\s{2,}", " ")
                        .replace("Time: ", "").split(" "))
                .map(Integer::parseInt).toList();
        List<Integer> distances = Arrays.stream(inputs.get(1)
                        .replaceAll("\\s{2,}", " ")
                        .replace("Distance: ", "").split(" "))
                .map(Integer::parseInt).toList();

        for (int occ = 0; occ < distances.size(); occ++) {
            int possibility = 0;
            for (int t = 0; t < times.get(occ); t++) {
                int vitesse = t;
                int tempsRestant = times.get(occ) - t;
                int distanceParcouru = vitesse * tempsRestant;

                if (distances.get(occ) < distanceParcouru) {
                    possibility++;
                }
            }
            possibilities.add(possibility);
         }


        int sum = 1;

        for (int possibility : possibilities) {
            sum *= possibility;
        }

        return sum;
    }

    private static int reponse2(List<String> inputs) {
        List<Long> possibilities = new ArrayList<>();
        List<Long> times = Arrays.stream(inputs.get(0)
                        .replaceAll("\\s{2,}", " ")
                        .replace("Time: ", "").replaceAll(" ", "").split(" "))
                .map(Long::parseLong).toList();
        List<Long> distances = Arrays.stream(inputs.get(1)
                        .replaceAll("\\s{2,}", " ")
                        .replace("Distance: ", "").replaceAll(" ", "").split(" "))
                .map(Long::parseLong).toList();

        for (int occ = 0; occ < distances.size(); occ++) {
            long possibility = 0;
            for (int t = 0; t < times.get(occ); t++) {
                int vitesse = t;
                long tempsRestant = times.get(occ) - t;
                long distanceParcouru = vitesse * tempsRestant;

                if (distances.get(occ) < distanceParcouru) {
                    possibility++;
                }
            }
            possibilities.add(possibility);
        }


        int sum = 1;

        for (long possibility : possibilities) {
            sum *= possibility;
        }

        return sum;
    }
}
