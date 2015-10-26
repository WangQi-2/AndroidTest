package com.example.thread;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MutilThreadDownloader {
    static final int THREAD_COUNT = 4;
    static final String SAVE_FILE_NAME = "down.apk";
    static final String urlstr = "http://jenkins/job/tvmarket_manual/lastSuccessfulBuild/artifact/artifact/tvmarket-130003-2.5.1-2005001-20151022-0085.apk";

    public void run() {
        try {
            long filesize = getFileLength(urlstr);
            createEmptyFile(SAVE_FILE_NAME, filesize);
            System.out.println("网络资源的大小" + filesize);
            long blockSize = filesize / THREAD_COUNT;
            for (int i = 0; i < THREAD_COUNT; i++) {
                long startIndex = i * blockSize;
                long endIndex = (i + 1) * blockSize - 1;
                if (i == THREAD_COUNT - 1) {
                    endIndex = filesize;
                }
                new DownLoadThread(urlstr, i, startIndex, endIndex).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static long getFileLength(String urlstr) throws Exception {
        URL url = new URL(urlstr);
        long length;
        URLConnection con = url.openConnection();
        long size = con.getContentLength();
        length = size;
        return length;
    }

    public static void createEmptyFile(String filename, long filesize) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filename, "rw");
            raf.setLength(filesize);
        } catch (Exception e) {
            try {
                if (raf != null)
                    raf.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static class DownLoadThread extends Thread {
        private String path;
        private long threadId;
        private long startIndex;
        private long endIndex;

        public DownLoadThread(String path, long threadId, long startIndex, long endIndex) {
            super();
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                int code = conn.getResponseCode();
                System.out.println("code:" + code);
                InputStream is = conn.getInputStream();
                RandomAccessFile raf = new RandomAccessFile(SAVE_FILE_NAME, "rwd");
                raf.seek(startIndex);

                int size = 0;
                int len;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                    size += len;
                }
                is.close();
                raf.close();
                System.out.println("线程：" + threadId + "下载完毕 ,download size: " + size);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

