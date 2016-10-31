package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface ImageService
{
    OsImages listOsImages(int pageSize, int pageNumber, OrderBy orderBy);

    OsImages listOsImages(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    OsImageType getOsImage(String id);

    CustomerImages listCustomerImages(int pageSize, int pageNumber, OrderBy orderBy);

    CustomerImages listCustomerImages(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    CustomerImageType getCustomerImage(String id);

    ResponseType editImageMetadata(ImageMetadataType imageMetadata);
}
