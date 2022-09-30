package gg.anyscore.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gg.anyscore.domain.model.PictureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 13.09.2022
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "folder_path")
    private String folderPath;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PictureType type;

    public Picture(final String folderPath, final String fileName, final String title, final PictureType type) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        this.title = title;
        this.type = type;
    }
}
