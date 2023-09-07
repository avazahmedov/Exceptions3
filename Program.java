import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IncorrectInfoException, IOException {
        String[] arrInfo = userRequest();
        lengthCheck(arrInfo);
        parseArray(arrInfo);
        File file = saveToFile(arrInfo);
        readFromFile(file);
    }

    public static String[] userRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter last name, first name, middle name and phone number through a space: ");
        String info = sc.nextLine();
        String[] arrInfo = info.split(" ");
        return arrInfo;
    }

    public static void lengthCheck(String[] arr) throws IncorrectInfoException {
        if (arr.length != 4) {
            throw new IncorrectInfoException("Incorrect count of info - " + arr.length + ", must be 4");
        }
    }

    public static void parseArray(String[] arr) {
        try {
            String lastName = arr[0];
            String firstName = arr[1];
            String middleName = arr[2];
            int a = Integer.parseInt(arr[3]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format of info, must be 'string, string, string, number'");
        }
    }

    public static void saveToFile(String lastN, String firstN, String middleN, int phone) {

        try (FileWriter fw = new FileWriter(lastN, true)) {
            fw.write(lastN);
            fw.write(firstN);
            fw.write(middleN);
            fw.write(phone);
        } catch (IOException e) {
            System.out.println("Error while saving" + Arrays.toString(e.getStackTrace()));
        }
    }

    public static File saveToFile(String[] arr) {
        String lastName = null;
        String firstName = null;
        String middleName = null;
        int a = 0;
        try {
            lastName = arr[0];
            firstName = arr[1];
            middleName = arr[2];
            a = Integer.parseInt(arr[3]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format of info, must be 'string, string, string, number'");
        }
        File file = new File(lastName);
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(lastName + " ");
            fw.write(firstName + " ");
            fw.write(middleName + " ");
            fw.write(Integer.toString(a));
            fw.write("\n");
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error while saving");
        }
        return file;
    }

    public static void readFromFile(File file) {
        try (FileReader fr = new FileReader(file)) {
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading");
        }
    }
}

class IncorrectInfoException extends Exception {
    public IncorrectInfoException(String message) {
        super(message);
    }
}