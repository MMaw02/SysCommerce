package pe.mmaw.projectventafactura.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UpdateFile {
    private final String FOLDER = "images//";
    private final String IMG_DEFAULT = "default.jpg";

    public String update(MultipartFile multipartFile) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        if (!multipartFile.isEmpty()) {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(FOLDER + uniqueFileName).toAbsolutePath();
            Files.write(path, bytes);
            return uniqueFileName;
        }

        return IMG_DEFAULT;
    }

    public void delete(String nameFile) {
        File file = new File(FOLDER + nameFile);
        file.delete();
    }
}
