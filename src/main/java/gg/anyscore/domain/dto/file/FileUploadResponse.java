package gg.anyscore.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    private String fileName;
    private String folderPath;
}
