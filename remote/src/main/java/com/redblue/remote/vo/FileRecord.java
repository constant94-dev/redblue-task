package com.redblue.remote.vo;

import java.nio.file.Path;
import java.util.Objects;
import lombok.Getter;

@Getter
public class FileRecord {
    private final Path rootLocation;

    public FileRecord(Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootLocation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FileRecord)) {
            return false;
        } else {
            FileRecord other = (FileRecord) obj;
            return Objects.equals(rootLocation, other.rootLocation);
        }
    }

    @Override
    public String toString() {
        return "FileRecord{" +
                "fileName='" + rootLocation + '\'' +
                '}';
    }
}
