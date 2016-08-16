package com.dimensiondata.cloud.client;

import com.dimensiondata.cloud.client.model.*;

public interface TagService
{
    ResponseType createTagKey(CreateTagKeyType createTagKeyType);

    TagKeys listTagKeys(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    TagKeyType getTagKey(String tagKeyId);

    ResponseType editTagKey(EditTagKeyType editTagKey);

    ResponseType deleteTagKey(String tagKeyId);

    ResponseType applyTags(ApplyTags applyTags);

    Tags listTags(int pageSize, int pageNumber, OrderBy orderBy, Filter filter);

    ResponseType removeTags(RemoveTagsType removeTags);
}
