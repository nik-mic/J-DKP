package entities;

import algorithm.F;
import formats.Parsetree;
import formats.Urbildelement;
import lombok.Value;

@Value
public class DkpUnparser implements Unparser {

    @Override
    public Urbildelement unparse(Object o) {
        return new F().solve(new Parsetree(o));
    }
}
