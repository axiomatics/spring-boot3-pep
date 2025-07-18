package com.axiomatics.cr.alfa.test.pep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * The status of the authorization decision result.
 * <p>
 * Indicates whether errors occurred during evaluation of the decision request, and optionally, information about those errors.  If the
 * {@link Response} element contains {@link Result} elements whose {@link Status} members are all identical, and the {@link Response} object is
 * contained in a protocol wrapper that can convey status information, then the common status information MAY be placed in the protocol wrapper and
 * this {@link Status} element MAY be omitted from all {@link Result} elements.
 */
public class Status {

    /**
     * The status code
     */
    @JsonProperty("StatusCode")
    StatusCode statusCode;

    /**
     * A free-form description of the status code
     */
    @JsonProperty("StatusMessage")
    String statusMessage;

    /**
     * Additional status information
     */
    @JsonProperty("StatusDetail")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    Object[] statusDetail;

    public Status() {
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Object[] getStatusDetail() {
        return this.statusDetail;
    }

    public void setStatusDetail(Object[] statusDetail) {
        this.statusDetail = statusDetail;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Status)) return false;
        final Status other = (Status) o;
        if (!Objects.equals(this.getStatusCode(), other.getStatusCode())) return false;
        if (!Objects.equals(this.getStatusMessage(), other.getStatusMessage())) return false;
        return java.util.Arrays.deepEquals(this.getStatusDetail(), other.getStatusDetail());
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $statusCode = this.getStatusCode();
        result = result * PRIME + ($statusCode == null ? 43 : $statusCode.hashCode());
        final Object $statusMessage = this.getStatusMessage();
        result = result * PRIME + ($statusMessage == null ? 43 : $statusMessage.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getStatusDetail());
        return result;
    }

    public String toString() {
        return "Status(statusCode=" + this.getStatusCode() + ", statusMessage=" + this.getStatusMessage() + ", statusDetail=" + java.util.Arrays.deepToString(this.getStatusDetail()) + ")";
    }
}
