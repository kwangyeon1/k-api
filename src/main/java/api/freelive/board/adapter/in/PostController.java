package api.freelive.board.adapter.in;

import api.freelive.board.adapter.in.response.PostResponse;
import api.freelive.board.application.port.in.WritePostUseCase;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostReqDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final WritePostUseCase writePostUseCase;

    @PostMapping(path = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> write(@ModelAttribute PostReqDto postReqDto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        Long postNum = writePostUseCase.writePost(postReqDto, user);

        return ResponseEntity.ok(new PostResponse(postNum));
    }

}
