package com.tianjie.study.bissafe;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class IOUtil {
    /**
     * @Author Nxy
     * @Date 2020/3/21 20:21
     * @Param in:输入流，length:读取字节数
     * @Return
     * @Exception
     * @Description 从输入流读取指定长度字节的数据
     */
    public static byte[] readBytesFromInputStream(BufferedInputStream in,
                                                  int length) throws IOException {
        int readSize;
        byte[] bytes = null;
        bytes = new byte[length];
        long length_tmp = length;
        long index = 0;// start from zero
        while ((readSize = in.read(bytes, (int) index, (int) length_tmp)) != -1) {
            length_tmp -= readSize;
            if (length_tmp == 0) {
                break;
            }
            index = index + readSize;
        }
        return bytes;
    }
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
