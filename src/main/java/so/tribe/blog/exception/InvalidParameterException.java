package so.tribe.blog.exception;

import org.springframework.web.bind.MissingRequestValueException;

public class InvalidParameterException  extends MissingRequestValueException {
    private final String parameterName;
    private final String parameterType;

    public InvalidParameterException(String parameterName, String parameterType) {
        this(parameterName, parameterType, false);
    }

    public InvalidParameterException(String parameterName, String parameterType, boolean missingAfterConversion) {
        super("", missingAfterConversion);
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    public String getMessage() {
        return "Required request parameter '" + this.parameterName + "' for method parameter type " + this.parameterType + " is " + (this.isMissingAfterConversion() ? "present but converted to null" : "not present");
    }

    public final String getParameterName() {
        return this.parameterName;
    }

    public final String getParameterType() {
        return this.parameterType;
    }
}
