package com.gatrium.gatrium;

import java.net.URI;
import java.util.Date;

public class MyImage {
    public URI imageURI;
    public Date imgDate;

    public URI getImageURI() {
        return imageURI;
    }

    public void setImageURI(URI imageURI) {
        this.imageURI = imageURI;
    }

    public Date getImgDate() {
        return imgDate;
    }

    public void setImgDate(Date imgDate) {
        this.imgDate = imgDate;
    }
}
