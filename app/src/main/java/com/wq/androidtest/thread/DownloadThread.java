package com.wq.androidtest.thread;

import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by wangqi on 15/10/22.
 */
public class DownloadThread extends Thread {

    long start;
    long end;
    InputStream inputStream;
    RandomAccessFile file;
    static int BUFF_LEN = 1024;

    public DownloadThread(long start, long end, InputStream inputStream, RandomAccessFile file) {
        this.start = start;
        this.end = end;
        this.inputStream = inputStream;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            inputStream.skip(start);
            file.seek(start);
            byte[] buff = new byte[BUFF_LEN];
            long contentLen = end - start;
            long times = contentLen / BUFF_LEN;
            if (contentLen % BUFF_LEN != 0) {
                times++;
            }

            int hasRead;
            for (int i = 0; i < times; i++) {
                hasRead = inputStream.read(buff);
                if (hasRead <= 0) {
                    break;
                }
                file.write(buff, 0, hasRead);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (file != null)
                    file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
