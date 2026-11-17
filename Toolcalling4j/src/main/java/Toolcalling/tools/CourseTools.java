package Toolcalling.tools;

import dev.langchain4j.agent.tool.Tool;

public class CourseTools {

    @Tool("Get the price the of given course")
    public String GetCoursePrice(String courseName){
        System.out.println("Getting the price of the course " + courseName);
        if(courseName.equals("Java Course")){
            return "3000";
        }
        else if(courseName.equals("Python Course")){
            return "7000";
        }
        else if(courseName.equals("AgenticAI Course")){
            return "10000";
        }
        else{
            return "No Course Avaliabel , one the given input";
        }

    }
    @Tool("Get the Duration of a given course")
    public String GetCourseDuration(String courseName){
        if(courseName.equals("JavaCourse")){
            return "2Monthes";
        }
        else if(courseName.equals("PythonCourse")){
            return "1.5Monthes";
        }
        else if(courseName.equals("AgenticAI")){
            return "3monthes";
        }
        else{
            return "No Course Avaliabel , one the given input";
        }
    }
}
