package Toolcalling;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface CourseAssistant {
    @SystemMessage("""
            Yoy are best Course Assistent, use avaliable tool to answer 
            price and duration of the given course , Don't invent by your own any.
            """)
    @UserMessage("{{it}}")
    String Vamsi(String course);
}
