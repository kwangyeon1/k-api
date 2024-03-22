package api.freelive.board.domain;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private Long postNum;

    private List<PostFileJpa> postFiles;

    private Long userNum;

    private Long guestHash;

    private String name;

    private String title;

    private String content;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;

    private Boolean isDel;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Post(Long userNum, String title, String content, List<PostFileJpa> postFiles) {
        this.userNum = userNum;
        this.title = title;
        this.content = content;
        this.postFiles = postFiles;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addPostFiles(List<PostFileJpa> postFiles) {
        this.postFiles.addAll(postFiles);
    }

    /**
     * 파일 번호들을 받아서 삭제하고 삭제된 파일의 이름들을 결과로 얻는다
     * @param postFileNums
     * @return List<String>
     */
    public List<String> removePostFiles(List<Long> postFileNums){
        List<String> fileNames = this.postFiles.stream()
                .filter(postFile -> postFileNums.contains(postFile.getPostFileNum()))
                .map(PostFileJpa::getName)
                .collect(Collectors.toList());

        for (Long postFileNum : postFileNums) {
            this.postFiles.forEach(postFile -> {
                if(postFile.getPostFileNum() == postFileNum) {
                    postFile.setPostJpa(null);
                }
            });
        }

        return fileNames;
    }

    private void setPostFiles(List<PostFileJpa> postFiles) {
        this.postFiles = postFiles;
    }

}
