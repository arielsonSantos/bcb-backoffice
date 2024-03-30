package br.com.arielsonsantos.bcbbackoffice.exception;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public class ErrorInfo {

    private LocalDateTime timestamp;
    private Integer statusCode;
    private String title;
    private String description;
    private String path;
    private Set<String> details;

    public ErrorInfo(final HttpStatus httpStatus, final WebRequest request, final String description) {
        this(httpStatus, request, description, Set.of());
    }

    public ErrorInfo(final HttpStatus httpStatus, final WebRequest request, final String description, final Set<String> details) {
        setTimestamp(LocalDateTime.now());
        setStatusCode(httpStatus.value());
        setTitle(httpStatus.getReasonPhrase());
        setDescription(description);
        setPath(request.getDescription(false));
        setDetails(details);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Set<String> getDetails() {
        return details;
    }

    public void setDetails(final Set<String> details) {
        this.details = details;
    }
}
