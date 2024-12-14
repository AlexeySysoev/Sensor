package utils;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class DataGenerator {
    private static final double MAX = 100.0d;
    private static final double MIN = -100.0d;
    public static double genTemperature() {
        double num =  MIN + new Random().nextDouble() * (MAX - MIN);
        DecimalFormatSymbols frm = new DecimalFormatSymbols();
        frm.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("##.#", frm);
        return Double.parseDouble(decimalFormat.format(num));
    }

    public static boolean genIsRaining() {
        return new Random().nextBoolean();
    }
}
