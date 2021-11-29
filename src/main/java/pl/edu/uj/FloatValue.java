package pl.edu.uj;

import java.util.Objects;

public class FloatValue extends Value {

    static final double THRESHOLD = .00001f;

    protected FloatValue() {

    }

    /*
     * See: https://stackoverflow.com/questions/2475259/can-i-override-and-overload-static-methods-in-java/5436790
     */
    public static Value create(String s) {
        FloatValue v = new FloatValue();
        v.val = Float.valueOf(s);
        return v;
    }

    @Override
    public Value add(Value val) {
        if (!(val instanceof FloatValue)) {
            throw new IllegalArgumentException("Should be of type '" + this.getClass() + "', but got '" + val.getClass() + "'");
        }
        assertNotNull((FloatValue) val);

        return FloatValue.builder()
                .setValue((float) this.val + (float) val.val)
                .build();
    }

    private void assertNotNull(FloatValue val) {
        if (val == null || val.val == null) {
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
        FloatValue other1 = (FloatValue) other;
        return other1.val.equals(this.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "FloatValue{" +
                "value=" + val +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        Object valueToSet;

        public Builder setValue(float value) {
            this.valueToSet = value;
            return this;
        }

        public Builder setValue(String s) {
            this.valueToSet = create(s).val;
            return this;
        }

        public FloatValue build() {
            FloatValue newFloatValue = new FloatValue();
            newFloatValue.val = valueToSet;
            return newFloatValue;
        }
    }
}
