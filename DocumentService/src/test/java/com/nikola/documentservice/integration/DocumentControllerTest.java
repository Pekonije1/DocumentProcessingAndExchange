package com.nikola.documentservice.integration;

import com.nikola.documentservice.AbstractTestBase;
import com.nikola.documentservice.enums.DocumentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DocumentControllerTest extends AbstractTestBase {
    private static final String PARAM_NAME = "files";
    private final Path UPLOAD_DIRECTORY = Paths.get("src/test/resources/upload");


    @AfterEach
    public void cleanUpUploadedFiles() throws IOException {
        if (Files.exists(UPLOAD_DIRECTORY)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(UPLOAD_DIRECTORY)) {
                for (Path filePath : stream) {
                    Files.deleteIfExists(filePath);
                }
            }
        }
    }

    @Test
    public void shouldUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile1 = getMockValidMultipartFile("item_data.csv", DocumentType.CSV);
        MockMultipartFile mockMultipartFile2 = getMockValidMultipartFile("item_data.xml", DocumentType.XML);
        MockMultipartFile mockMultipartFile3 = getMockValidMultipartFile("item_data.xml", DocumentType.PDF);
        MockMultipartFile mockMultipartFile4 = getMockInvalidMultipartFile("item_data.txt");

        mockMvc.perform(multipart("/documents/upload")
                        .file(mockMultipartFile1)
                        .file(mockMultipartFile2)
                        .file(mockMultipartFile3)
                        .file(mockMultipartFile4)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uploaded").exists())
                .andExpect(jsonPath("$.failedToUpload").exists());
    }

    private MockMultipartFile getMockInvalidMultipartFile(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "files/" + filename);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(
                PARAM_NAME,
                file.getName(),
                "text/txt",
                fileInputStream
        );
    }

    private MockMultipartFile getMockValidMultipartFile(String filename, DocumentType documentType) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "files/" + filename);
        FileInputStream fileInputStream = new FileInputStream(file);

        return new MockMultipartFile(
                PARAM_NAME,
                file.getName(),
                documentType.getValue(),
                fileInputStream
        );
    }
}

