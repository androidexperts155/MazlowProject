
package com.mazlow.ui.users.dashboard.set_goals.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GoalInfo {
    public boolean isChecked=false;

    @SerializedName("answer")
    private String mAnswer;
    @SerializedName("name")
    private String mName;
    @SerializedName("question")
    private String mQuestion;
    @SerializedName("type")
    private String mType;
    @SerializedName("_id")
    private String m_id;

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

}
