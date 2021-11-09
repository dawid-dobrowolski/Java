import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataFrame {

    private ArrayList<String> colnames;
    private ArrayList<String> coltypes;
    private ArrayList<Series> data;

    public DataFrame(String[] colnames, String[] coltypes) {
        this.colnames = new ArrayList(Arrays.asList(colnames));
        this.coltypes = new ArrayList(Arrays.asList(coltypes));
        assert this.colnames.size() == this.coltypes.size();

        data = new ArrayList<>(this.colnames.size());

        Iterator<String> itColnames = this.colnames.iterator();
        Iterator<String> itColtypes = this.coltypes.iterator();
        while (itColnames.hasNext() && itColtypes.hasNext()) {
            String type = itColtypes.next();
            switch (type) {
                case "int":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Integer>()));
                    break;
                case "String":
                    data.add(new Series(itColnames.next(), type, new ArrayList<String>()));
                    break;
                case "long":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Long>()));
                    break;
                case "float":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Float>()));
                    break;
                case "double":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Double>()));
                    break;
                case "boolean":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Boolean>()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown column type =" + type);

            }
        }
    }

    private DataFrame(List<String> colnames, List<String> coltypes, List<Series> data) {
        this.colnames = new ArrayList(Arrays.asList(colnames));
        this.coltypes = new ArrayList(Arrays.asList(coltypes));
        assert this.colnames.size() == this.coltypes.size();

        data = new ArrayList<>(this.colnames.size());

        Iterator<String> itColnames = this.colnames.iterator();
        Iterator<String> itColtypes = this.coltypes.iterator();
        while (itColnames.hasNext() && itColtypes.hasNext()) {
            String type = itColtypes.next();
            switch (type) {
                case "int":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Integer>()));
                    break;
                case "String":
                    data.add(new Series(itColnames.next(), type, new ArrayList<String>()));
                    break;
                case "long":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Long>()));
                    break;
                case "float":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Float>()));
                    break;
                case "double":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Double>()));
                    break;
                case "boolean":
                    data.add(new Series(itColnames.next(), type, new ArrayList<Boolean>()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown column type =" + type);

            }
        }
    }

    public int size() {
        if (data.isEmpty()) {
            return 0;
        }
        final int size = data.get(0).size();
        for (Series s : data) {
            assert size == s.size();
        }
        return data.get(0).size();
    }

    public Series get(String colname) {
        int index = -1;
        for (String name : colnames) {
            index++;
            if (name.equals(colname)) {
                return data.get(index);
            }
        }
        // no column
        return null;
    }



    public void ilocSet(int i, String c, String value) {
        int index = -1;
        for (String colname : colnames) {
            index++;
            if (colname.equals(c)) {
                final Series series = new Series(this.colnames.get(i), this.coltypes.get(i), this.data);
                series.setValue(i,value,coltypes.get(i));
                return;
            }
        }
        throw new IllegalArgumentException("Column '" + c + "' not in DataFrame");
    }




    public DataFrame iloc(int i) {
        return iloc(i, i);
    }


    public DataFrame iloc(int from, int to) {
        return get(colnames.toArray(new String[0]), true, from, to);
    }

    public DataFrame get(String[] cols, boolean copy) {
        return get(cols, copy, -1, -1);
    }

    public DataFrame get(String[] cols, boolean copy, int from, int to) {
        List<String> newColnames = new ArrayList<>();
        List<String> newColtypes = new ArrayList<>();
        List<Series> newData = new ArrayList<>();

        int index;
        for (String columnsToAddNames : cols) {
            index = -1;
            for (String colname : colnames) {
                index++;
                if (columnsToAddNames.equals(colname)) {
                    newColnames.add(columnsToAddNames);
                    newColtypes.add(this.coltypes.get(index));
                    final Series series = new Series(this.colnames.get(index), this.coltypes.get(index), this.data);
                    if (from >= 0) {
                        assert to >= from;
                        newData.add(this.data.get(index));
                    } else {
                        newData.add(this.data.get(index));
                    }
                }
            }
        }
        return new DataFrame(newColnames, newColtypes, newData);
    }

    public DataFrame addColumn(String colname, String dtype, boolean inplace) {
        DataFrame df;
        if (inplace) {
            df = this;
        } else {
            df = new DataFrame(this.colnames,this.coltypes,this.data);
        }
        df.colnames.add(colname);
        df.coltypes.add(dtype);

        Series emptySeries = new Series(colname, dtype);
        for (int i = 0; i < df.size(); i++) {
            emptySeries.addValue(colname, dtype);
        }
        df.data.add(emptySeries);

        return df;
    }

    protected DataFrame copy() {
        return get(colnames.toArray(new String[0]), true);
    }


    public DataFrame addRow(String[] datarow, boolean inplace) {
        assert datarow.length == this.coltypes.size();

        DataFrame df;
        if (inplace) {
            df = this;
        } else {
            df = new DataFrame(this.colnames,this.coltypes,this.data);
        }

        int i = -1;
        for (String type : df.coltypes) {
            i++;
            df.data.get(i).addValue(datarow[i],type);
        }

        return df;
    }}