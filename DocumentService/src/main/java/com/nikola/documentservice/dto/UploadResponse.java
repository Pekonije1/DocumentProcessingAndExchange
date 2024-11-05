package com.nikola.documentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UploadResponse {
    private List<String> uploaded;
    private List<String> failedToUpload;
}
