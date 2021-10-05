package formats;

import java.util.List;

public interface DataFormat {
    Stump getStump();
    Content getContent();
    DataFormat transform();
    default List<String> getKeys() {
        return getStump().getKeys();
    }
}
