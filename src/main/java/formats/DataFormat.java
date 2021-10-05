package formats;

import java.util.List;

public interface DataFormat {
    Stump getStump();
    DataFormat transform();
    Object getContent();
    default List<String> getKeys() {
        return getStump().getKeys();
    }
}
