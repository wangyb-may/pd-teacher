package com.bysj.wyb.teacher.result;

import com.bysj.wyb.teacher.vo.HomeworkStatusVo;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ZipUtil {
    //将网络url资源文件的多个文件打包成压缩包并输出流下载

    public void download2(List<HomeworkStatusVo> homeworkStatusVoList, HttpServletResponse response) throws Exception {

        String outputFileName = "作业" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
        // 设置response参数
        response.reset();
        response.setContentType("content-type:octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((outputFileName).getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();

        ZipArchiveOutputStream zous = new ZipArchiveOutputStream(out);
        zous.setUseZip64(Zip64Mode.AsNeeded);



        for (HomeworkStatusVo path : homeworkStatusVoList) {
            String fileName = UUID.randomUUID() + ".png";
            InputStream inputStream = getInputStreamFromUrl(path.getUrl());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            if (baos != null) {
                baos.flush();
            }
            byte[] bytes = baos.toByteArray();

            //设置文件名
            ArchiveEntry entry = new ZipArchiveEntry(fileName);
            zous.putArchiveEntry(entry);
            zous.write(bytes);
            zous.closeArchiveEntry();
            if (baos != null) {
                baos.close();
            }
        }
        if (zous != null) {
            zous.close();
        }
    }

    /**
     * 通过网络地址获取文件InputStream
     *
     * @param path 地址
     * @return
     */
    public static InputStream getInputStreamFromUrl(String path) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}

