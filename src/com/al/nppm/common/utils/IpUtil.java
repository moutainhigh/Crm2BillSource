package com.al.nppm.common.utils;

import org.apache.log4j.Logger;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Set;

public class IpUtil {
    private static org.apache.log4j.Logger logger = Logger.getLogger(IpUtil.class);
    public static String getLocalIPForJava(){
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("获取服务器ip失败："+e.getMessage());
        }
        return null;
    }
    /**
     * @return
     * @throws MalformedObjectNameException
     * 获取当前机器的端口号
     */
    public static String getLocalPort()  {
        String port=null;
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            port = objectNames.iterator().next().getKeyProperty("port");
        }catch (MalformedObjectNameException e){
            logger.error("获取服务器端口失败："+e.getMessage());
        }
        return port;
    }



    public static void main(String[] args) {
        System.out.println(IpUtil.getLocalIPForJava());
    }
}
