package com.bah.nos.model;

import org.codehaus.jackson.annotate.JsonCreator;

import java.util.Arrays;
import java.util.List;

public class ResourceFinderAnswer {

    private QuestionSectionEnum section;

    private Integer questionId;

    private Integer pageNum;

    private String questionText;

    private AnswerTypeEnum answerType;

    private List<String> answer;

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
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

    //TODO extend this class and do dynamic deserialization so only CHECKBOX type has list
    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public enum QuestionSectionEnum {
        CORE("Core"),
        GENERAL("General"),
        HOUSEHOLD("Household"),
        EDUCATION("Education"),
        HEALTH("Health"),
        INCOME_ASSISTANCE("Income & Assistance"),
        WORK_EXPERIENCE("Work Experience");

        private String sectionTitle;

        QuestionSectionEnum(String sectionTitle) {
            this.sectionTitle = sectionTitle;
        }

        @JsonCreator
        public static QuestionSectionEnum forValue(String value) {
            return Arrays.stream(QuestionSectionEnum.values())
                    .filter(s -> s.getSectionTitle().equalsIgnoreCase(value))
                    .findFirst().get();
        }

        public String getSectionTitle() {
            return sectionTitle;
        }
    }

    public enum AnswerTypeEnum {
        RADIO, TEXT, CHECKBOX
    }
}
