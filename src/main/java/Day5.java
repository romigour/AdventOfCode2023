import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        // Récupération des seeds
        List<Long> rangeSeeds = Arrays.stream(inputs.get(0).replaceAll("seeds: ", "").split(" ")).map(Long::parseLong).toList();
        List<Range> ranges = new ArrayList<>();
        for (int s = 0 ; s < rangeSeeds.size(); s = s + 2) {
            long start = rangeSeeds.get(s);
            long rangeLenght = rangeSeeds.get(s+1);
            ranges.add(new Range(start, rangeLenght));
        }

        // Récupération des Catérogies
        List<MapTo> mapsTo = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            if (inputs.get(i).contains(":")) {
                mapsTo.add(new MapTo(inputs.get(i)));
            } else if (inputs.get(i).isEmpty()) {
            } else {
                List<Long> values = Arrays.stream(inputs.get(i).split(" ")).map(Long::parseLong).toList();
                mapsTo.get(mapsTo.size() - 1).categories.add(new Category(values.get(1), values.get(0), values.get(2)));
            }
        }

        final long[] min = {Long.MAX_VALUE};
        ranges.stream().parallel().forEach(range -> {
            for (long seed = range.start; seed <= (range.start + range.rangeLenght); seed++) {

                long newsSeed = seed;
                for (MapTo map : mapsTo) {
                    for (Category category : map.categories) {
                        if (newsSeed >= category.start && newsSeed < (category.start + category.range)) {
                            newsSeed = category.destination - category.start + newsSeed;
                            break;
                        }
                    }
                }
                if (min[0] > newsSeed) {
                    min[0] = newsSeed;
                }
            }
            System.out.println("Range [" + range.start + " -> " + range.rangeLenght + "] Done with min value : " + min[0]);
        });
        return min[0];
    }

    static class MapTo {
        String name;
        List<Category> categories = new ArrayList<>();

        public MapTo(String name) {
            this.name = name;
        }
    }

    static class Category {
        long start;
        long destination;
        long range;

        public Category(long start, long destination, long range) {
            this.start = start;
            this.destination = destination;
            this.range = range;
        }
    }

    static class Range {
        long start;
        long rangeLenght;

        public Range(long start, long rangeLenght) {
            this.start = start;
            this.rangeLenght = rangeLenght;
        }
    }
}
