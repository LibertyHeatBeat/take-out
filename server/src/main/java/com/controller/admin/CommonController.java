package com.controller.admin;

import com.constant.MessageConstant;
import com.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController("adminCommonController")
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Value("${spring.files.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    @ApiOperation("上传菜品图片")
    public Result<String> imageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("文件上传本地：{}",file);
        try{
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;
            Path upDir = Paths.get(uploadDir);
            // 确保目录存在
            if (!Files.exists(upDir)) {
                Files.createDirectories(upDir);
            }
            // 定义文件保存完整路径
            Path filePath = upDir.resolve(objectName);
            // 将文件从MultipartFile转换为File
            File serverFile = new File(filePath.toAbsolutePath().toString());
            // 将上传的文件写入到服务器的文件系统中
            file.transferTo(serverFile);
            // 构造HTTP访问路径
            String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + "/static/image/" + objectName;
            log.info("文件上传成功，访问路径为：{}", fileUrl);
            return Result.success(fileUrl);
        }catch (Exception e){
            log.info("文件上传：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }








}
