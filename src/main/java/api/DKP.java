package api;

import entities.DkpParser;
import entities.DkpUnparser;
import formats.DataFormat;

public enum DKP {
    INSTANCE;

    public DataFormat transform(DataFormat in){
        return in.transform();
    }
    public final String out(Object rawPt){
        return new DkpUnparser().unparse(rawPt).getUbeContent();
    }
    public final Object in(String rawUbe){
        return new DkpParser().parse(rawUbe);
    }
}
