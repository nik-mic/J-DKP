package entities;

import algorithm.FInverse;
import formats.Parsetree;
import formats.Urbildelement;

public class DkpParser implements Parser {
    @Override
    public Parsetree parse(String ube) {
        return new FInverse().solve(new Urbildelement(ube));
    }
}
