import java.util.ArrayList;
import java.util.List;

public class Series {

    private String name;
    private String type;
    private List data;

    protected Series(String name, String type, ArrayList data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    protected Series(String name, String type) {
        this.name = name;
        this.type = type;
        if (!"int".equals(type) && !"String".equals(type) && !"long".equals(type) && !"short".equals(type) && !"float".equals(type) && !"double".equals(type) && !"boolean".equals(type)) {
            throw new IllegalArgumentException("Unknown column type= " + type);
        }
        this.data = new ArrayList();
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

    protected void addValue(String value, String type) {
        assert this.type.equals(type);
        switch (type) {
            case "int":
                data.add(Integer.valueOf(value));
                break;
            case "String":
                data.add(String.valueOf(value));
                break;
            case "long":
                data.add(Long.valueOf(value));
                break;
            case "short":
                data.add(Short.valueOf(value));
                break;
            case "float":
                data.add(Float.valueOf(value));
                break;
            case "double":
                data.add(Double.valueOf(value));
                break;
            case "boolean":
                data.add(Boolean.valueOf(value));
                break;
            default:
                throw new IllegalArgumentException("Unknown column type= " + type);
        }
    }

    public List<Object> getData() {
        return data;
    }

    Series copy(boolean deep) {
        if (deep) {
            ArrayList<Object> newData = new ArrayList<>(getData());

            return new Series(name, type, newData);
        }
        else {
            String x=this.name;
            String y=this.type;
            ArrayList z= (ArrayList) this.data;
            return new Series(x, y, z);
        }
    }

    Series getSliceCopy(int from, int to) {
        assert this.size() > to;

        List newData = new ArrayList<>( this.data.subList(from, to+1));
        return new Series(name, type, (ArrayList) newData);

    }

    public void setValue(int i, String value, String type) {
        assert i < size();
        assert type.equals(this.type);

        switch (type) {
            case "int":
                data.set(i, Integer.valueOf(value));
                break;
            case "String":
                data.set(i, String.valueOf(value));
                break;
            case "long":
                data.set(i, Long.valueOf(value));
                break;
            case "short":
                data.set(i, Short.valueOf(value));
                break;
            case "float":
                data.set(i, Float.valueOf(value));
                break;
            case "double":
                data.set(i, Double.valueOf(value));
                break;
            case "boolean":
                data.set(i, Boolean.valueOf(value));
                break;
            default:
                throw new IllegalArgumentException("Unknown column type= " + type);
        }
    }
}
