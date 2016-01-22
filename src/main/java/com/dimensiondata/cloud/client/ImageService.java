package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface ImageService
{
    OsImages listOsImages(int pageSize, int pageNumber, OrderBy orderBy);

    OsImageType getOsImage(String id);

    CustomerImages listCustomerImages(int pageSize, int pageNumber, OrderBy orderBy);

    CustomerImageType getCustomerImage(String id);

    ResponseType editImageMetadata(ImageMetadataType imageMetadata);
}
