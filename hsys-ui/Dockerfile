# 1) Build
FROM node:20-alpine AS build
WORKDIR /app

# Copiamos archivos de dependencias
COPY hsys-ui/package*.json ./

# Usamos install en lugar de ci para asegurar que se generen los binarios en Alpine
RUN npm install

# Copiamos el contenido de la carpeta hsys-ui al WORKDIR (/app)
COPY hsys-ui/ .

# Ejecutamos el build. 
# Si falla, intenta cambiarlo por: RUN ./node_modules/.bin/ng build
RUN npm run build

# 2) Serve
FROM nginx:alpine

# Limpiamos el directorio por defecto de nginx
RUN rm -rf /usr/share/nginx/html/*

# Ajuste de ruta: Angular 17+ suele generar /dist/nombre-app/browser
COPY --from=build /app/dist/hsys-ui/browser/ /usr/share/nginx/html

# Nginx conf
COPY hsys-ui/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]