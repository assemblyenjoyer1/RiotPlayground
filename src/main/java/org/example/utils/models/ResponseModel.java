package org.example.utils.models;

import java.util.List;

public class ResponseModel {
    public String id;
    public String object;
    public long created;
    public String model;
    public List<Choice> choices;
    public Usage usage;

    public static class Choice {
        public String text;
        public int index;
        public Object logprobs;
        public String finish_reason;
    }

    public static class Usage {
        public int prompt_tokens;
        public int completion_tokens;
        public int total_tokens;
    }
}