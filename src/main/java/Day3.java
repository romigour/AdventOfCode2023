import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day3 {
    static List<MyNumber> listMyNumber = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(3);

//        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse2(List<String> inputs) {


        int sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            String[] inputArr = inputs.get(i).split("");

            String number = "";
            boolean isNeighboor = false;
            List<Position> symbols = new ArrayList<>();
            for (int j = 0; j < inputArr.length; j++) {
                if (isNumeric(inputArr[j])) {
                    number += inputArr[j];
                    Position pos;
                    if ((pos = isNeighboor(i, j, inputs)) != null) {
                        symbols.add(pos);
                        isNeighboor = true;
                    }
                } else {
                    if (isNeighboor) {
                        listMyNumber.add(new MyNumber(Integer.parseInt(number), new ArrayList<>(symbols)));
                        isNeighboor = false;
                        symbols.clear();
                    }
                    number = "";
                }

                if (j == (inputArr.length - 1)) {
                    if (isNeighboor) {
                        listMyNumber.add(new MyNumber(Integer.parseInt(number), new ArrayList<>(symbols)));
                        isNeighboor = false;
                        symbols.clear();
                    }
                    number = "";
                }
            }
        }


        for (int i = 0; i < listMyNumber.size(); i++) {
            for (int j = i+1; j < listMyNumber.size(); j++) {
                int finalJ = j;
                if (listMyNumber.get(i).symbols.stream()
                        .anyMatch(position -> listMyNumber.get(finalJ).symbols.stream()
                                .anyMatch(position1 -> position.x == position1.x && position.y == position1.y))) {
                    sum += listMyNumber.get(i).number * listMyNumber.get(j).number;
                    break;
                }
            }

        }

        return sum;
    }

    public static Position isNeighboor(int y, int x, List<String> inputs) {

        if (y > 0) {
            String inputMoinsY = inputs.get(y-1).split("")[x];
            if (!isNumeric(inputMoinsY) && !".".equals(inputMoinsY)) {
                return new Position(x, y-1);
            }
        }

        if (y < (inputs.size() - 1)) {
            String inputPlusY = inputs.get(y+1).split("")[x];
            if (!isNumeric(inputPlusY) && !".".equals(inputPlusY)) {
                return new Position(x, y+1);
            }
        }

        if (x > 0) {
            String inputMoinsX = inputs.get(y).split("")[x-1];
            if (!isNumeric(inputMoinsX) && !".".equals(inputMoinsX)) {
                return new Position(x-1, y);
            }
        }

        if (x < (inputs.get(0).length() - 1)) {
            String inputPlusX = inputs.get(y).split("")[x+1];
            if (!isNumeric(inputPlusX) && !".".equals(inputPlusX)) {
                return new Position(x+1, y);
            }
        }

        if (x > 0 && y > 0) {
            String diagMoinsXY = inputs.get(y-1).split("")[x-1];
            if (!isNumeric(diagMoinsXY) && !".".equals(diagMoinsXY)) {
                return new Position(x-1, y-1);
            }
        }

        if (x > 0 && y < (inputs.size() - 1)) {
            String p = inputs.get(y+1).split("")[x-1];
            if (!isNumeric(p) && !".".equals(p)) {
                return new Position(x-1, y+1);
            }
        }

        if (x < (inputs.get(0).length() - 1) && y > 0) {
            String p = inputs.get(y-1).split("")[x+1];
            if (!isNumeric(p) && !".".equals(p)) {
                return new Position(x+1, y-1);
            }
        }

        if (x < (inputs.get(0).length() - 1) && y < (inputs.size() - 1)) {
            String p = inputs.get(y+1).split("")[x+1];
            if (!isNumeric(p) && !".".equals(p)) {
                return new Position(x+1, y+1);
            }
        }

        return null;
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches();
    }

    private static class MyNumber {
        int number;
        List<Position> symbols = new ArrayList<>();

        public MyNumber(int number, List<Position> symbols) {
            this.number = number;
            this.symbols = symbols;
        }
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
