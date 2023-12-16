package api.freelive.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostWriteReqDto {

    private String title;

    private String content;

    private MultipartFile file;

}
