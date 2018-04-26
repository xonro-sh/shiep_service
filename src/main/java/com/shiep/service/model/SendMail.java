package com.shiep.service.model;


import java.io.Serializable;

/**
 * 发送邮件模型
 *
 * @author louie
 * @date created in 2018-4-26 14:56
 */
public class SendMail implements Serializable {
    /**
     * 发件人
     */
    private String mailFrom = "admin";

    /**
     * 收件人
     */
    private String mailTo;

    /**
     * 抄送
     */
    private String mailToCc;

    /**
     * 标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailToCc() {
        return mailToCc;
    }

    public void setMailToCc(String mailToCc) {
        this.mailToCc = mailToCc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
