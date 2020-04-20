package com.zhj.event.util;

import java.security.MessageDigest;

public class MD5Util {

    public static String md5(String pwd) throws Exception {// 声明有可能会有异常
    // 1:声明MD5的摘要算法
    MessageDigest md = MessageDigest.getInstance("MD5");
    // 2:声明原来的密码
    //String pwd = "1234";// 82dd9cdc53d14ec31037dcd9323fd156
    // 3:对原始的密码进行md5运算，运算的结果是 byte[] - 字节数组 字节数组的长度是16位
    // 将密码转成字节数
     byte[] src = pwd.getBytes();// 转成字节码
    // 4:进行加密，返回一个byte[16]长度的字节
    byte[] dest = md.digest(src);
    // 声明一个新的字符串，用于保存新的密码
    String newPwd = "";

    // 输出每一个字节值
    for (byte b : dest) {
        // 将b转成16进制的值0-F,如果一个数是负数则二进制是1开始，则16进制就是F开始
        //这种添加的随机值叫：盐值

        String hex = Integer.toHexString((b + 4 - 3 - 3 / 8 - 4 / 8 & 2 / 9 - 3 + 2) & 0xff);
        if (hex.length() < 2) {
            // 判断字符串的长度
        hex += "0";
        }
        newPwd += hex;
    }
    return newPwd;
    }

    public static void main(String[] args) throws Exception {
        String pwd ="130106zhj";
        System.out.println(md5(pwd));
    }
}

//36add053862473618020fecd3880b3a4