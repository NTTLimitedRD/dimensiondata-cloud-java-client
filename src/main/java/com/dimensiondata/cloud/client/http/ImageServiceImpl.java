package com.dimensiondata.cloud.client.http;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.dimensiondata.cloud.client.Filter;
import com.dimensiondata.cloud.client.ImageService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.CustomerImageType;
import com.dimensiondata.cloud.client.model.CustomerImages;
import com.dimensiondata.cloud.client.model.ImageMetadataType;
import com.dimensiondata.cloud.client.model.OsImageType;
import com.dimensiondata.cloud.client.model.OsImages;
import com.dimensiondata.cloud.client.model.ResponseType;

public class ImageServiceImpl implements ImageService
{
    private final HttpClient httpClient;

    public ImageServiceImpl(String baseUrl)
    {
        httpClient = new HttpClient(baseUrl);
    }

    @Override
    public OsImages listOsImages(int pageSize, int pageNumber, OrderBy orderBy)
    {
        return listOsImages(pageSize, pageNumber, orderBy, new Filter());
    }

    @Override
    public OsImages listOsImages(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        // TODO validate parameters
        return httpClient.get("image/osImage", OsImages.class,
            filter.concatenateParameters(
            new Param(Param.PAGE_SIZE, pageSize),
            new Param(Param.PAGE_NUMBER, pageNumber),
            new Param(Param.ORDER_BY, orderBy.concatenateParameters()))
        );
    }

    @Override
    public OsImageType getOsImage(String id)
    {
        return httpClient.get("image/osImage/" + id, OsImageType.class);
    }

    @Override
    public CustomerImages listCustomerImages(int pageSize, int pageNumber, OrderBy orderBy) {
        return listCustomerImages(pageSize, pageNumber, orderBy, new Filter());
    }

    @Override
    public CustomerImages listCustomerImages(int pageSize, int pageNumber, OrderBy orderBy, Filter filter)
    {
        // TODO validate parameters
        return httpClient.get("image/customerImage", CustomerImages.class,
            filter.concatenateParameters(
                new Param(Param.PAGE_SIZE, pageSize),
                new Param(Param.PAGE_NUMBER, pageNumber),
                new Param(Param.ORDER_BY, orderBy.concatenateParameters()))
            );
    }

    @Override
    public CustomerImageType getCustomerImage(String id)
    {
        return httpClient.get("image/customerImage/" + id, CustomerImageType.class);
    }

    @Override
    public ResponseType editImageMetadata(ImageMetadataType imageMetadata)
    {
        return httpClient.post("image/editImageMetadata",
                new JAXBElement<>(new QName(HttpClient.DEFAULT_NAMESPACE, "imageMetadata"), ImageMetadataType.class, imageMetadata),
                ResponseType.class);
    }
}