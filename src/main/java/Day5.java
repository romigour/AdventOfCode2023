import util.FileReader;

import java.io.IOException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(5);

//        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        List<Long> seeds = Arrays.stream(inputs.get(0).replaceAll("seeds: ", "").split(" ")).map(Long::parseLong).toList();
        List<String> path = new ArrayList<>();
        List<List<Long>> lines = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            if (inputs.get(i).isEmpty() || i == inputs.size()-1) {
                List<Long> nextSeeds = new ArrayList<>();
                for (long seed: seeds) {

                    long newsSeed = -1L;
                    for (List<Long> line: lines) {
                        if (seed >= line.get(1) && seed < (line.get(1) + line.get(2))) {
                            newsSeed = line.get(0) - line.get(1) + seed;
                            nextSeeds.add(newsSeed);
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

    private static long reponse2(List<String> inputs) {
        List<Long> rangeSeeds = Arrays.stream(inputs.get(0).replaceAll("seeds: ", "").split(" ")).map(Long::parseLong).toList();

        long survivor = Long.MAX_VALUE;
        for (int r = 0; r < rangeSeeds.size(); r = r+2) {
            List<Long> seeds = new ArrayList<>();

            System.out.println("Range [" + rangeSeeds.get(r) + " -> " + rangeSeeds.get(r+1) + "]");
            for (long j = rangeSeeds.get(r); j < (rangeSeeds.get(r) + rangeSeeds.get(r+1)); j = j + 10000000) {
                seeds.clear();
                for (long k = j; k < (j + 10000000) && k < (rangeSeeds.get(r) + rangeSeeds.get(r+1)); k++) {
                    seeds.add(k);
                }
                List<String> path = new ArrayList<>();
                List<List<Long>> lines = new ArrayList<>();
                for (int i = 1; i < inputs.size(); i++) {
                    if (inputs.get(i).isEmpty() || i == inputs.size()-1) {
                        List<Long> nextSeeds = new ArrayList<>();
                        for (long seed: seeds) {

                            long newsSeed = -1L;
                            for (List<Long> line: lines) {
                                if (seed >= line.get(1) && seed < (line.get(1) + line.get(2))) {
                                    newsSeed = line.get(0) - line.get(1) + seed;
                                    nextSeeds.add(newsSeed);
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

                long challenger = seeds.stream().mapToLong(v -> v).min().orElse(Long.MAX_VALUE);
                if (survivor > challenger) survivor = challenger;
            }
        }

        return survivor;
    }
}
