package com.wonderful.apiary.Network;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class ClientGUI extends JFrame {

    private JTextField enter;
    private JTextArea display;
    private static final JLabel text = new JLabel("TeaCon，嘿嘿，我的TeaCon"); //要显示的文字

    public ClientGUI() throws IOException {
        super("乱按右键的后果");
        //设置背景图
        // 背景图片(网络图片) 图床：https://imgloc.com/image/NziCs
        URL url = new URL("https://s1.328888.xyz/2022/07/17/NziCs.md.png");
        //http读文件
        BufferedImage image = ImageIO.read(url);

        //方法A
        BufferedImage newImage = reSize(image, 451, 549, true);
        ImageIcon icon = new ImageIcon(newImage);

        //调整图片大小为 690X840尺寸  方法B
        //BufferedImage newImage = resizeImage(image,451,549);
        //ImageIcon icon = new ImageIcon(newImage); //换为ImageIcon类型


        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板

        //获取顶层容器设为透明
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        //建立透明文本显示区面板
        JPanel displayPanel=new JPanel();
        displayPanel.setOpaque(false);


        //建立透明的JTextArea以显示输出内容
        //display = new JTextArea(10,10);
        //display.setText("Connnect Attmpting...");
        //display.setOpaque(false);
        //JTextArea加入文本显示面板
        //displayPanel.add(display);



        //Dimension size = text.getPreferredSize();
        //text.setBounds(150, 100, size.width, size.height);
        //文字设置：
        text.setFont(new Font("微软雅黑", Font.BOLD, 25));
        text.setForeground(Color.CYAN);
        displayPanel.add(text);
        //显示位置：来源：https://www.codenong.com/how-to-set-location-of-jlabel-in-a-jframe-with-java/
        displayPanel.setBorder(BorderFactory.createEmptyBorder(433, 10, 0, 0));


        //文本显示面板加至顶层容器
        imPanel.add(displayPanel, BorderLayout.WEST);

/**
        //设置透明文本输入区面板
        JPanel enterPanel=new JPanel();
        enterPanel.setOpaque(false);
        //文本输入标签
        JLabel enterLab = new JLabel("ClientGUI Input:");
        //设置透明可写的文本输入框
        enter = new JTextField(20);
        enter.setOpaque(false);

        enter.setEnabled(true);
        enter.addActionListener(
                new ActionListener() {
                    public void actionPerformed( ActionEvent e )
                    {//添加事件监听动作
                    }
                }
        );
        enterPanel.add(enterLab);
        enterPanel.add(enter);
        //文本输入面板加至顶层容器
        imPanel.add(enterPanel,BorderLayout.SOUTH);
 */

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //do something
                ClientGUI.text.setText("TeaCon，嘿嘿，我的TeaCon");
                JOptionPane.showConfirmDialog(ClientGUI.this, "就你也想关掉我？", "你个憨批", JOptionPane.YES_NO_OPTION);
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }});



        //设置窗口为图片大小且不可调整 高度偏移+39
        setSize(icon.getIconWidth(),icon.getIconHeight() + 39);
        setResizable(false);

        //设置位置 来源：https://blog.csdn.net/u012151974/article/details/22735365
        int windowWidth = this.getWidth(); //获得窗口宽
        int windowHeight = this.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //获取整个屏幕的宽和高
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        this.setLocation(screenWidth /2 - windowWidth /2, screenHeight /2 - windowHeight /2);//设置窗口居中显示

        //不能最小化 来源：https://blog.csdn.net/sky15737/article/details/50850315
        //this.setUndecorated(true); // 去掉窗口的装饰
        //this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG); //采用指定的窗口装饰风格

        //将窗口置顶
        this.setAlwaysOnTop(true);
        //设置窗口可见
        setVisible(true);

        //监视状态，防止用户进行最小化操作    来源：https://blog.51cto.com/u_15127702/4202962
        addWindowStateListener(new WindowStateListener() {
            //事件：当窗体发生改变时
            @Override
            public void windowStateChanged(WindowEvent e) {
                // TODO Auto-generated method stub
                int newState = e.getNewState();
                System.out.println(newState);

                //最小化状态码
                if(newState == 1)
                {
                    //打回原形(还原窗口)
                    ClientGUI.this.setExtendedState(JFrame.NORMAL);
                    ClientGUI.text.setText("你碍我，我碍你，你学编程，天灭你");
                    JOptionPane.showConfirmDialog(ClientGUI.this, "想最小化？门都没有！", "你个憨批", JOptionPane.YES_NO_OPTION);
                }
            }
        });


    }

/*
    public static void main(String[] args) throws IOException {
        ClientGUI app = new ClientGUI();
        app.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing( WindowEvent e )
                    {
                        System.exit( 0 );
                    }
                }
        );
    }
 */



    //来源： https://blog.csdn.net/qq_33697094/article/details/114327979
    /**
     * 通过BufferedImage图片流调整图片大小
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }


    //原文链接：https://blog.csdn.net/servermanage/article/details/103470214
    /**
     * @param srcImage   原图片
     * @param width      期望宽
     * @param height     期望高
     * @param equalScale 是否等比例缩放
     * @return target    处理好的图片
     */
    public static BufferedImage reSize(BufferedImage srcImage, int width,
                                       int height, boolean equalScale) {
        //String type = getImageType(srcImg);
        //if (type == null) {
            //return;
        //}

        if (width < 0 || height < 0) { //防负数大小
            return srcImage;
        }

        //BufferedImage srcImage = null;
        //srcImage = ImageIO.read(srcImg);
        System.out.println("srcImg size=" + srcImage.getWidth() + "X" + srcImage.getHeight());
        if (srcImage != null) {
            // targetW，targetH分别表示目标长和宽
            BufferedImage target = null;
            double sx = (double) width / srcImage.getWidth();
            double sy = (double) height / srcImage.getHeight();
            // 等比缩放
            if (equalScale) {
                if (sx > sy) {
                    sx = sy;
                    width = (int) (sx * srcImage.getWidth());
                } else {
                    sy = sx;
                    height = (int) (sy * srcImage.getHeight());
                }
            }
            System.out.println("destImg size=" + width + "X" + height);
            ColorModel cm = srcImage.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();

            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
            Graphics2D g = target.createGraphics();
            // smoother than exlax:
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.drawRenderedImage(srcImage, AffineTransform.getScaleInstance(sx, sy));
            g.dispose();

            // 处理好的图片：target
            return target;

        }
        return srcImage;
    }
}
