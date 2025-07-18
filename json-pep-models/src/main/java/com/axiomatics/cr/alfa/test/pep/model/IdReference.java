package com.axiomatics.cr.alfa.test.pep.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Holder of the identifier and version of a policy or policy set which was applicable to the request
 */
public class IdReference {

    /**
     * The identifier of a policy or policy set, depending on which list of {@link PolicyIdentifierList} the {@link IdReference} belongs to
     */
    @JsonProperty("Id")
    String id;

    /**
     * The version of a policy or policy set, depending on which list of {@link PolicyIdentifierList} the {@link IdReference} belongs to
     */
    @JsonProperty("Version")
    String version;

    public IdReference() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof IdReference)) return false;
        final IdReference other = (IdReference) o;
        if (!Objects.equals(this.getId(), other.getId())) return false;
        return Objects.equals(this.getVersion(), other.getVersion());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $version = this.getVersion();
        result = result * PRIME + ($version == null ? 43 : $version.hashCode());
        return result;
    }

    public String toString() {
        return "IdReference(id=" + this.getId() + ", version=" + this.getVersion() + ")";
    }
}
