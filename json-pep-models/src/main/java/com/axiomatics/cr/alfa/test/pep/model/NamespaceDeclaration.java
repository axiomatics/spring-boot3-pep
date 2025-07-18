package com.axiomatics.cr.alfa.test.pep.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class NamespaceDeclaration {

    @JsonProperty("Prefix")
    String prefix;

    @JsonProperty("Namespace")
    String namespace;

    public NamespaceDeclaration(String namespace) {
        this.namespace = namespace;
    }

    public NamespaceDeclaration(String namespace, String prefix) {
        this.namespace = namespace;
        this.prefix = prefix;
    }

    public NamespaceDeclaration() {
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NamespaceDeclaration)) return false;
        final NamespaceDeclaration other = (NamespaceDeclaration) o;
        if (!Objects.equals(this.getPrefix(), other.getPrefix())) return false;
        return Objects.equals(this.getNamespace(), other.getNamespace());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $prefix = this.getPrefix();
        result = result * PRIME + ($prefix == null ? 43 : $prefix.hashCode());
        final Object $namespace = this.getNamespace();
        result = result * PRIME + ($namespace == null ? 43 : $namespace.hashCode());
        return result;
    }

    public String toString() {
        return "NamespaceDeclaration(prefix=" + this.getPrefix() + ", namespace=" + this.getNamespace() + ")";
    }
}
