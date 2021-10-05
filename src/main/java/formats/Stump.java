package formats;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Stump {
    String content;
    public String getIdentifier(){
        return String.valueOf(content.charAt(0));
    }
    public String getSuperkey(){
        return getParts()[1];
    }
    public String getHeader(){
        return getParts()[2];
    }
    private String[] getParts(){
        return content.split(getIdentifier());
    }

    public List<String> getKeys(){
        String header = getHeader().substring(1);
        String superkey = getSuperkey();
        List<String> keys = new ArrayList<>();
        keys.add(superkey);
        if (header.length() > 0) {
            for (String h : header.split("")) {
                superkey = superkey.substring(0, superkey.length() - Integer.parseInt(h));
                keys.add(superkey);
            }
        } return keys;
    }
}
