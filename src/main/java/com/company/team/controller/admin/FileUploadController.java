package com.company.team.controller.admin;

import com.company.team.data.response.base.MyResponse;
import com.company.team.service.implement.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    FilesStorageService storageService;

    @PostMapping("/admin/auth/v1/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        String fileSave = storageService.save(file);

        String pathOnServer = "http://localhost:8099/api/v1/public/files/";
        String path =  pathOnServer + fileSave;

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("url", path);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("upload thanh cong")
                .buildData(responseObj)
                .get();

        return ResponseEntity.ok(response);

    }

    /**
     *
     * làm cho vui thôi chứ đúng ra sẽ mở access nginx chẳng hạn
    * */

    @GetMapping("/v1/public/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
