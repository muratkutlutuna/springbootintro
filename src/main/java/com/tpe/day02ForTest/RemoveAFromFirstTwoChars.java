package com.tpe.day02ForTest;

public class RemoveAFromFirstTwoChars {
    //Task: write a method which will remove "A" from a string. If "A" is found in first two characters
    //AABD --> BD, ABC --> BC , AA --> ''

    public String removeAFromFirstTwoChars(String str){
        //if the length is less than or equal to 2, we do not need to use substring method
        if (str.length() < 2) {
            return str.replaceAll("A","");

        }
        String firstTwoChars = str.substring(0,2);//get first two characters from the string
        String charsAfterFirstTwo = str.substring(2);//get rest of characters
        return firstTwoChars.replaceAll("A","")+charsAfterFirstTwo;
    }

}
