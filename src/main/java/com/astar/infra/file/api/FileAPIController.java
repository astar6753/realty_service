package com.astar.infra.file.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController @RequestMapping("/api")
public class FileAPIController {
    @Value("${images.location.realty}") String directory_realty;
    @Value("${images.location.broker}") String directory_broker;
    @Value("${images.location.user}") String directory_user;
    @Value("${images.location.office}") String directory_office;
    @Value("${images.location.building}") String directory_building;

    @PostMapping("/images/upload/{type}")
    public Map<String,Object> postImageFile(
        @PathVariable String type,
        @RequestPart MultipartFile[] img_files
        ) {
            Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
            
            Path folderLocation = null;
            if(type.equals("realty")){ folderLocation=Paths.get(directory_realty); }
            else if(type.equals("broker")){ folderLocation=Paths.get(directory_broker); }
            else if(type.equals("user")){ folderLocation=Paths.get(directory_user); }
            else if(type.equals("office")){ folderLocation=Paths.get(directory_office); }
            else if(type.equals("building")){ folderLocation=Paths.get(directory_building); }
            else {
                resultMap.put("status",false);
                resultMap.put("message","type is invalid [realty,broker,user,office,building]");
                return resultMap;
            }

            List<Map<String,Object>> fileInfoList = new ArrayList<Map<String,Object>>();
            // 파일이름 조회후 순차 가공
            for(MultipartFile file : img_files) {
                String filename = file.getOriginalFilename();
                String[] split = filename.split("\\.");
                String ext = split[split.length-1];
                if(!ext.equalsIgnoreCase("jpg")&&!ext.equalsIgnoreCase("jpeg")&&!ext.equalsIgnoreCase("png")&&!ext.equalsIgnoreCase("gif")) {
                    resultMap.put("status",false);
                    resultMap.put("message","file extension is not allowed [jpg,jpeg,png,gif]");
                }
                switch(ext.toLowerCase()) {
                    case "jpg": case "jpeg": case "png": case "gif": 
                        
                        break;
                    default : 
                        resultMap.put("status",false);
                        resultMap.put("message","file extension is not allowed [jpg,jpeg,png,gif]");
                        return resultMap;
                }
                Calendar c = Calendar.getInstance();
                String saveFileName = StringUtils.cleanPath(filename+"_"+c.getTimeInMillis()+"."+ext);
                Path target = folderLocation.resolve(saveFileName);


                try {
                    Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    resultMap.put("status",false);
                    resultMap.put("message",e.getMessage());
                    return resultMap;
                }

                Map<String,Object> fileInfo = new LinkedHashMap<String,Object>();
                fileInfo.put("filename", saveFileName);
                fileInfo.put("ext", ext);
                fileInfoList.add(fileInfo);
            }

            resultMap.put("status",true);
            resultMap.put("message","파일 업로드");
            resultMap.put("fileList",fileInfoList);
            return resultMap;
        }
    
        @GetMapping("/images/{type}/{filename}")
        public ResponseEntity<Resource> getImage(
            @PathVariable String type, @PathVariable String filename, HttpServletRequest request
            ) {
                Path folderLocation = null;
                if(type.equals("realty")){ folderLocation=Paths.get(directory_realty); }
                else if(type.equals("broker")){ folderLocation=Paths.get(directory_broker); }
                else if(type.equals("user")){ folderLocation=Paths.get(directory_user); }
                else if(type.equals("office")){ folderLocation=Paths.get(directory_office); }
                else if(type.equals("building")){ folderLocation=Paths.get(directory_building); }
                else {return null;}

                Path filePath = folderLocation.resolve(filename);
                
                Resource r = null;
                try {
                    r = new UrlResource(filePath.toUri());
                } catch (IOException e) {
                    System.out.println("file not found");
                }

                String contentType = null;
                try {
                    contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
                    if(contentType==null) {
                        contentType = "application/octet-stream";
                    }
                } catch (IOException e) {
                    System.out.println("file not found");                    
                }
                return
                    ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+r.getFilename()+"\"")
                        .body(r);

            }
}

