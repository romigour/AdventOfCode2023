import util.FileReader;

import java.io.IOException;
import java.util.*;

public class Day4 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(4);

        //System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        int sum = 0;

        for (String input: inputs) {
            String[] arrayCards = input.split(" \\| ");

            List<String> listCard1 = new ArrayList<>(Arrays.asList(arrayCards[0]
                    .replaceAll("\\s{2,}", " ").trim()
                    .replaceAll("Card -?\\d+(\\.\\d+)?: ", "").split(" ")));
            List<String> listCard2 = new ArrayList<>(Arrays.asList(arrayCards[1].replaceAll("\\s{2,}", " ").trim().split(" ")));


            listCard1.retainAll(listCard2);

            int scoreParty = 0;
            for (int i = 0; i < listCard1.size(); i++) {
                if (i == 0) {
                    scoreParty = 1;
                } else {
                    scoreParty = scoreParty * 2;
                }
            }
            sum += scoreParty;
        }

        return sum;
    }

    private static int reponse2(List<String> inputs) {

        int sum = 0;

        Map<Integer, Integer> mapCard = new HashMap<>();

        for (int n = 1; n <= inputs.size(); n++) {
            String[] arrayCards = inputs.get(n-1).split(" \\| ");

            List<String> listCard1 = new ArrayList<>(Arrays.asList(arrayCards[0]
                    .replaceAll("\\s{2,}", " ").trim()
                    .replaceAll("Card -?\\d+(\\.\\d+)?: ", "").split(" ")));
            List<String> listCard2 = new ArrayList<>(Arrays.asList(arrayCards[1].replaceAll("\\s{2,}", " ").trim().split(" ")));


            listCard1.retainAll(listCard2);

            for (int i = 1; i <= listCard1.size(); i++) {

                if (mapCard.get((n + i)) == null) {
                    if (mapCard.get(n) != null) {
                        mapCard.put(n + i, 1 + mapCard.get(n));
                    } else {
                        mapCard.put(n + i, 1 + 1);
                    }
                } else {
                    if (mapCard.get(n) != null) {
                        mapCard.put(n + i, mapCard.get(n + i) + mapCard.get(n));
                    } else {
                        mapCard.put(n + i, mapCard.get(n + i) + 1);
                    }
                }
            }

            mapCard.putIfAbsent(n, 1);
        }

        for (Map.Entry<Integer, Integer> entrySet : mapCard.entrySet()) {
            sum += entrySet.getValue();
        }

        return sum;
    }
}
