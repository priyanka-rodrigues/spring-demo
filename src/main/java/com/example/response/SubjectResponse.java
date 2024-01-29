package com.example.response;

import com.example.entity.Subject;

public class SubjectResponse {

    private int id;

    private String subjectName;

    private int marks;

    public SubjectResponse (Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.marks = subject.getMarks();
    }
}

