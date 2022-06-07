package com.company.team.service.implement;

import com.company.team.service.IFilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesStorageService implements IFilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {

    if (Files.exists(root)) {
      return;
    }

    try {
      Files.createDirectory(root);
      System.out.println("create dir successfully");
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public String save(MultipartFile file) {

    UUID uuid = UUID.randomUUID();
    String name ="";
    try {
      String fileUploadName = file.getOriginalFilename();
      assert fileUploadName != null;
      name = fileUploadName.split("\\.")[0] + uuid +"."+ fileUploadName.split("\\.")[1];
      Files.copy(file.getInputStream(), this.root.resolve(name));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }

    return name;
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

}
