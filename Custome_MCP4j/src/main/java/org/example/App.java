package org.example;

import io.modelcontextprotocol.json.McpJsonDefaults;
import io.modelcontextprotocol.json.McpJsonMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import org.example.services.CourseService;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println("Hello World!");
        CourseService courseService = new CourseService();

        //configure Communication
        StdioServerTransportProvider transport = new StdioServerTransportProvider(McpJsonDefaults.getMapper());

        //create MCP Server
        McpSyncServer server = McpServer.sync(transport)
                .serverInfo("Course_MCP_Server","1.0.0")
                .capabilities(
                        McpSchema.ServerCapabilities.builder()
                                .tools(true)
                                .build()).build();
        //Defining the first inputtool schema with zero params
        Map<String,Object> emptySchema = Map.of("type", "object", "properties", Map.of());

        //Create ListCourse tool and register as a MCP tool

        var listCoursesTool = McpServerFeatures.SyncToolSpecification.builder()
                .tool(
                        McpSchema.Tool.builder("listCourse", emptySchema)
                                .description("List all Avaliable courses")
                                .build()
                ).callHandler((exchange,request)->{
                    String Courses = courseService.getCourselist();
                    return result(Courses);
                }).build();
        server.addTool(listCoursesTool);

        //Defining the first inputtool schema with one params
        Map<String,Object> oneSchema = Map.of("type", "object", "properties", Map.of(
                "courseName",Map.of(
                        "type","string",
                        "description","Name of Course"

                )
        ),"required", List.of("courseName"));

        //Create CoursePrice tool and register with MCP

       var courseprice= McpServerFeatures.SyncToolSpecification.builder()
                .tool(
                        McpSchema.Tool.builder("getCoursePrice",oneSchema)
                                .description("Get Course price details")
                                .build()
                ).callHandler((exchange,request)->{
                           String name = (String)
                                   request.arguments().get("courseName");
                    String CoursePrice = courseService.getCoursePrice(name);
                    return result(CoursePrice);
                        }
                        ).build();
                server.addTool(courseprice);


        //Create CourseDuration tool and register with MCP
        var courseDuration = McpServerFeatures.SyncToolSpecification.builder()
                .tool(
                        McpSchema.Tool.builder("CourseDuration",oneSchema)
                                .description("Get CoursDuration details")
                        .build()
                ).callHandler((exchange,request)->{
                    String name = (String)
                            request.arguments().get("courseName");
                    String CourseDuration = courseService.getCourseDuration(name);
                    return result(CourseDuration);
                }).build();
        server.addTool(courseDuration);
    }

    public static McpSchema.CallToolResult result(String text)
    {
        return McpSchema.CallToolResult.builder()
                .content(
                        List.of(
                                new McpSchema.TextContent(text)
                        )
                )

                .build();
    }
}
