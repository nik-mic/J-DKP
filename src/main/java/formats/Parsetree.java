package formats;

import algorithm.F;
import algorithm.StumpGenerator;
import algorithm.SolvingStrategy;
import common.Values;
import entities.LiteralScanner;
import entities.ObjectScanner;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Parsetree implements DataFormat {
    private final List<Object> tree;
    private final SolvingStrategy<Parsetree, Urbildelement> solver = new F();
    private final ObjectScanner scanner = new LiteralScanner();
    private final StumpGenerator kg = new StumpGenerator(this);

    public Parsetree(Object o){
        tree = scanner.scan(o);
    }
    public Parsetree(List<Object> pt){
        this.tree = pt;
    }

    public List<String> getAllLevel(){
        return getAllLevel(tree);
    }

    private int getMaxNesting() {
        return tree.stream()
                .map(this::depthOfTree)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public int getDK(String line){
        for(char c : Values.D){
            if(!line.contains(c+"")){
                return 1;
            }
        } return 2;
    }

    public String getHeader(){
        return getAllLevel().stream()
                .map(s -> String.valueOf(getDK(s)))
                .collect(Collectors.joining());
    }

    public int getStumpLength() {
        System.out.println(getMaxNesting());
        return 3 + getMaxNesting() + getSumOfAllDK();
    }

    private int getSumOfAllDK(){
        return getAllLevel().stream()
                .mapToInt(this::getDK)
                .sum();
    }

    private int depthOfTree(Object obj) {
        if(obj instanceof List) {
            int maxDepth = 1;
            for (Object o : (List<Object>) obj) {
                maxDepth = Math.max(maxDepth,
                        depthOfTree(o));
            } return maxDepth + 1;
        } return 1;
    }

    private List<Object> getLevel(List<Object> parsetree){
        List<Object> newLevel = new ArrayList<>();
        parsetree.stream()
                .filter(o -> o instanceof List)
                .map(o -> (Collection<?>) o)
                .forEachOrdered(newLevel::addAll);
        return newLevel;
    }

    private List<String> getAllLevel(List<Object> tree){
        List<String> result = new ArrayList<>();
        do {
            result.add(getFlatline(tree, ""));
            tree = getLevel(tree);
        } while (tree.size()!=0);
        return result;
    }

    private String getFlatline(List<Object> parsetree, String content) {
        String flatline = content;
        for (Object o : parsetree) {
            if (o instanceof List) {
                flatline = getFlatline((List<Object>)o, flatline);
            } else {
                flatline = flatline + o;
            }
        }
        return flatline;
    }

    @Override
    public Stump getStump() {
        String id = "0";
        String keyword = kg.getSuperkey(id);
        String header = getHeader();
        return new Stump(id + keyword + id + header + id);
    }

    @Override
    public List<Object> getContent(){
        return tree;
    }

    @Override
    public DataFormat transform() {
        return solver.solve(this);
    }
}
