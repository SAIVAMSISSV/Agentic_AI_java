package org.example;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.service.AiServices;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        McpTransport transport = StdioMcpTransport.builder()
                .command(List.of(
                        "java",
                        "-jar",
                        // Build the server first: mvn package in custom-mcpserver
                        "D:\\AI_With_Java\\CustomToolCll4j\\Custome_MCP4j\\target\\Custome_MCP4j-1.0-SNAPSHOT.jar"
                ))
                .logEvents(true)
                .build();
        System.out.println("Transport created");

        McpClient client = DefaultMcpClient.builder()
                .transport(transport)
                .build();
        System.out.println("Client created");

        McpToolProvider tools = McpToolProvider.builder()
                .mcpClients(client)
                .build();

        CourseAssistant Aiservice = AiServices.builder(CourseAssistant.class)
                .toolProvider(tools)
                .chatModel(model).build();

        String res=Aiservice.chat("provide the duration of all courses");
        System.out.println(res);
    }
}
