package pl.edu.uj;

import jdk.jshell.spi.ExecutionControl;
import org.junit.platform.commons.logging.LoggerFactory;


import java.util.logging.Logger;

public abstract class Value {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Value.class);
    protected Object val;

    /*
     * See: https://stackoverflow.com/questions/370962/why-cant-static-methods-be-abstract-in-java
     */
    public static Value create(String s) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException(s);
    }

    public abstract Value add(Value val);

    public Object getVal() {
        return val;
    }

    public abstract Value sub(Value val);

    public abstract Value mul(Value val);

    public abstract Value div(Value val);

    public abstract Value pow(Value val);

    public abstract boolean eq(Value val);

    public abstract boolean lte(Value val);

    public abstract boolean gte(Value val);

    public abstract boolean neq(Value val);

    public abstract boolean equals(Object other);

    public abstract int hashCode();

    public void print() {
        logger.info(val.toString());
    }

}
