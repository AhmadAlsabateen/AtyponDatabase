package dto;

import java.util.HashMap;
import java.util.Map;

public class Filter {
    public Map<String, Object> fields;

    public Filter(){
        fields = new HashMap<>();
    }
    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Filter) {
            return ((Filter) obj).fields.equals(fields);
        }
        return false;
    }
}
