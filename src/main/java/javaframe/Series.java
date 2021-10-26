package javaframe;

import java.util.ArrayList;
import java.util.List;

public class Series implements Cloneable {

    private String name;
    private String type;
    private ArrayList<Object> data;

    protected Series(String name, String type, ArrayList data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int size() {
        return data.size();
    }

    protected void addValue(Object value) {
        switch (type) {
            case "int":
                assert value instanceof Integer;
                int intValue = (Integer) value;
                data.add(intValue);
                break;
            case "String":
                assert value instanceof String;
                String stringValue = (String) value;
                data.add(stringValue);
                break;
            case "long":
                assert value instanceof Long;
                long longValue = (Long) value;
                data.add(longValue);
                break;
            case "float":
                assert value instanceof Float;
                float floatValue = (Float) value;
                data.add(floatValue);
                break;
            case "double":
                assert value instanceof Double;
                double doubleValue = (Double) value;
                data.add(doubleValue);
                break;
            case "boolean":
                assert value instanceof Boolean;
                boolean booleanValue = (Boolean) value;
                data.add(booleanValue);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<Object> getData() {
        return data;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
