public class IntHolder implements Sravnimoe<Integer> {
    private int value;

    public IntHolder(int value) { this.value = value; }

    @Override public int sravnit(Integer other) {
        return Integer.compare(this.value, other);
    }

    @Override public String toString() { return "IntHolder(" + value + ")"; }
}
