
/**
 * Group: Group 2
 * Authors: CAMPOL, RUSSEL JOSHUA MANGAMPO
 *          MEDIALDEA, RAYMUND LUIS CARDENAS
 *          MULDONG, JERICHO LUIS SICANGCO
 * Course: CSARCH2
 * Section: S12
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
                    output = "0" + "00000000000" + "0000000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "0" + " | " + "00000000000" + " | " + "0000000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 4:
                    output = "1" + "00000000000" + "0000000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "1" + " | " + "00000000000" + " | " + "0000000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 5:
                    output = "0" + "00000000000" + "1010101010101010101010101010101010101010101010101010";
                    txtOutput = "| " + "0" + " | " + "00000000000" + " | " + "1010101010101010101010101010101010101010101010101010" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 6:
                    output = "1" + "00000000000" + "1010101010101010101010101010101010101010101010101010";
                    txtOutput = "| " + "1" + " | " + "00000000000" + " | " + "1010101010101010101010101010101010101010101010101010" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 7:
                    output = "0" + "11111111111" + "0000000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "0" + " | " + "11111111111" + " | " + "0000000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 8:
                    output = "1" + "11111111111" + "0000000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "1" + " | " + "11111111111" + " | " + "0000000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 9:
                    output = "0" + "11111111111" + "0100000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "0" + " | " + "11111111111" + " | " + "0100000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 10:
                    output = "0" + "11111111111" + "1000000000000000000000000000000000000000000000000000";
                    txtOutput = "| " + "0" + " | " + "11111111111" + " | " + "1000000000000000000000000000000000000000000000000000" + " | ";
                    println(txtOutput);
                    hexadecimalEquivalent(output);
                    break;
                case 11:
                    outputTextFile();
                    break;
                case 12:
                    txtOutput = "";
                    txtHexOutputFinal = "";
                    break;
                case 13:
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
        println("3- Input +0 (Positive Zero)");
        println("4- Input -0 (Negative Zero)");
        println("5- Input +denormalized");
        println("6- Input -denormalized");
        println("7- Input +infinity");
        println("8- Input -infinity");
        println("9- Input sNaN");
        println("10- Input qNaN");
        println("11- Write recent output to a text file");
        println("12- Clear output");
        print("13- Quit\n>>");
    }
    public void binaryMantissa(){
        println("Mantissa: ");
        input = readDouble();
        mantissa = BigDecimal.valueOf(input);
        numDigitsMantissa = mantissa.precision();
        println("Exponent of base-2: ");
        exponent = readInt();
        normalize();
        //println("Mantissa: " + mantissa + " Exponent: " + exponent);
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
        //println("Mantissa: " + mantissa + " Exponent: " + exponent);
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
        int lengthExponentRep = String.valueOf(exponentRep).length();
        int paddingSize = 8 - lengthExponentRep;
        String stringExponentRep = String.valueOf(exponentRep);
        String formattedStringExponentRep = String.format("%011d", exponentRep);
        println(formattedStringExponentRep);

        String[] split = mantissa.toString().split("\\.");
        int i = split[1].length();   // counts the digit after decimal point
        BigDecimal fractionalPart = mantissa.remainder(BigDecimal.ONE).movePointRight(i);
        //BigDecimal fractionalPart = mantissa.remainder(BigDecimal.ONE);

        //int d = fractionalPart.intValue();
        int d = fractionalPart.intValue();
        String str = String.format("%" + i + "d", d);
        str = String.format("%1$-" + 52 + "s", str).replace(' ', '0'); //padding 0 for fractionalPart

        output = String.valueOf(sign) + formattedStringExponentRep + str;
        txtOutput = "| " + sign + " | " + formattedStringExponentRep + " | " + str + " | ";
        println("| " + sign + " | " + formattedStringExponentRep + " | " + str + " | ");
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

        //println(firstdigit);

        if(firstdigit.compareTo(new BigDecimal(1)) >= 0 || firstdigit.compareTo(new BigDecimal(-1)) <= 0){

            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                //mantissa = mantissa.movePointLeft(1);
                mantissa = mantissa.divide(new BigDecimal("10.0"));
                input = input / 10.0;
                exponent++;
                //println(Math.floor(input));
            }
            //mantissa = mantissa.round(new MathContext(numDigitsMantissa));
        } else {
            while(Math.floor(input) != 1 && Math.ceil(input) != -1){
                //mantissa = mantissa.movePointRight(1);
                mantissa = mantissa.multiply(new BigDecimal("10.0"));
                input = input * 10.0;
                exponent--;
                //println(Math.floor(input));
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