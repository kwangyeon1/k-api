package api.freelive.board.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface StoreFilePort {

    String store(MultipartFile file);

}
