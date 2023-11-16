import java.util.Scanner;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
*
* @author smadzar
*/

public class CIDRInfoExtractor {
    
    public static Scanner scan = new Scanner(System.in);
    public static int prefixLength;
    public static String address;

    //validates CIDR (IPv4 format) address
    public static boolean validateCIDRAddress(String ipAddress) {

        String[] split = ipAddress.split("/");
        if(split.length != 2) {
            return false;
        }
        address = split[0];

        try {
            prefixLength = Integer.parseInt(split[1]);
        }
        catch(Exception e) {
            return false;
        }

        InetAddressValidator validator = InetAddressValidator.getInstance();
        
        if(validator.isValidInet4Address(address) && (prefixLength >= 0 && prefixLength <= 32)) {
            return true;
        }
            
        return false;
    }

    public static String ipAddressToBinaryIpAddress() {

        StringBuilder binaryIpAddress = new StringBuilder();
        String[] split = address.split("\\.");

        for(int i = 0; i < split.length; i++) {
            String binaryOctet = Integer.toBinaryString(Integer.parseInt(split[i]));

            while(binaryOctet.length() < 8) {
                binaryOctet = "0" + binaryOctet;
            }

            binaryIpAddress.append(binaryOctet);
            if(i != 3) {
                binaryIpAddress.append(".");
            }
        }
        return binaryIpAddress.toString();
    }

    public static String binaryToDecimalAddress(String binaryAddress) {
        
        String[] split = binaryAddress.toString().split("\\.");
        StringBuilder decimalAddress = new StringBuilder();
        
        for(int i = 0; i < split.length; i++) {
            decimalAddress.append(Integer.parseInt(split[i],2));

            if(i != 3) {
                decimalAddress.append(".");
            }
        }
        return decimalAddress.toString();
    }
    
    public static String findNetworkAndBroadcastAddress(String binaryIpAddress, boolean decision) {

        //'0' for network address 
        //'1' for broadcast address
        char bitToChange = decision ? '0' : '1';
        int count = 0;

        StringBuilder networkBinaryAddress = new StringBuilder(binaryIpAddress);

        for(int i = 0; i < binaryIpAddress.length(); i++) {
            if(Character.isDigit(binaryIpAddress.charAt(i))) {
                if(count >= prefixLength) {
                    networkBinaryAddress.setCharAt(i, bitToChange);
                }
                count++;
            }
        }

        return binaryToDecimalAddress(networkBinaryAddress.toString());
    }

    public static String findNetworkMask() {
        StringBuilder networkMask = new StringBuilder();
        
        int count = prefixLength;

        for(int i = 0; i < 4; i++) {
            StringBuilder octet = new StringBuilder();
            for(int x = 0; x < 8; x++) {
                if(count > 0) {
                    octet.append("1");
                }
                else {
                    octet.append("0");
                }
                count--;
            }

            if(i != 3) {
                octet.append(".");
            }
            networkMask.append(octet);
        }
        
        return binaryToDecimalAddress(networkMask.toString());
    }

    public static long calculateNumberOfIPAddresses() {
        return (long) Math.pow(2, 32 - prefixLength);
    }
    
    
    public static void main(String[] args) throws Exception {

        while(true) {
             System.out.print("\nEnter a CIDR(IPv4) slash notation (e.g., 5.5.5.5/10) of your network: ");
             String cidrIPv4Address = scan.nextLine();

             if(validateCIDRAddress(cidrIPv4Address)) {
                System.out.println("\nThe number of addresses: " + calculateNumberOfIPAddresses());
                String binaryIPAddress = ipAddressToBinaryIpAddress();
                System.out.println("The network address is: " + findNetworkAndBroadcastAddress(binaryIPAddress, true));
                System.out.println("The broadcast address is: " + findNetworkAndBroadcastAddress(binaryIPAddress, false));
                System.out.println("The network mask with prefix /" + prefixLength + " is: " + findNetworkMask() + "\n");

                System.out.print("Do you want to try again [y/n]? ");

                String ans = "";

                while(true) {
                    ans = scan.nextLine();
                    if(ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("n")) {
                        break;
                    }
                }
                
                if(ans.equalsIgnoreCase("n")) {
                    break;
                }
             }
             else {
                System.out.println("\nInvalid CIDR address entered. Try again!");
             }
        }
        System.out.println("\n*** Program executed ***\n");
    }
}
