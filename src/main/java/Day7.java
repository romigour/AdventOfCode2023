import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    static Map<String, Integer> mapCardScore = new HashMap<>();
    static {
        mapCardScore.put("A", 13);
        mapCardScore.put("K", 12);
        mapCardScore.put("Q", 11);
        mapCardScore.put("J", 10);
        mapCardScore.put("T", 9);
        mapCardScore.put("9", 8);
        mapCardScore.put("8", 7);
        mapCardScore.put("7", 6);
        mapCardScore.put("6", 5);
        mapCardScore.put("5", 4);
        mapCardScore.put("4", 3);
        mapCardScore.put("3", 2);
        mapCardScore.put("2", 1);
    }

    static Map<String, Integer> mapCardScoreWithoutJ = new HashMap<>();
    static {
        mapCardScoreWithoutJ.put("A", 13);
        mapCardScoreWithoutJ.put("K", 12);
        mapCardScoreWithoutJ.put("Q", 11);
        mapCardScoreWithoutJ.put("J", 0);
        mapCardScoreWithoutJ.put("T", 9);
        mapCardScoreWithoutJ.put("9", 8);
        mapCardScoreWithoutJ.put("8", 7);
        mapCardScoreWithoutJ.put("7", 6);
        mapCardScoreWithoutJ.put("6", 5);
        mapCardScoreWithoutJ.put("5", 4);
        mapCardScoreWithoutJ.put("4", 3);
        mapCardScoreWithoutJ.put("3", 2);
        mapCardScoreWithoutJ.put("2", 1);
    }

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(7);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        List<Hand> hands = new ArrayList<>();
        for (String input: inputs) {
            String[] line = input.split(" ");
            hands.add(new Hand(line[0], Integer.parseInt(line[1])));
        }

        Collections.sort(hands, (hand1, hand2) -> {

            if (hand1.score == hand2.score) {
                for (int i = 0; i < hand1.cards.size(); i++) {
                    if (!mapCardScore.get(hand1.cards.get(i)).equals(mapCardScore.get(hand2.cards.get(i)))) {
                        return mapCardScore.get(hand1.cards.get(i)) - mapCardScore.get(hand2.cards.get(i));
                    }
                }
            } else {
                return hand1.score - hand2.score;
            }
            return 0;
        });

        int sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += (i+1) * hands.get(i).value;
        }

        return sum;
    }

    private static int reponse2(List<String> inputs) {

        List<Hand> hands = new ArrayList<>();
        for (String input: inputs) {
            String[] line = input.split(" ");
            hands.add(new Hand(line[0], Integer.parseInt(line[1])));
        }

        Collections.sort(hands, (hand1, hand2) -> {

            if (hand1.score == hand2.score) {
                for (int i = 0; i < hand1.cards.size(); i++) {
                    if (!mapCardScoreWithoutJ.get(hand1.cards.get(i)).equals(mapCardScoreWithoutJ.get(hand2.cards.get(i)))) {
                        return mapCardScoreWithoutJ.get(hand1.cards.get(i)) - mapCardScoreWithoutJ.get(hand2.cards.get(i));
                    }
                }
            } else {
                return hand1.score - hand2.score;
            }
            return 0;
        });

        int sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            sum += (i+1) * hands.get(i).value;
        }

        return sum;
    }

    static class Hand {
        List<String> cards;
        List<String> cardsWithoutJ;
        int score;
        int value;

        public Hand(String cards, Integer value) {
            this.cards = Arrays.asList(cards.split(""));
            this.cardsWithoutJ = new ArrayList<>(Arrays.asList(cards.split("")));

            List<List<String>> aa = this.cards.stream().filter(s -> !s.equals("J")).collect(Collectors.groupingBy(w -> w)).values().stream().sorted((o1, o2) -> o2.size() - o1.size()).toList();
            List<List<String>> bb = aa.stream().filter(a -> a.size() == aa.get(0).size()).toList();
            List<List<String>> cc = bb.stream().sorted((o1, o2) -> mapCardScoreWithoutJ.get(o2.get(0)) - mapCardScoreWithoutJ.get(o1.get(0))).toList();

            if (cc.size() > 0 && cc.get(0).size() > 0)
            Collections.replaceAll(this.cardsWithoutJ, "J", cc.get(0).get(0));
            this.score = getScore();
            this.value = value;
        }

        public int getScore() {
            List<Integer> values = cardsWithoutJ.stream().collect(Collectors.groupingBy(w -> w)).values().stream().map(List::size).toList();
            if (values.size() == 1) {
                return 7;
            } else if (values.size() == 2 && (values.get(0) == 4 || values.get(1) == 4)) {
                return 6;
            } else if (values.size() == 2 && (values.get(0) == 3 || values.get(1) == 3)) {
                return 5;
            } else if (values.size() == 3 && (values.get(0) == 3 || values.get(1) == 3 || values.get(2) == 3)) {
                return 4;
            } else if (values.size() == 3 && (values.get(0) == 2 || values.get(1) == 2 || values.get(2) == 2)) {
                return 3;
            } else if (values.size() == 4 && (values.get(0) == 2 || values.get(1) == 2 || values.get(2) == 2 || values.get(3) == 2)) {
                return 2;
            } else {
                return 1;
            }
        }
    }
}
