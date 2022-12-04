
/**
 *
 * Authors:
 * Course:
 * Section:
 */
import acm.program.*;
public class SimulationProject extends ConsoleProgram {
    private int continueValue = 0;
    public void run() {
        println("IEEE-754 Binary-64 Floating Point Converter");
        while (continueValue != 1 ) {
            operations();
            int input = readInt();
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
                case 4:
                    binaryOutput();
                    break;
                case 5:
                    hexadecimalEquivalent();
                    break;
                case 6:
                    outputTextFile();
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
        println("4- Output binary output with space between section");
        println("5- Output its hexadecimal equivalent");
        println("6- Output with option to output in text file");
        print("7- Quit\n>>");
    }
    public void binaryMantissa(){
        System.out.println("TODO 1");
    }
    private void decimalMantissa(){
        System.out.println("TODO 2");
    }
    private void naN(){
        System.out.println("TODO 3");
    }
    private void binaryOutput(){
        System.out.println("TODO 4");
    }
    private void hexadecimalEquivalent() {
        System.out.println("TODO 5");
    }
    private void outputTextFile(){
        System.out.println("TODO 6");
    }
    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new SimulationProject().start(args);
    }
}
