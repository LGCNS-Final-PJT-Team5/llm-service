package com.modive.llm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModiveException extends RuntimeException {
    ErrorCode errorCode;
}
