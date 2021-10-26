package javaframe;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataFrame implements Cloneable {

    private List<String> colnames;
    private List<String> coltypes;
    private List<Series> data;

    public DataFrame(String[] colnames, String[] coltypes) {
        this.colnames = Arrays.asList(colnames);
        this.coltypes = Arrays.asList(coltypes);
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
                    throw new IllegalArgumentException("Unknown column type= " + type);
            }
        }
    }

    public int size() {
        if (data.isEmpty()) {
            return 0;
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

    public Series get(String[]cols,boolean copy) {
        // shallow copy - if the value of copy is false
     /*   if(copy == false) {
            return clone();
            //return DataFrame newdf = (DataFrame) super.clone();
        }
        // deep copy - if the value of copy is true
        else if(copy == true) {

            DataFrame df = (DataFrame) super.clone();
            df.data = (Series) data.clone();

        }*/

    }
    public DataFrame addColumn(String colname, String dtype, boolean inplace) {
        if (inplace) {
            switch (dtype) {
                case "int":
                    data.add(new Series(colname, dtype, new ArrayList<Integer>()));
                    break;
                case "String":
                    data.add(new Series(colname, dtype, new ArrayList<String>()));
                    break;
                case "long":
                    data.add(new Series(colname, dtype, new ArrayList<Long>()));
                    break;
                case "float":
                    data.add(new Series(colname, dtype, new ArrayList<Float>()));
                    break;
                case "double":
                    data.add(new Series(colname, dtype, new ArrayList<Double>()));
                    break;
                case "boolean":
                    data.add(new Series(colname, dtype, new ArrayList<Boolean>()));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown column type= " + dtype);
            }
            return this;
        } else {
            DataFrame df = new DataFrame((String[]) colnames.toArray(), (String[]) coltypes.toArray());
            df.addColumn(colname, dtype, true);
            return df;
        }
    }
    public DataFrame addRow(String[] values, boolean inplace) {
        if (inplace) {
            for(int i  = 0; i < data.size(); i++) {
                data.get(i).addValue(values[i]);
            }
            return this;
        } else {
            DataFrame df = new DataFrame((String[]) colnames.toArray(), (String[]) coltypes.toArray());
            df.addRow(values, inplace);
            return df;
        }
    }

    public DataFrame iloc(int i) {

    }
    public DataFrame iloc(int from,int to) {


    }




    @Override
    public DataFrame clone() throws CloneNotSupportedException {
        return (DataFrame) super.clone();
    }

}
