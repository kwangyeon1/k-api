package api.freelive.board.adapter.in;

import api.freelive.board.adapter.in.response.ApiResponse;
import api.freelive.board.adapter.in.response.PostResponse;
import api.freelive.board.application.port.in.ReadPostUseCase;
import api.freelive.board.application.port.in.EditPostUseCase;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostWriteReqDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final ReadPostUseCase readPostUseCase;
    private final EditPostUseCase editPostUseCase;

    @GetMapping("/read")
    public ResponseEntity<?> readPosts(@RequestParam Map<String, String> param) {
        String page = param.get("page");
        Page<Post> postPage = readPostUseCase.readPosts(page);

        ApiResponse<Page<PostResponse>> response = new ApiResponse<>(
                postPage.map(post -> new PostResponse(post, false))
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{p_id}")
    public ResponseEntity<?> readPost(@PathVariable Long p_id){ // p_id는 postNum이랑 같다
        Post post = readPostUseCase.readPost(p_id);

        ApiResponse<PostResponse> response = new ApiResponse<>(new PostResponse(post));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my/read")
    public ResponseEntity<?> readMyPosts(@RequestParam Map<String, String> param, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        String page = param.get("page");
        Page<Post> postPage = readPostUseCase.readMyPosts(page, user);

        ApiResponse<Page<PostResponse>> response = new ApiResponse<>(
                postPage.map(post -> new PostResponse(post, false))
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> write(@ModelAttribute PostWriteReqDto postReqDto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        Long postNum = editPostUseCase.writePost(postReqDto, user);

        ApiResponse<Long> response = new ApiResponse<>(postNum);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/del")
    public ResponseEntity<?> deletePost(@RequestParam Long p_id, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        editPostUseCase.deletePost(p_id, user);

        ApiResponse<Boolean> response = new ApiResponse<>(true);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> edit(@ModelAttribute PostWriteReqDto postReqDto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        editPostUseCase.modifyPost(postReqDto, user);

        ApiResponse<Boolean> response = new ApiResponse<>(true);

        return ResponseEntity.ok(response);
    }

}
