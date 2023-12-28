package api.freelive.util;

import java.util.Arrays;
import java.util.List;

public class FileFilter {

    // 허용된 이미지 확장자 목록
    private static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg", "gif");

    // 파일 확장자를 추출하는 유틸리티 메서드
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return ""; // 확장자가 없는 경우
        }
        return fileName.substring(lastDotIndex + 1);
    }

    // 허용된 이미지 확장자인지 확인하는 메서드
    public static boolean isAllowedImageExtension(String extension) {
        return ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }

}
