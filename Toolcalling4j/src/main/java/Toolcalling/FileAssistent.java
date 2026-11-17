package Toolcalling;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface FileAssistent {

    @SystemMessage("""
            Act As fileAssistent  Use the available filesystem tools when
            the user asks about local files.Never invent file contents.
            """)
    @UserMessage("{{it}}")
    public String Fvamsi(String Message);
}
