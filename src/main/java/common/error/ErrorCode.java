package common.error;

public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    DUPLICATE_USER("이미 존재하는 사용자입니다."),
    INVALID_LOGIN("아이디 또는 비밀번호가 일치하지 않습니다."),
    TODO_ALREADY_EXISTS("오늘의 할 일은 이미 작성되었습니다."),
    TODO_NOT_FOUND("할 일을 찾을 수 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
