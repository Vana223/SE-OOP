public class task {
    /**
     * Клас для 16-річних подання цілісних значень координат
     * @param args
     */
    public static void main(String[] args) {
        double v0 = 10;
        double alpha = Math.toRadians(45);
        double g = 9.8;
        double t;

        for (int i = 0; i < 16; i++) {
            t = i;
            double x = v0 * Math.cos(alpha) * t;
            double y = v0 * Math.sin(alpha) * t - (g * t * t) / 2;

            System.out.println("Час " + t + ": x = " + x + ", y = " + y);
        }
    }
}
