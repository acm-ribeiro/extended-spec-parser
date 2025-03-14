package extended_parser_domain;


public class APIProperty {

    private final String name;
    private final String type;
    private final String format;
    private final String itemsType;
    private final String itemsFormat;
    private final String itemsPattern;
    private final String ref;
    private final String refersTo;
    private String pattern;

    private final int minimum, maximum;
    private final boolean isCollection;
    private final boolean required, gen;

    public APIProperty(String name, String type, String pattern, String format, String itemsType,
                       String itemsFormat, String itemsPattern, String ref, int minimum,
                       int maximum, boolean isCollection, boolean required, boolean gen, String refersTo) {
        this.name = name;
        this.type = type;
        this.pattern = pattern;
        this.format = format;
        this.itemsType = itemsType;
        this.itemsFormat = itemsFormat;
        this.itemsPattern = itemsPattern;
        this.minimum = minimum;
        this.maximum = maximum;
        this.isCollection = isCollection;
        this.required = required;
        this.gen = gen;
        this.ref = ref;
        this.refersTo = refersTo;
    }

    public String getRefersTo() {
        if (!refersTo.isEmpty()) {
            String[] parts = refersTo.split("/");
            String idProperty = parts[parts.length - 1];
            String schemaName = parts[parts.length - 2];
            return schemaName + "/" + idProperty;
        } else
            return refersTo;
    }

    public String getRef() {
        return ref;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isGen() {
        return gen;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public String getItemsType() {
        return itemsType;
    }

    public String getItemsFormat() {
        return itemsFormat;
    }

    public String getItemsPattern() {
        return itemsPattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public String toString() {
        String space = "\n        ";
        if (isCollection)
            return space + name + " {type: " + type + ", pattern: " + pattern + ", itemsType: " + itemsType + ", required: " + required + "}";
        else if (minimum == -999 && format != null && format.isEmpty())
            return space + name + " {type: " + type + ", pattern: " + pattern + ", required: " + required + ", refersTo: " + refersTo + "}";
        else if (minimum != -999 && format != null && format.isEmpty())
            return space + name + " {type: " + type + ", pattern: " + pattern + ", required: " + required + ", minimum: " + minimum + ", refersTo: " + refersTo + "}";
        else if (minimum == -999)
            return space + name + " {type: " + type + ", pattern: " + pattern + ", required: " + required + ", format: " + format + ", refersTo: " + refersTo + "}";
        else
            return space + name + " {type: " + type + ", pattern: " + pattern + ", required: " + required + ", format: " + format + ", minimum: " + minimum + ", refersTo: " + refersTo + "}";
    }
}
