package org.example.services;

import java.util.Locale;

public class CourseService {

    public String getCourselist(){

        return "java,python,AgenticAI,devops";
    }

    public String getCoursePrice(String Name){

       return switch (Name.toLowerCase()){

           case "java"->" java couse costs 4000INR";
           case "python"->" python couse costs 7000INR";
           case "AgenticAI"->"AgenticAI course costs 10000INR";
           case "devops"->"devops course costs 50000INR";
           default -> "Given course details  found";

        };
    }

    public String getCourseDuration(String Name){

        return switch (Name.toLowerCase()){

            case "java"->" java couse Duration 2monthes";
            case "python"->" python couse Duration 3monthes";
            case "AgenticAI"->"AgenticAI course Duration 3.5monthes";
            case "devops"->"devops course Duration 1.5monthes";
            default -> "Given course details not found";

        };
    }

}
