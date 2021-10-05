package formats;

import lombok.Value;
import algorithm.FInverse;
import algorithm.SolvingStrategy;

@Value
public class Urbildelement implements DataFormat {
    String ubeContent;
    SolvingStrategy<Urbildelement, Parsetree> solve = new FInverse();

    public String getHeader() {
        return getParts()[2];
    }

    public String getSuperkey() {
        return getParts()[1];
    }

    private String getIdentifier() {
        return String.valueOf(ubeContent.charAt(0));
    }

    private String[] getParts() {
        return ubeContent.split(getIdentifier());
    }

    @Override
    public Stump getStump() {
        return new Stump(getIdentifier() + getSuperkey() + getIdentifier() + getHeader() + getIdentifier());
    }

    @Override
    public Content getContent() {
        return new Content(ubeContent.substring(getStump().getContent().length()));
    }

    @Override
    public DataFormat transform() {
        return solve.solve(this);
    }

}

