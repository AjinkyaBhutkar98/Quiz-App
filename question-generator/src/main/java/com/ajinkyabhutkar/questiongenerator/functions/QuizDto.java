package com.ajinkyabhutkar.questiongenerator.functions;




public class QuizDto {

    private String id;
    private  String title;
    private  String description;
    private  Integer maxMarks;
    private  Integer timeLimit;
    private  String createdBy;
    private  Integer noOfQuestions;
    private  String imageUrl;
    private  Boolean live;
    private  Integer passingMarks;
    private  Long categoryId;

    private CategoryDto categoryDto;

    public QuizDto(String id, String title, String description, Integer maxMarks, Integer timeLimit, String createdBy, Integer noOfQuestions, String imageUrl, Boolean live, Integer passingMarks, Long categoryId, CategoryDto categoryDto) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        this.timeLimit = timeLimit;
        this.createdBy = createdBy;
        this.noOfQuestions = noOfQuestions;
        this.imageUrl = imageUrl;
        this.live = live;
        this.passingMarks = passingMarks;
        this.categoryId = categoryId;
        this.categoryDto = categoryDto;
    }

    public QuizDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(Integer noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public Integer getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(Integer passingMarks) {
        this.passingMarks = passingMarks;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
