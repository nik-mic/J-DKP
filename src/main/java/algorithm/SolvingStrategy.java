package algorithm;

import formats.DataFormat;

@FunctionalInterface
public interface SolvingStrategy <I extends DataFormat, O extends DataFormat> {
    public O solve(I i);
}
