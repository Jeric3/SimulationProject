
/**
 *
 * Authors:
 * Course:
 * Section:
 */
import acm.program.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math.*;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Objects;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class SimulationProject extends ConsoleProgram {
    private int continueValue = 0;
    private double input;
    private BigDecimal mantissa;
    private int numDigitsMantissa;
    private int choice;
    private int exponent;
    private int sign;
    private long exponentRep;
    private int fractionSig;
    private String output = "";
    private String txtOutput = "";
    private String txtHexOutput = "";
    private String txtHexOutputFinal = "";
    private String printYesNo = "";

    DecimalFormat decFormat = new DecimalFormat("000000000000");
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
                    outputTextFile();
                    break;
                case 5:
                    continueValue = 1;
                    break;
                default:
                    break;
            }
        }
        System.exit(0);
    }
    private void operations() {
        println("\nChoose Operation");
        println("1- Input binary mantissa and base-2 (i.e., 101.01x2^5)");
        println("2- Input decimal mantissa and base-10 (i.e., 15.75x10^5)");
        println("3- Input NaN");
        println("4- Write recent output to a text file");
        print("5- Quit\n>>");
    }
    public void binaryMantissa(){
        println("Mantissa: ");
        input = readDouble();
        mantissa = BigDecimal.valueOf(input);
        numDigitsMantissa = mantissa.precision();
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
        mantissa = BigDecimal.valueOf(input);
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

        String[] split = mantissa.toString().split("\\.");
        int i = split[1].length();   // counts the digit after decimal point
        BigDecimal fractionalPart = mantissa.remainder(BigDecimal.ONE).movePointRight(i+(52-i));
        //BigDecimal fractionalPart = mantissa.remainder(BigDecimal.ONE);

//        int d = fractionalPart.intValue();
        String str = String.valueOf(fractionalPart);
        String str2 = str;
//        println(String.valueOf(mantissa).charAt(2));
        if(String.valueOf(mantissa).charAt(2) == '0' || (String.valueOf(mantissa).charAt(0) == '-' && String.valueOf(mantissa).charAt(3) == '0')){
            str2 = "0" + str;
        }
        String str3 = str2.replace("-", "");
//        String str = String.format("%052d", d); //padding 0 for fractionalPart
//        String str = String.format("%d", d); //padding 0 for fractionalPart

        output = String.valueOf(sign) + String.valueOf(exponentRep) + str3;
        txtOutput = "| " + sign + " | " + exponentRep + " | " + str3 + " | ";
        println("| " + sign + " | " + exponentRep + " | " + str3 + " | ");
        //println("| " + sign + " | " + exponentRep + " | " + fractionalPart + " | ");
        //println( "" + sign  + exponentRep + fractionalPart);
        hexadecimalEquivalent(output);
//        println("Write output in a text file? [Y/N]");
//        printYesNo = scanner.nextLine();
//        if(Objects.equals(printYesNo, "Y") || Objects.equals(printYesNo, "y"))
//            outputTextFile();
    }
    private void hexadecimalEquivalent(String binaryString) {
        int digitNumber = 1;
        int sum = 0;
        String binary = binaryString;
        for(int i = 0; i < binary.length(); i++){
            if(digitNumber == 1)
                sum+=Integer.parseInt(binary.charAt(i) + "")*8;
            else if(digitNumber == 2)
                sum+=Integer.parseInt(binary.charAt(i) + "")*4;
            else if(digitNumber == 3)
                sum+=Integer.parseInt(binary.charAt(i) + "")*2;
            else if(digitNumber == 4 || i < binary.length()+1){
                sum+=Integer.parseInt(binary.charAt(i) + "")*1;
                digitNumber = 0;
                if(sum < 10){
                    txtHexOutput = String.valueOf(sum);
                    txtHexOutputFinal = txtHexOutputFinal.concat(txtHexOutput);
//                    print(sum);
                }
                else if(sum == 10)
                    txtHexOutputFinal = txtHexOutputFinal.concat("A");
//                    print("A");
                else if(sum == 11)
                    txtHexOutputFinal = txtHexOutputFinal.concat("B");
//                    print("B");
                else if(sum == 12)
                    txtHexOutputFinal = txtHexOutputFinal.concat("C");
//                    print("C");
                else if(sum == 13)
                    txtHexOutputFinal = txtHexOutputFinal.concat("D");
//                    print("D");
                else if(sum == 14)
                    txtHexOutputFinal = txtHexOutputFinal.concat("E");
//                    print("E");
                else if(sum == 15)
                    txtHexOutputFinal = txtHexOutputFinal.concat("F");
//                    print("F");
                sum=0;
            }
            digitNumber++;
        }
        println(txtHexOutputFinal);
    }
    private void outputTextFile(){
        try {
            File fileObj = new File(System.getProperty("user.dir")+"\\output.txt");
            if (fileObj.createNewFile()) {
                System.out.println("File created: " + fileObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+"\\output.txt");
            fileWriter.write(txtOutput);
            fileWriter.write("\r\n");
            fileWriter.write(txtHexOutputFinal);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
                //mantissa = mantissa.movePointLeft(1);
                mantissa = mantissa.divide(new BigDecimal("10.0"));
                input = input / 10.0;
                exponent++;
                println(Math.floor(input));
            }
            //mantissa = mantissa.round(new MathContext(numDigitsMantissa));
        } else {
            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                //mantissa = mantissa.movePointRight(1);
                mantissa = mantissa.multiply(new BigDecimal("10.0"));
                input = input * 10.0;
                exponent--;
                println(Math.floor(input));
            }
            //mantissa = mantissa.round(new MathContext(numDigitsMantissa));
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