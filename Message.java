package org.example;
import java.io.Serializable;

public class Message implements Serializable
{
    private int number;
    private String content;
    public Message(int n, String s)
    {
        this.number = n;
        this.content = s;
    }
    public int getNumber() {
        return number;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}