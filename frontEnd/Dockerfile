# Stage 1: Build the Angular application
FROM node:18.17.0-alpine AS builder

WORKDIR /usr/src/app

COPY package.json package-lock.json ./

# Install npm globally
RUN npm install -g npm@10.4.0

COPY . .

RUN npm run build --prod

# Stage 2: Serve the Angular application using NGINX
FROM nginx:1.15.8-alpine

# Copy the built Angular app from the builder stage to NGINX directory
COPY --from=builder /usr/src/app/dist/examfront/ /usr/share/nginx/html
