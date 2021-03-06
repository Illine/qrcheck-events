package ru.softdarom.qrcheck.events.builder;


import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import ru.softdarom.qrcheck.events.model.dto.FileDto;
import ru.softdarom.qrcheck.events.model.dto.ImageDto;
import ru.softdarom.qrcheck.events.model.dto.internal.InternalImageDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ImageBuilder {

    private final Collection<FileDto> externalSavedFiles;
    private final Collection<InternalImageDto> savedImages;

    public Set<ImageDto> build() {
        var externalFilesId2Url
                = externalSavedFiles.stream().collect(Collectors.toMap(FileDto::getId, FileDto::getUrl));
        return savedImages.stream()
                .map(it -> new ImageDto(
                        it.getId(),
                        externalFilesId2Url.get(it.getExternalImageId()),
                        FilenameUtils.getExtension(externalFilesId2Url.get(it.getExternalImageId())))
                )
                .collect(Collectors.toSet());
    }

}