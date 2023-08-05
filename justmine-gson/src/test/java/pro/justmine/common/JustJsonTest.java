package pro.justmine.common;


import pro.justmine.common.gson.JustGson;

public class JustJsonTest {
    public static void main(String[] args) {

        String jsonString = JustJson.toJsonString(new Object() {
            public String name = "John";
            public int age = 42;
        });
        System.out.println(jsonString);

    }

}