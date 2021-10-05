package algorithm;

import common.Values;
import formats.Parsetree;
import formats.Stump;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class StumpGenerator implements StumpFindingAlgorithm{
    Parsetree pt;

    public Stump getStump() {
        String id = "0";
        String keyword = getSuperkey(id);
        String header = pt.getHeader();
        return new Stump(id + keyword + id + header + id);
    }

    public String getSuperkey(String id) {
        String first, middle, last = "";
        final List<String> lines = pt.getAllLevel();
        first = setUpFirstLevel(id, lines);
        last = setUpLastLevel(last, lines);
        middle = setUpMiddle(id, lines);
        return combine(first, middle, last, id);
    }

    private String combine(String first, String middle, String last, String id) {
        if(!last.equals("")) {
            return first + middle + getLastKey(last, first + middle, id);
        } return first;
    }

    private String setUpMiddle(String id, List<String> lines) {
        return lines.stream()
                .map(s -> getLevelKey(s, id))
                .collect(Collectors.joining());
    }

    private String setUpLastLevel(String lastLevel, List<String> lines) {
        if (lines.size() > 0) {
            lastLevel = lines.get(lines.size() - 1);
            lines.remove(lines.size() - 1);
        } return lastLevel;
    }

    private String setUpFirstLevel(String id, List<String> lines) {
        String minKey = getLevelKey(lines.get(0), id);
        lines.remove(0);
        Collections.reverse(lines);
        return minKey;
    }

    private String getLastKey(String line, String currentKey, String id) {
        int length = pt.getDK(line);
        String keyword;
        int[] index = new int[length];
        keyword = generateFromBytes(index);
        while (line.contains(keyword) || keyword.contains(id) || checkIfSymmetric(currentKey + keyword)) {
            keyword = generateFromBytes(index);
            iterate(index);
        } return keyword;
    }

    private boolean checkIfSymmetric(String superkey) {
        return new StringBuilder(superkey)
                .reverse()
                .toString()
                .equals(superkey);
    }

    private String getLevelKey(String s, String id) {
        return getKey(s, pt.getDK(s), id);
    }

    private void iterate(int[] index) {
        index[0]++;
        IntStream.range(0, index.length)
                .filter(i -> index[i] >= Values.D.length)
                .filter(i -> i + 1 < index.length)
                .forEach(i -> {
                    index[i + 1]++;
                    index[i] = 0;
                });
    }

    private String generateFromBytes(int[] s) {
        StringBuilder g = new StringBuilder();
        Arrays.stream(s).forEach(v -> {
            char c = (char) (v + 97);
            g.append(c);
        });
        return g.toString();
    }

    private String getKey(String s, int length, String id) {
        String keyword;
        int[] index = new int[length];
        iterate(index);
        keyword = generateFromBytes(index);
        while (s.contains(keyword) || keyword.contains(id)) {
            keyword = generateFromBytes(index);
            iterate(index);
        }
        return keyword;
    }
}
