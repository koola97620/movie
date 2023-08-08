package com.example.pay.docs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class})
public class BaseControllerTest {
    //protected static final String DEFAULT_RESTDOC_PATH = "{class_name}/{method_name}/";

    @LocalServerPort
    private int port;
//    @Autowired
//    private H2DatabaseCleanUp h2DatabaseCleanUp;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        //h2DatabaseCleanUp.execute();
    }

    protected RequestSpecification spec;

    @BeforeEach
    void setUpRestdocs(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .setPort(port)
                .setConfig(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig().defaultCharsetForContentType("UTF-8", ContentType.JSON))
                        .decoderConfig(DecoderConfig.decoderConfig().defaultCharsetForContentType("UTF-8", ContentType.JSON))
                )
                .addFilter(
                        documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint())
                )
                .build();
    }
}

