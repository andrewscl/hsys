export interface PublicTopbarInterface {
    label: string;
    url: string;
    icon?: string;          // Opcional: para FontAwesome como 'fa-home'
    fragment?: string;      // Opcional: para ir a una sección (ej: #contacto)
    external?: boolean;     // Opcional: para abrir en pestaña nueva
    cssClass?: string;      // Opcional: por si quieres resaltar un link específico
}