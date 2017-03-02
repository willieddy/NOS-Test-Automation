package com.bah.nos.model;

public class ResourceFinderAnswer {

    private QuestionSectionEnum section;

    private Integer questionId;

    private String questionText;

    private AnswerTypeEnum answerType;

    private String answer;

    public enum QuestionSectionEnum {
        CORE;
    }

    public enum AnswerTypeEnum {
        RADIO, TEXT;
    }
}
