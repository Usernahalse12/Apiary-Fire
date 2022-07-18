package com.wonderful.apiary.Network;

public class Regular {


    /**
     * 取两个文本之间的文本值
     * @param text 源文本 比如：欲取全文本为 12345
     * @param left 文本前面
     * @param right  后面文本
     * @return 返回 String
     *
     * 来源：<a href="https://blog.csdn.net/qq_33865142/article/details/86773507">https://blog.csdn.net/qq_33865142/article/details/86773507</a>
     */
    public String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
