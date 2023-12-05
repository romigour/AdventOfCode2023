import util.FileReader;

import java.io.IOException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(5);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {

        List<Long> seeds = Arrays.stream(inputs.get(0).replaceAll("seeds: ", "").split(" ")).map(Long::parseLong).toList();
        List<String> path = new ArrayList<>();
        List<List<Long>> lines = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            if (inputs.get(i).isEmpty()) {
                List<Long> nextSeeds = new ArrayList<>();
                for (long seed: seeds) {

                    long newsSeed = -1;
                    for (List<Long> line: lines) {
                        if (seed >= line.get(1) && seed < (line.get(1) + line.get(2))) {
                            newsSeed = line.get(0) - line.get(1) + seed;
                            nextSeeds.add(newsSeed);
                            break;
                        }
                    }

                    if (newsSeed == -1L) {
                        nextSeeds.add(seed);
                    }
                }
                seeds = nextSeeds;
                path.clear();
                lines.clear();
            } else if (inputs.get(i).contains(":")) {
                path.addAll(Arrays.stream(inputs.get(i).split(" ")[0].split("-to-")).toList());
            } else {
                lines.add(Arrays.stream(inputs.get(i).split(" ")).map(Long::parseLong).toList());
            }
        }

        return seeds.stream().mapToLong(v -> v).min().orElseThrow();
    }

    private static String reponse2(List<String> inputs) {

        return null;
    }
}
