package algorithm;

import formats.Content;
import formats.Parsetree;
import formats.Urbildelement;
import java.util.ArrayList;
import java.util.List;

public class F implements SolvingStrategy<Parsetree, Urbildelement> {

    @Override
    public Urbildelement solve(Parsetree pt) {
        return build(pt);
    }

    private Urbildelement build(Parsetree pt) {
        Content content = getContent(pt);
        return new Urbildelement(pt.getStump().getContent() + content.getContent());
    }

    public Content getContent(Parsetree pt) {
        List<String> keys = pt.getStump().getKeys();
        String content = addInList(pt.getTree(), keys);
        return new Content(content);
    }

    private String addInList(List<Object> o, List<String> keys){
        StringBuilder out = new StringBuilder();
        o.forEach(l -> {
            if(l instanceof List){
                List<String> tmpKeys = new ArrayList<>(List.copyOf(keys));
                tmpKeys.remove(0);
                out.append(keys.get(0)).append(addInList((List<Object>)l, tmpKeys));
            } else {
                out.append(keys.get(0)).append(l.toString());
            }
        });
        return out.toString();
    }
}

