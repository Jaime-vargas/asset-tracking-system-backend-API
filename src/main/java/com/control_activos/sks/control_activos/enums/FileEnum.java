package com.control_activos.sks.control_activos.enums;

public enum FileEnum {
    CREATION_ERROR("DirectoryCreationError, Could not create directory for saving attachment files."),
    SAVE_ERROR("FileSaveError, Could not save the file to the specified path."),
    IMAGE_FORMAT_ERROR("InvalidFileFormat, The uploaded file is not a valid image format."),
    ALREADY_EXISTS("A file with the same name already exists.");

    private final String message;
    FileEnum(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public String getMessage(String message){
        return this.message + message;
    }
}
