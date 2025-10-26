public class MaxFinder {

    public static double findMax(Box<? extends Number>[] boxes) {
        Double max = null;

        for (Box<? extends Number> box : boxes) {
            if (!box.isEmpty()) {
                double value = box.peek().doubleValue();
                if (max == null || value > max) {
                    max = value;
                }
            }
        }

        return max == null ? Double.NaN : max;
    }
}
