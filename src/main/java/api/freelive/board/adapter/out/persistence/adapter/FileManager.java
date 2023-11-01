package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.application.port.out.StoreFilePort;
import api.freelive.util.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileManager implements StoreFilePort {

    @Value("${path.file.post}")
    private Path rootLocation;

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("파일이 없습니다.");
            }
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException(
                        "현재 경로에 파일을 저장할 수 없습니다.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return destinationFile.toAbsolutePath().toString();
        }
        catch (IOException e) {
            throw new StorageException("파일 저장 실패.", e);
        }
    }
}
