package com.scaler.projectservice.models;

import java.util.Date;

public class BaseModel {
    private Long id;
    private String createdBy;
    private Date createdAt;
    private Boolean isDeleted; // This can be an Enum as well.
}
