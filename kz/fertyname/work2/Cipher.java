package kz.fertyname.work2;

import kz.fertyname.util.Util;

public class Cipher {
    private enum Operation {
        ENCODE, DECODE
    }

    private static String encrypt(String input, int SHIFT_KEY) {
        char[] chars = input.toCharArray();
        StringBuilder encoded = new StringBuilder();
        for (char c : chars) {
            encoded.append(processCharacter(c, SHIFT_KEY, Operation.ENCODE));
        }
        return encoded.toString();
    }

    private static String decrypt(String input, int SHIFT_KEY) {
        char[] chars = input.toCharArray();
        StringBuilder decoded = new StringBuilder();

        for (char c : chars) {
            decoded.append(processCharacter(c, SHIFT_KEY, Operation.DECODE));
        }

        return decoded.toString();
    }

    private static char processCharacter(char c, int shift, Operation operation) {
        if (Character.isLetter(c)) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC) {
                if (Character.isUpperCase(c)) {
                    return shiftCharacter(c, shift, operation, 'А', 32);
                } else {
                    return shiftCharacter(c, shift, operation, 'а', 32);
                }
            } else {
                if (Character.isUpperCase(c)) {
                    return shiftCharacter(c, shift, operation, 'A', 26); 
                } else {
                    return shiftCharacter(c, shift, operation, 'a', 26);
                }
            }
        }
        return c; 
    }

    private static char shiftCharacter(char c, int shift, Operation operation, int alphabetStart, int alphabetSize) {
        if (operation == Operation.DECODE) {
            shift = -shift;
        }
        return (char) ((c - alphabetStart + shift + alphabetSize) % alphabetSize + alphabetStart);
    }

    public static void manager(String data, int SHIFT_KEY) throws Exception {
        String originalText = data;

        String encoded = encrypt(originalText, SHIFT_KEY);
        Util.m_Print("info", "Ответ шифрования: " + encoded, 20);

        String decoded = decrypt(encoded, SHIFT_KEY);
        Util.m_Print("info", "Ответ расшифрования: " + decoded, 20);
    }
}
