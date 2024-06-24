package iwo.wintech.jwt.api;

public interface JWTObjectParser<T> {

    /**
     * Parses the given object into a string representation.
     *
     * @param obj The object to be parsed.
     * @return A string representation of the object.
     */
    String parseObjectToString(T obj);

    /**
     * Parses the given string into an object of the specified type.
     *
     * @param str The string to be parsed.
     * @return An object of the specified type, parsed from the given string.
     */
    T parseStringToObject(String str);
}
