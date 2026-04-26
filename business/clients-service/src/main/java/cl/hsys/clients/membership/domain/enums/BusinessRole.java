package cl.hsys.clients.membership.domain.enums;

public enum BusinessRole {

    //Roles para el cliente
    OWNER("Propietario"),
    ADMIN_CLIENT("Administrador"),
    PROJECT_MANAGER("Gerente de Proyecto"),
    PROJECT_USER("Usuario de Proyecto"),
    
    //Roles para el cliente del cliente
    PROJECT_CLIENT_MANAGER("Gerente de Cliente"),
    PROJECT_CLIENT_USER("Usuario de Cliente");

    private final String displayName;

    BusinessRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName () {
        return displayName;
    }

}
