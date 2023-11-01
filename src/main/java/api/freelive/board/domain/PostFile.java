package api.freelive.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostFile {

    private Long postFileNum;

    private Long postNum;

    private String url;

    public PostFile(Long postNum, String absoluteFilePath) {
        this.postNum = postNum;
        this.url = absoluteFilePath;
    }
}