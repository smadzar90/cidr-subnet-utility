## CIDR-Subnet-Utility

### About

CIDR and subnets are fundamental tools in networking that provide more efficient IP address allocation, network segmentation, improved routing, enhanced security, and better network performance. These benefits are very important for efficient menaging and scaling modern computer networks. The program accepts the CIDR slash notation with the IPv4 address as an input. If the address is valid, it provides the user with the information such as number of addresses, network address, broadcast address, and network mask.

### Skills Used

- Java
- Networking
- Logic and algorithms
- String manipulation
- Input validation

### Example

```
Enter a CIDR(IPv4) slash notation (e.g., 5.5.5.5/10) of your network: 1.1.1.1/23

The number of addresses: 512
The network address is: 1.1.0.0
The broadcast address is: 1.1.1.255
The network mask with prefix /23 is: 255.255.254.0

Do you want to try again [y/n]? y

Enter a CIDR(IPv4) slash notation (e.g., 5.5.5.5/10) of your network: 2.2.2.2/12

The number of addresses: 1048576
The network address is: 2.0.0.0
The broadcast address is: 2.15.255.255
The network mask with prefix /12 is: 255.240.0.0

Do you want to try again [y/n]? y

Enter a CIDR(IPv4) slash notation (e.g., 5.5.5.5/10) of your network: 1233.2.2.3/23

Invalid CIDR address entered. Try again!

Enter a CIDR(IPv4) slash notation (e.g., 5.5.5.5/10) of your network: 123.2.56.2/27

The number of addresses: 32
The network address is: 123.2.56.0
The broadcast address is: 123.2.56.31
The network mask with prefix /27 is: 255.255.255.224

Do you want to try again [y/n]? n

*** Program executed ***
```

### How to Run

1. cd path_to_cidr-subnet-utility
2. javac -cp lib/commons-validator-1.7.jar src/CIDRInfoExtractor.java
3. java -cp lib/commons-validator-1.7.jar:src CIDRInfoExtractor

### Author 

- Stipan Madzar
