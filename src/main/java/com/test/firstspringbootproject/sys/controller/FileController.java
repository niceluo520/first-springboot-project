package com.test.firstspringbootproject.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "文件上传")
@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    //单文件上传
    @ApiOperation("单文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        try {
            if(file.isEmpty()){
                return "文件不能为空";
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("文件名："+fileName+"后缀名："+suffixName);
            String filePath = "E:\\copy\\";
            String path = filePath + fileName;//完整路径名
            File f = new File(path);
            if (!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            file.transferTo(f);//文件写入
            /*FileOutputStream fop = new FileOutputStream(f);
            BufferedOutputStream bop = new BufferedOutputStream(fop);
            bop.write(file.getBytes());*/
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //多文件上传
    @ApiOperation("多文件上传")
    @ResponseBody
    @RequestMapping(value = "/batch",method = RequestMethod.POST)
    public String batch(HttpServletRequest request){
        //把上传的文件放入list集合中去
        List<MultipartFile> list = ((MultipartHttpServletRequest)request).getFiles("file");
        //缓冲流
        BufferedOutputStream outputStream = null;
        //创建文件输出流
        FileOutputStream fileStream = null;
        for(MultipartFile file:list){
            String filePath = "E:\\copy\\";
            if(!file.isEmpty()){
                try {
                    File f = new File(filePath + file.getOriginalFilename());
                    fileStream = new FileOutputStream(f);
                    outputStream = new BufferedOutputStream(fileStream);
                    byte[] bytes = file.getBytes();
                    //写入文件
                    outputStream.write(bytes);
                    fileStream.close();
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                    return "多文件上传失败";
                }
            }
        }
        return "多文件上传成功";
    }

    //文件下载
    @ApiOperation("文件下载")
    @ResponseBody
    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public String downloadFile(HttpServletRequest request, HttpServletResponse response)throws IOException{
        //读取文件名
        String fileName = request.getParameter("fileName");
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        // 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(realPath+"\\"+fileName);
        if(!file.exists()){
            request.setAttribute("message","文件不存在或已被删除");
        }
        //处理文件名
        String realName = fileName.substring(fileName.indexOf("_")+1);

        // 设置HTTP响应头
        response.setContentType("application/x-download");// 告知浏览器下载文件，而不是直接打开，浏览器默认为打开
        //处理响应头
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(realName,"UTF-8"));
        // 读取要下载的文件，保存到文件输入流
        FileInputStream inputStream = new FileInputStream(realPath+"\\"+fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte[] buff = new byte[1024];
        int len = 0;
        //循坏将输入流的内容读取到缓冲区
        if((len = inputStream.read(buff)) != -1){
            // 输出缓冲区的内容到浏览器，实现文件下载
            out.write(buff,0,len);
        }
        out.close();
        inputStream.close();
        return "下载成功";
    }

}
