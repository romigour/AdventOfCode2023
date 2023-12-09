import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(9);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {

        List<List<Integer>> historiques = new ArrayList<>();

        for (String input: inputs) {
            historiques.add(Arrays.asList(input.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int sum = 0;
        for (List<Integer> historique: historiques) {
            List<List<Integer>> sousHistoriques = new ArrayList<>();
            sousHistoriques.add(historique);
            boolean end = false;
            while (!end) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < sousHistoriques.get(sousHistoriques.size() - 1).size() - 1; i++) {
                    list.add(sousHistoriques.get(sousHistoriques.size() - 1).get(i + 1) - sousHistoriques.get(sousHistoriques.size() - 1).get(i));
                }
                sousHistoriques.add(list);

                if (list.stream().allMatch(i -> i == 0)) {
                    end = true;
                }
            }
            for (int j = sousHistoriques.size() - 1; j >= 0; j--) {
                if (j == (sousHistoriques.size() - 1)) {
                    sousHistoriques.get(j).add(0);
                } else if (j == 0) {
                    sum += sousHistoriques.get(j).get(sousHistoriques.get(j).size() - 1) + sousHistoriques.get(j+1).get(sousHistoriques.get(j).size() - 1);
                } else {
                    sousHistoriques.get(j).add(sousHistoriques.get(j).get(sousHistoriques.get(j).size() - 1) + sousHistoriques.get(j+1).get(sousHistoriques.get(j).size() - 1));
                }
            }
        }

        return sum;
    }

    private static int reponse2(List<String> inputs) {

        List<Deque<Integer>> historiques = new ArrayList<>();

        for (String input: inputs) {
            historiques.add(Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new)));
        }
        int sum = 0;
        for (Deque<Integer> historique: historiques) {
            List<Deque<Integer>> sousHistoriques = new ArrayList<>();
            sousHistoriques.add(historique);
            boolean end = false;
            while (!end) {
                Deque<Integer> list = new ArrayDeque<>();
                for (int i = 0; i < sousHistoriques.get(sousHistoriques.size() - 1).size() - 1; i++) {
                    list.add(sousHistoriques.get(sousHistoriques.size() - 1).stream().toList().get(i + 1) - sousHistoriques.get(sousHistoriques.size() - 1).stream().toList().get(i));
                }
                sousHistoriques.add(list);

                if (list.stream().allMatch(i -> i == 0)) {
                    end = true;
                }
            }
            for (int j = sousHistoriques.size() - 1; j >= 0; j--) {
                if (j == (sousHistoriques.size() - 1)) {
                    sousHistoriques.get(j).addFirst(0);
                } else if (j == 0) {
                    sum += sousHistoriques.get(j).getFirst() - sousHistoriques.get(j+1).getFirst();
                } else {
                    sousHistoriques.get(j).addFirst(sousHistoriques.get(j).getFirst() - sousHistoriques.get(j+1).getFirst());
                }
            }
        }

        return sum;
    }
}
