import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DNSLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an IP address or a domain name:");
        String input = scanner.nextLine();
        if (isIpAddress(input)) {
            try {
                InetAddress address = InetAddress.getByName(input);
                System.out.println("Hostname for IP address " + input + " is: " + address.getHostName());
            } catch (UnknownHostException e) {
                System.out.println("Cannot resolve hostname for IP address: " + input);
            }
        } else {
            try {
                InetAddress[] addresses = InetAddress.getAllByName(input);
                for (InetAddress address : addresses) {
                    System.out.println("IP address for hostname " + input + " is: " + address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("Cannot resolve IP address for hostname: " + input);
            }
        }
    }

    private static boolean isIpAddress(String input) {
        String[] parts = input.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}