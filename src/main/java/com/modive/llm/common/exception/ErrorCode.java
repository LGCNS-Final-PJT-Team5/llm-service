package com.modive.llm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    HAS_EMAIL(HttpStatus.BAD_REQUEST, "ACCOUNT-002", "존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "비밀번호가 일치하지 않습니다."),

    // Gemini
    GEMINI_API_ERROR         (HttpStatus.BAD_GATEWAY,        "GEMINI-001", "Gemini 호출에 실패했습니다."),
    GEMINI_EMPTY_RESPONSE     (HttpStatus.INTERNAL_SERVER_ERROR,"GEMINI-002", "Gemini 응답이 비어 있습니다."),
    GEMINI_PARSE_ERROR        (HttpStatus.INTERNAL_SERVER_ERROR,"GEMINI-003", "Gemini 응답 파싱 실패"),
    TEMPLATE_LOAD_FAIL        (HttpStatus.INTERNAL_SERVER_ERROR,"TEMPLATE-001","프롬프트 템플릿 읽기 실패");


    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;				// ACCOUNT-001
    private final String message;			// 설명
}
