package api.freelive.board.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreFilePort {

    String storePostFile(MultipartFile file, String fileName);

    void deletePostFiles(List<String> fileNames);

}
