export interface RegisterRequest {
    username: string;
    mail: string;
    password: string;
    companyName: string;
    phone?: string;
    taxId?: string;
    timezone: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    accessToken: string;
    refreshToken: string;
    tokenType: string;
    userId: string;
    username: string;
    roles: string[];
}