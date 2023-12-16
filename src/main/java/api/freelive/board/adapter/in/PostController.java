package api.freelive.board.adapter.in;

import api.freelive.board.adapter.in.response.PostResponse;
import api.freelive.board.application.port.in.ReadPostUseCase;
import api.freelive.board.application.port.in.WritePostUseCase;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostDto;
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
    private final WritePostUseCase writePostUseCase;

    @GetMapping("/read")
    public ResponseEntity<?> readPosts(@RequestParam Map<String, String> param){
        String page = param.get("page");
        Page<Post> postPage = readPostUseCase.readPosts(page);

        return ResponseEntity.ok(postPage.map(post -> new PostResponse(post, false)));
    }

    @GetMapping("/read/{postNum}")
    public ResponseEntity<?> readPost(@PathVariable Long postNum){
        Post post = readPostUseCase.readPost(new PostDto(postNum));

        return ResponseEntity.ok(new PostResponse(post));
    }

    @PostMapping(path = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> write(@ModelAttribute PostWriteReqDto postReqDto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        Long postNum = writePostUseCase.writePost(postReqDto, user);

        return ResponseEntity.ok(new PostResponse(postNum));
    }

}
