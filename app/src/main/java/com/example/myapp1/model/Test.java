package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Test {


    // @SerializedName("id")
   // @Expose
   @DatabaseField(generatedId = true)
   private Long id;
   @DatabaseField(dataType = DataType.BYTE_ARRAY)
    @JsonIgnore
    byte[] imageBytes;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DatabaseField(dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd")
   private  Date date;
    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }



    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

}
