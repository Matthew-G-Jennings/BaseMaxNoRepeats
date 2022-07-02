package BaseMaxNoRepeats;

import java.lang.Math;

/**
 * Class for handling the storage and management of numbers
 * with a given base.
 * Limitations: Used exclusively for checking repeat digits so
 * it cannot store a number with more digits than the base it is
 * given in. For large bases a very large array will be generated.
 *
 * @author Matthew Jennings
 */
public class BasedNumber {

    int[] number;
    int base;

    /**
     * Constructor for class, takes a given number and base and
     * creates and fills an array of digits to store it.
     *
     * @param inputnumber int, decimal value of the number to store
     * @param base        int, desired base for storage.
     */
    public BasedNumber(int inputnumber, int base) {
        this.base = base;
        this.number = new int[base + 1];
        int rem;
        for (int i = 0; i <= base; i++) {
            while (inputnumber >= Math.pow(base, (base - i))) {
                if (base - i > 0) {
                    rem = inputnumber % (int) Math.pow(base, (base - i));
                    inputnumber = inputnumber - rem;
                    this.number[i] = inputnumber / (int) Math.pow(base, (base - i));
                    inputnumber = rem;
                } else {
                    this.number[i] = inputnumber;
                    break;
                }
            }
        }
        if (this.number[0] > base) {
            System.err.println("Number too large for this base with no repeat digits");
            System.exit(1);
        }
    }

    /**
     * Increments this number by 1.
     */
    public void addOne() {
        for (int i = this.number.length - 1; i >= 0; i--) {
            if (this.number[i] < base - 1) {
                this.number[i]++;
                for (int j = i + 1; j < this.number.length; j++) {
                    this.number[j] = 0;
                }
                return;
            }
        }
    }

    /**
     * Returns a String representation of the number.
     * Uses a decimal representation of the value of each digit
     * separated by | for ease of reading.
     *
     * @return String representation of number.
     */
    public String toString() {
        String result = "";
        int firstDigit = 0;
        while (this.number[firstDigit] == 0) {
            firstDigit++;
        }
        for (int i = firstDigit; i < this.number.length; i++) {
            result += this.number[i];
            result += "|";
        }
        return result;
    }

    /**
     * Checks this number to see if there are any repeated digits.
     *
     * @return boolean, true if number has repeated digits, otherwise false.
     */
    public boolean hasRepeats() {
        int firstDigit = 0;
        while (this.number[firstDigit] == 0) {
            firstDigit++;
        }
        for (int i = firstDigit; i < this.number.length; i++) {
            for (int j = i + 1; j < this.number.length; j++) {
                if (this.number[i] == this.number[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the max value that can exist in the base of this number
     * without any repeated digits.
     *
     * @return long max value with no repeats.
     */
    public long maxValue() {
        long result = 0;
        for (int i = 0; i < this.number.length - 1; i++) {
            result += (this.number.length - i - 2) * (Math.pow(this.base, (this.number.length - i - 2)));
        }
        return result;
    }

}
