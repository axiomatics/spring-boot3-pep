package com.axiomatics.cr.alfa.test.pep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Specifies attributes of a Category (like subject, resource, action, environment or another category) by listing a sequence of {@link Attribute}s
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Category {

    /**
     * A sequence of {@link Attribute}s that apply to the category of the request
     */
    @JsonProperty("Attribute")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    final List<Attribute> attributes = new ArrayList<>();
    /**
     * A string containing a XACML category URI or the shorthand notation defined
     * <p>
     * Mandatory for a {@link Category} object in the {@link Request#customCategories} member array; otherwise, optional.
     */
    @JsonProperty("CategoryId")
    String categoryId;
    /**
     * A unique identifier for this Category. It is primarily intended to be referenced in multiple requests.
     */
    @JsonProperty("Id")
    String id;
    /**
     * Specifies additional sources of attributes in free form XML document format
     * <p>
     * There are two possible ways to represent the XML content of a XACML request in the JSON representation: XML escaping or Base64
     * encoding. The request parser must determine whether XML escaping or Base 64 encoding is used. There are no members in the JSON request
     * to indicate which is used.
     * <p>
     * In both cases, any XML content sent in a JSON request MUST include all namespace definitions needed to parse that content.
     * <p>
     * The value of the "Content" member is a string which MUST contain an XML payload per the XACML specification. XML content must be escaped
     * before being inserted into the JSON request. JSON dictates double quotes (") be escaped using a backslash (\). This profile therefore follows
     * this behavior.
     * <p>
     * In addition, since the XML content could itself contain backslashes and possibly the sequence \", it is important to also
     * escape backslashes.
     * <p>
     * In the case of Base64 encoding, the XML content shall be converted to its Base64 representation.
     */
    @JsonProperty("Content")
    String content;

    public Category() {
    }

    public Category(String id) {
        this.id = id;
    }

    public boolean addAttribute(Attribute attribute) {
        return attributes.add(attribute);
    }

    public boolean addAttribute(String attributeId, Object value) {
        return attributes.add(new Attribute(attributeId, value));
    }

    public boolean addAttribute(String attributeId, Object value, String dataType) {
        return attributes.add(new Attribute(attributeId, value, dataType));
    }

    public boolean addAttribute(String attributeId, Object value, Boolean includeInResult) {
        return attributes.add(new Attribute(attributeId, value, includeInResult));
    }

    public boolean addAttribute(String attributeId, Object value, Boolean includeInResult, String dataType) {
        return attributes.add(new Attribute(attributeId, value, includeInResult, dataType));
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Category)) return false;
        final Category other = (Category) o;
        if (!Objects.equals(this.getCategoryId(), other.getCategoryId())) return false;
        if (!Objects.equals(this.getId(), other.getId())) return false;
        if (!Objects.equals(this.getContent(), other.getContent())) return false;
        return Objects.equals(this.getAttributes(), other.getAttributes());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $attributes = this.getAttributes();
        result = result * PRIME + ($attributes == null ? 43 : $attributes.hashCode());
        return result;
    }

    public String toString() {
        return "Category(categoryId=" + this.getCategoryId() + ", id=" + this.getId() + ", content=" + this.getContent() + ", attributes=" + this.getAttributes() + ")";
    }
}
