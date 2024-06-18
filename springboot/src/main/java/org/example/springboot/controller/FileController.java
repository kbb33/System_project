package org.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import org.example.springboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/**
 * 功能：提供文件传输功能
 * 日期：2024/5/6 下午2:49
 * 问题：
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${ip:loaclhost}")
    String ip;

    @Value("${server.port}")
    String port;

    // @AuthAccess 添加放行

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";//定义一个根目录 比如C:\Users\20363\Desktop\工程制图作业批语系统\files

    //这个是上传接口
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();//文件的原始名称 aaa.png
        String mainName = FileUtil.mainName(originalFilename);//aaa
        String extName = FileUtil.extName(originalFilename);//png
        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH);//如果不存在父级目录就创建
        }
        if (FileUtil.exist(ROOT_PATH + File.separator + originalFilename)) {//如果上传的文件已经存在已存在，要重命名文件,ROOT_PATH + File.separator + originalFilename为上传文件路径即视为上传文件
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;
        }//File.separator为分隔符
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);//C:\Users\20363\Desktop\工程制图作业批语系统\files\aaa.png
        file.transferTo(saveFile);//存文件到本地磁盘文件里面
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        return Result.success(url);//返回文件的连接，这个连接时文件的下载地址
    }

    //这个是下载请求接口
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {

        String filePath = ROOT_PATH + File.separator + filename;
        if (!FileUtil.exist(filePath)) {//如果这个文件不存在
            return;
        }
        byte[] bytes=FileUtil.readBytes(filePath);//读文件
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);//数组是一个字节数组，也就是文件的字节数据流
        outputStream.flush();
        outputStream.close();
    }
}