
/**
 *
 * Authors:
 * Course:
 * Section:
 */
import acm.program.*;
public class SimulationProject extends ConsoleProgram {
    private int continueValue = 0;
    private float input;
    private int exponent;
    public void run() {
        println("IEEE-754 Binary-64 Floating Point Converter");
        while (continueValue != 1 ) {
            operations();
            switch (input){
                case 1:
                    binaryMantissa();
                    break;
                case 2:
                    decimalMantissa();
                    break;
                case 3:
                    naN();
                    break;
                case 7:
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
        print("7- Quit\n>>");
    }
    public void binaryMantissa(){
        println("Mantissa: ");
        input = readFloat();
        println("Exponent of base-2: ");
        exponent = readInt();
        normalize();
    }
    private void decimalMantissa(){
        println("Mantissa: ");
        input = readFloat();
        println("Exponent of base-10: ");
        exponent = readInt();
        convertToBinary();
        normalize();
    }
    private void naN(){
        println("TODO 3\n");
    }
    private void binaryOutput(){
        println("TODO 4\n");
    }
    private void hexadecimalEquivalent() {
        println("TODO 5\n");
    }
    private void outputTextFile(){
        println("TODO 6\n");
    }
    private void convertToBinary(){
        //String bin = Integer.toBinaryString(input);
        //input = Integer.parseInt(bin);
    }
    private void normalize(){
        int firstDigit = Integer.parseInt(Integer.toString(number).substring(0, 1));
        if (firstDigit == 1){
            while(floor(input) != 1){
                input = input / 10;
                exponent++;
            }
            
        } else if (firstDigit == 0){
            while(floor(input) != 1){
                input = input * 10;
                exponent--;
            }
        }
    }
    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new SimulationProject().start(args);
    }
}
