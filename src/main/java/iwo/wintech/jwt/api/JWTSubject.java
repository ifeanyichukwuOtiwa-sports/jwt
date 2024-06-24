package iwo.wintech.jwt.api;

import iwo.wintech.jwt.main.model.AccessRole;

import java.util.Set;
import java.util.UUID;

/**
 * JWTSubject interface represents a subject that can be authenticated using JWT.
 * It provides methods to retrieve the unique identifier, email, username, name, password, and roles of the subject.
 * The {@code canEqual} method is a default implementation that checks if two subjects have the same ID and email.
 *
 * @author Ifeanyichukwu Otiwa
 * @version 0.0.1-SNAPSHOT
 */
public interface JWTSubject {

    /**
     * Get the unique identifier of the subject.
     *
     * @return the unique identifier as a UUID
     */
    UUID getId();

    /**
     * Get the email of the subject.
     *
     * @return the email of the subject as a String
     */
    String getEmail();

    /**
     * Get the username of the subject.
     *
     * @return the username of the subject as a String
     */
    String getUsername();

    /**
     * Get the name of the subject.
     *
     * @return the name of the subject as a String
     */
    String getName();

    /**
     * Get the password of the subject.
     *
     * @return the password of the subject as a String
     */
    String getPassword();

    /**
     * Get the roles of the subject.
     *
     * @return the roles of the subject as a Set of AccessRole objects
     */
    Set<AccessRole> getRoles();

    /**
     * Default implementation of the {@code canEqual} method.
     * Checks if two subjects have the same ID and email.
     *
     * @param other the other subject to compare with
     * @return true if the ID and email are the same, false otherwise
     */
    default boolean canEqual(JWTSubject other) {
        return this.getId().equals(other.getId()) && this.getEmail().equals(other.getEmail());
    }
}