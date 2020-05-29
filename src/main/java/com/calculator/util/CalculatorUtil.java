package com.calculator.util;

import com.calculator.constance.StatusConstants;
import com.calculator.exception.UtilException;
import org.springframework.util.StringUtils;

public class CalculatorUtil {

    public static double value(String c) {
        return Double.parseDouble(c);
    }

    /**
     * This method is doing all Multiplication And Division Operation
     * @param splitInput
     * @return
     */
    public static String[] multiplicationAndDivisionOperation(String[] splitInput) {
        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].contains("*")) {
                splitInput[i] = calculateDivisionOrMultiplicationByOperator(splitInput[i], "*");
            }
            if (splitInput[i].contains("/")) {
                splitInput[i] = calculateDivisionOrMultiplicationByOperator(splitInput[i], "/");
            }
        }
        return splitInput;
    }

    /**
     * This method is calculating Division Or Multiplication By Operator
     * @param input e.g. 3+2*5
     * @param operator e.g. "*" or "/"
     * @return String
     */
    public static String calculateDivisionOrMultiplicationByOperator(String input, String operator) {
        String[] splitInput = input.split("(?<=[" + operator + "])|(?=[" + operator + "])");
        for (int i = 0; i < splitInput.length - 1; i = i + 2) { //  5 / 4 / 3+2
            String opr = splitInput[i + 1], opd1 = calculateDivisionOrMultiplicationByOperator(splitInput[i], operator.equals("*") ? "/" : "*"), opd2 = calculateDivisionOrMultiplicationByOperator(splitInput[i + 2], operator.equals("*") ? "/" : "*");
            splitInput[i + 2] = String.valueOf(calculateOperation(opr, opd1, opd2));
        }
        return splitInput[splitInput.length - 1];
    }

    /**
     * This method is doing all Addition and Subtraction Operations
     * @param splitInput
     * @return double
     */
    public static double additionAndSubtractionOperation(String[] splitInput) {
        double finalValue = splitInput.length == 1 ? Double.parseDouble(splitInput[0]) : 0;
        int i = 0;
        while (i < splitInput.length - 1) {
            if (splitInput[i].equals("+")) {
                String opr = splitInput[i + 2], opd1 = splitInput[i + 1], opd2 = splitInput[i + 3];
                splitInput[i + 3] = String.valueOf(calculateOperation(opr, opd1, opd2));
                finalValue = Double.parseDouble(splitInput[i + 3]);
                i = i + 3;
            } else if (splitInput[i].equals("-")) {
                String opr = splitInput[i + 2], opd1 = "-" + splitInput[i + 1], opd2 = splitInput[i + 3];
                splitInput[i + 3] = String.valueOf(calculateOperation(opr, opd1, opd2));
                finalValue = Double.parseDouble(splitInput[i + 3]);
                i = i + 3;
            } else {
                String opr = splitInput[i + 1], opd1 = splitInput[i], opd2 = splitInput[i + 2];
                splitInput[i + 2] = String.valueOf(calculateOperation(opr, opd1, opd2));
                finalValue = Double.parseDouble(splitInput[i + 2]);
                i = i + 2;
            }
        }
        return finalValue;
    }

    /**
     * This method can take any valid number with operator
     * e.g. 3+2*5 = 13
     * @param input
     * @return String
     */
    public static String calculate(String input) {
        try {
            if (!StringUtils.isEmpty(input)) {
                double finalValue = 0;
                String[] splitInput = input.split("(?<=[-+])|(?=[-+])");

                if (input.contains("*") || input.contains("/")) {
                    splitInput = multiplicationAndDivisionOperation(splitInput);
                }
                finalValue = additionAndSubtractionOperation(splitInput);
                return String.valueOf(finalValue);
            } else {
                throw new UtilException(StatusConstants.HttpConstants.EMPTY_INPUT);
            }
        } catch (NumberFormatException e) {
            throw new UtilException(StatusConstants.HttpConstants.INVALID_NUMBER);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UtilException(StatusConstants.HttpConstants.INVALID_INPUT);
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * This method is calculating firstOperand and secondOperand based on operator
     * @param operator
     * @param firstOperand
     * @param secondOperand
     * @return
     */
    public static double calculateOperation(String operator, String firstOperand, String secondOperand) {
        double value = 0;
        if (operator.equals("+")) value = value(firstOperand) + value(secondOperand);
        else if (operator.equals("-")) value = value(firstOperand) - value(secondOperand);
        else if (operator.equals("*")) value = value(firstOperand) * value(secondOperand);
        else if (operator.equals("/")) value = value(firstOperand) / value(secondOperand);
        return value;
    }

}
