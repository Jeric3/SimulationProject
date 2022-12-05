
/**
 *
 * Authors:
 * Course:
 * Section:
 */
import acm.program.*;
import java.lang.Math.*;
import java.math.MathContext;
import java.util.Scanner;
import java.math.BigDecimal;


public class SimulationProject extends ConsoleProgram {
    private int continueValue = 0;
    private double input;
    private BigDecimal mantissa;
    private int choice;
    private int exponent;
    private int sign;
    private long exponentRep;
    private int fractionSig;
    Scanner scanner = new Scanner(System.in);
    public void run() {
        println("IEEE-754 Binary-64 Floating Point Converter");
        while (continueValue != 1 ) {
            operations();
            choice = readInt();
            switch (choice){
                case 1:
                    binaryMantissa();
                    break;
                case 2:
                    decimalMantissa();
                    break;
                case 3:
                    naN();
                    break;
                case 4:
                    continueValue = 1;
                    break;
                default:
                    break;
            }
        }
        System.exit(0);
    }
    private void operations() {
        println("Choose Operation");
        println("1- Input binary mantissa and base-2 (i.e., 101.01x2^5)");
        println("2- Input decimal mantissa and base-10 (i.e., 15.75x10^5)");
        println("3- Input NaN");
        print("4- Quit\n>>");
    }
    public void binaryMantissa(){
        println("Mantissa: ");
        input = readDouble();
        mantissa = new BigDecimal(input);
        println("Exponent of base-2: ");
        exponent = readInt();
        normalize();
        println("Mantissa: " + mantissa + " Exponent: " + exponent);
        binaryOutput();
    }
    private void decimalMantissa(){
        println("Mantissa: ");
        input = readDouble();
        println("Exponent of base-10: ");
        exponent = readInt();
        String str = convertToBinary(input);
        input = Double.valueOf(str);
        mantissa = new BigDecimal(input);
        normalize();
        println("Mantissa: " + mantissa + " Exponent: " + exponent);
        binaryOutput();
    }
    private void naN(){
        println("TODO 3\n");
    }
    private void binaryOutput(){
        if(mantissa.compareTo(new BigDecimal(0)) >= 0){
            sign = 0;
        } else {
            sign = 1;
        }

        int newExponent = exponent + 1023;
        exponentRep = Long.parseLong(Integer.toBinaryString(newExponent));

        println("| " + sign + " | " + exponentRep + " | ");

    }
    private void hexadecimalEquivalent() {
        println("TODO 5\n");
    }
    private void outputTextFile(){
        println("TODO 6\n");
    }

    public String convertToBinary(Double d){
        long wholePart = Double.valueOf(d).longValue();
        return Long.toBinaryString(wholePart) + '.' + fractionalToBinary(d - wholePart) + '0';
    }

    public String fractionalToBinary(Double d){
        StringBuilder bin = new StringBuilder();
        while(d > 0 && bin.length() < 22){
            double r = d * 2;
            if (r >= 1){
                bin.append(1);
                d = r - 1;
            } else {
                bin.append(0);
                d = r;
            }
        }
        return bin.toString();
    }
    /**private void convertToBinary(){
        String bin = Long.toBinaryString(Double.doubleToRawLongBits(input));
        input = Integer.parseInt(bin);
        mantissa = new BigDecimal(input);
    }**/
    private void normalize(){
        /**int firstDigit;
        if (input >= 0){
            firstDigit = Integer.parseInt(Double.toString(input).substring(0, 1));
        } else {
            firstDigit = Integer.parseInt(Double.toString(input).substring(0, 2));
        }**/

        BigDecimal firstdigit = mantissa.round(new MathContext(1));

        println(firstdigit);

        if(firstdigit.compareTo(new BigDecimal(1)) >= 0 || firstdigit.compareTo(new BigDecimal(-1)) <= 0){

            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                mantissa = mantissa.movePointLeft(1);
                //mantissa = mantissa.divide(BigDecimal.TEN);
                input = input / 10.0;
                exponent++;
                println(Math.floor(input));
            }
        } else {
            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                mantissa = mantissa.movePointRight(1);
                //mantissa = mantissa.multiply(BigDecimal.TEN);
                input = input * 10.0;
                exponent--;
                println(Math.floor(input));

            }
        }

        /**if (firstDigit == 1){
            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                mantissa = mantissa.movePointLeft(1);
                //mantissa = mantissa.divide(new BigDecimal(10));
                input = input / 10.0;
                exponent++;
                println(Math.floor(input));
            }

        } else if (firstDigit == 0){
            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                mantissa = mantissa.movePointRight(1);
                //mantissa = mantissa.multiply(new BigDecimal(10));
                input = input * 10.0;
                exponent--;
                println(Math.floor(input));

            }
        }**/
    }
    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new SimulationProject().start(args);
    }
}