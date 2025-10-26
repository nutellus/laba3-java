public class Box<T> {
    private T value;

    public Box() {
        this.value = null;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public void put(T newValue) {
        if (!isEmpty()) {
            throw new IllegalStateException("Коробка уже занята!");
        }
        this.value = newValue;
    }

    public T get() {
        T temp = this.value;
        this.value = null;
        return temp;
    }

    public T peek() {
        return value;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Коробка пустая" : "В коробке: " + value;
    }
}
