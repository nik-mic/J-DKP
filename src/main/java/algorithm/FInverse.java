package algorithm;

import formats.Parsetree;
import formats.Urbildelement;
import java.util.*;

public class FInverse implements SolvingStrategy<Urbildelement, Parsetree> {

    @Override
    public Parsetree solve(Urbildelement ube) {
        final List<String> keys = ube.getKeys();
        final String rawUbe = cutOfHeader(ube);
        List<Object> pt = Arrays.asList(rawUbe.split(keys.get(0)));
        pt = pt.subList(1, pt.size());
        for (String k : keys) {
            pt = levelLoop(pt, k);
        } return new Parsetree(pt);
    }

    private String cutOfHeader(Urbildelement ube) {
        return ube.getUbeContent().substring(ube.getStump().getContent().length());
    }

    private List<Object> levelLoop(List<Object> pt, String key) {
        final List<Object> newPt = new ArrayList<>();
        pt.forEach(o -> {
            if (o instanceof List) {
                newPt.add(levelLoop((List<Object>)o, key));
            } else {
                newPt.add(parseLevel((String)o, key));
            }
        });
        return newPt;
    }

    private Object parseLevel(String upt, String levelKey) {
        if (upt.contains(levelKey)) {
            List<Object> pt = Arrays.asList(upt.split(levelKey));
            return pt.subList(1, pt.size());
        } return upt;
    }
}
