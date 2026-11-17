package Toolcalling;

import Toolcalling.tools.CourseTools;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String Api_Key = System.getenv("OpenAI_API_KEY");
       OpenAiChatModel chatModel =OpenAiChatModel.builder()
               .apiKey(Api_Key)
               .modelName("gpt-4o-mini")
               .build();
        McpTransport Mcp = StdioMcpTransport.builder()
                .command(List.of(
                        "cmd", "/c",
                        "npx", "-y",
                        "@modelcontextprotocol/server-filesystem",
                        "--read-only",
                        "D:\\Test_MCP"
                )).build();
        McpClient client = DefaultMcpClient.builder()
                .key("filesystem-server")
                .transport(Mcp)
                .build();
        McpToolProvider ToolProvider = McpToolProvider.builder()
                .mcpClients(client).build();
        FileAssistent FAssist = AiServices.builder(FileAssistent.class)
                .chatModel(chatModel)
                .toolProvider(ToolProvider).build();
        String Response  = FAssist.Fvamsi("are there any .pdf files");
        System.out.println("File Resposne output is :"+Response);
        CourseTools courseTools = new CourseTools();
        CourseAssistant assistant = AiServices.builder(CourseAssistant.class)
                .chatModel(chatModel)
                .tools(courseTools)
                .build();
        String reply = assistant.Vamsi("what is the price of AgenticAI Course");
        System.out.println(reply);
    }
}
