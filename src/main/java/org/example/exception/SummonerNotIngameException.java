package org.example.exception;

public class SummonerNotIngameException extends Exception {
    public SummonerNotIngameException() {
        super("The summoner is not ingame.");
    }
}