package api.freelive.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class PostWriteReqDto {

    private String title;

    private String content;

    private MultipartFile file = null;

    private List<Long> removeFileIds;

    private String p_id; // p_id는 postNum이랑 같다

}
