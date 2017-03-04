package com.bah.nos.model;

public class ResourceFinderAnswer {

    private QuestionSectionEnum section;

    private Integer questionId;

    private String questionText;

    private AnswerTypeEnum answerType;

    private String answer;

    public QuestionSectionEnum getSection() {
        return section;
    }

    public void setSection(QuestionSectionEnum section) {
        this.section = section;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public AnswerTypeEnum getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerTypeEnum answerType) {
        this.answerType = answerType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public enum QuestionSectionEnum {
        CORE, GENERAL, HOUSEHOLD, EDUCATION, HEALTH, INCOME_ASSISTANCE, WORK_EXPERIENCE;
    }

    public enum AnswerTypeEnum {
        RADIO, TEXT;
    }
}
