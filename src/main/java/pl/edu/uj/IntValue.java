package pl.edu.uj;

import java.util.Objects;

public class IntValue extends Value {

    protected IntValue() {

    }


    public static Value create(String s) {
        IntValue v = new IntValue();
        v.val = Integer.valueOf(s);
        return v;
    }

    @Override
    public Value add(Value val) {
        if (!(val instanceof IntValue)) throw new AssertionError();
        if (val.val == null) throw new AssertionError();

        return IntValue.builder()
                .setValue((int) this.val + (int) val.val)
                .build();
    }

    private void assertNotNull(IntValue val) {
        if (val ==  null || val.val == null) {
            throw new IllegalArgumentException("Should not be null, but got '" + val + "'");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        IntValue other1 = (IntValue) other;
        return other1.val.equals(this.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "IntValue{" +
                "value=" + val +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Object valueToSet;

        public Builder setValue(int value) {
            this.valueToSet = value;
            return this;
        }

        public Builder setValue(String s) {
            this.valueToSet = create(s).val;
            return this;
        }

        public IntValue build() {
            IntValue newIntValue = new IntValue();
            newIntValue.val = valueToSet;
            return newIntValue;
        }
    }
}
