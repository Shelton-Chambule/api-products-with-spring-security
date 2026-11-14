package project.product.spring.security.entity;
public enum UserRole {

    ADMIN("admin"),
    USER("user"),
    MANAGER("manager");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
