package org.justmine.spring.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPUtils {
    private static volatile String cachedLocalIp;

    /**
     * Convert an IP address to a number
     *
     * @param ipAddress the IP address to convert
     * @return the IP address as a number
     */
    public static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }


    /**
     * Convert a number to an IP address
     *
     * @param ip the number to convert
     * @return the number as an IP address
     */
    public static String longToIp(long ip) {
        StringBuilder result = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            result.insert(0, Long.toString(ip & 0xff));
            if (i < 3) {
                result.insert(0, '.');
            }
            ip = ip >> 8;
        }
        return result.toString();
    }

    /**
     * resolve local machine ip address
     * get all local matchine ip4 address check one by one
     * if any ip4 with 10. then return 10
     * if any ip4 with 192. then return 192
     * if any ip4 with 172. then return 172
     * else return 127
     */
    public static String getIpAddress() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        String ipAddress = "127.0.0.1";
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (!address.isLinkLocalAddress() && !address.isLoopbackAddress() && address instanceof java.net.Inet4Address) {
                    String currentIpAddress = address.getHostAddress();
                    if (currentIpAddress.startsWith("10.")) {
                        return currentIpAddress;
                    } else if (currentIpAddress.startsWith("172.") && ipAddress.equals("127.0.0.1")) {
                        ipAddress = currentIpAddress;
                    } else if (currentIpAddress.startsWith("192.") && ipAddress.equals("127.0.0.1")) {
                        ipAddress = currentIpAddress;
                    }
                }
            }
        }
        return ipAddress;
    }

    public static List<String> getAllIpList() throws SocketException {
        List<String> ipAddresses = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (!address.isLinkLocalAddress() && !address.isLoopbackAddress() && address instanceof java.net.Inet4Address) {
                    ipAddresses.add(address.getHostAddress());
                }
            }
        }
        return ipAddresses;
    }

    public static String getLocalIp() throws SocketException {
        if (cachedLocalIp != null) {
            return cachedLocalIp;
        }
        synchronized (IPUtils.class) {
            if (cachedLocalIp != null) {
                return cachedLocalIp;
            }
            cachedLocalIp = getPreferredIp(getAllIpList());
            return cachedLocalIp;
        }
    }

    public static String getPreferredIp(List<String> allIpList) {
        String ip = allIpList.stream().filter(ip1 -> ip1.startsWith("10.")).findFirst()
                .orElse(allIpList.stream().filter(ip2 -> ip2.startsWith("172.")).findFirst()
                        .orElse(allIpList.stream().filter(ip3 -> ip3.startsWith("192.")).findFirst()
                                .orElse("127.0.0.1")));
        return ip;
    }

}
