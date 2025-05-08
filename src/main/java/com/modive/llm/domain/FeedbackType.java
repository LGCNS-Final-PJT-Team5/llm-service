package com.modive.llm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {

    // prompts/single-drive
    DRIVE("prompt/feedback/single-drive/single-feedback.txt"),

    // prompts/total-drive
    ECO("prompt/feedback/total-drive/1-eco-feedback.txt"),
    INSURANCE("prompt/feedback/total-drive/2-insurance-feedback.txt"),
    BEGINNER("prompt/feedback/total-drive/3-beginner-feedback.txt"),
    REWARD("prompt/feedback/total-drive/4-reward-feedback.txt"),
    MAINTENANCE("prompt/feedback/total-drive/5-maintenance-feedback.txt"),
    CARBON("prompt/feedback/total-drive/6-carbon-feedback.txt"),
    DRIVESTAR("prompt/total-drive/drive-star-driving-summary.txt"),
    TECHNIQUE("prompt/feedback/total-drive/8-technique-feedback.txt");

    private final String templatePath;   // 클래스패스 상대 경로
}
