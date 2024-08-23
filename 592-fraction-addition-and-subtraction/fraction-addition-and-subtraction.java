import java.math.BigInteger;

class Solution {
    public String fractionAddition(String expression) {
        BigInteger num = BigInteger.ZERO;
        BigInteger den = BigInteger.ONE;
        int i = 0;
        int n = expression.length();
        
        while (i < n) {
            int sign = 1;
            
            // Determine the sign of the current fraction
            if (expression.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (expression.charAt(i) == '+') {
                i++;
            }
            
            // Extract the numerator
            int j = i;
            while (j < n && Character.isDigit(expression.charAt(j))) {
                j++;
            }
            BigInteger numerator = new BigInteger(expression.substring(i, j)).multiply(BigInteger.valueOf(sign));
            
            // Move past the '/' character
            i = j + 1;
            
            // Extract the denominator
            j = i;
            while (j < n && Character.isDigit(expression.charAt(j))) {
                j++;
            }
            BigInteger denominator = new BigInteger(expression.substring(i, j));
            
            // Calculate the new denominator (LCM of current and new denominator)
            BigInteger commonDen = den.multiply(denominator).divide(den.gcd(denominator));
            
            // Adjust the numerators to the new common denominator and add them
            num = num.multiply(commonDen.divide(den)).add(numerator.multiply(commonDen.divide(denominator)));
            den = commonDen;
            
            // Move to the next part of the expression
            i = j;
        }
        
        // Simplify the fraction
        BigInteger gcd = num.gcd(den);
        num = num.divide(gcd);
        den = den.divide(gcd);
        
        return num.toString() + "/" + den.toString();
    }
}
