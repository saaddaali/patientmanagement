# MediTrack App

MediTrack, an innovative healthcare monitoring system designed to track and ensure the safety of patients with cognitive disorders.
## Build the Project

To clean and package the project, run the following command:

```bash
.\mvnw clean package -DskipTests
```

## Create the Docker Image

Build the Docker image with the following command:

```bash
docker build -t ride-link:v1 .
```

## Display the Created Docker Image

To view the created Docker image, run:

```bash
docker images
```

## Run the Docker Container

Start the container using:

```bash
docker run -d -p 8036:8036 --name medi-track-ms1-v1 medi-track-ms1-v1
```

```bash
docker run -d -p 8037:8037 --name medi-track-ms2-v1 medi-track-ms2-v1
```


## Display the Running Container

To view the running container, use:

```bash
docker ps
```

## MinIO Configuration

1. Open MinIO at http://localhost:9001/login
2. Login credentials:
   - Username: saad@2024
   - Password: saad@2024
   (Check .env file in the Docker Compose folder for credentials)
3. In the MinIO dashboard:
   - Navigate to Identity >> Users
   - Create a user:
     - Username: saad
     - Password: saad
     - Role: read and write
4. Edit the user and click Service Account >> Create
5. Copy the Access Key and Secret Key to your application.properties file
6. Connect as "zyn" using the login credentials and create your bucket
7. Test file upload with:
   - Endpoint: http://localhost:8036/api/cloud/upload/bucket/your-default-bucket
   - Method: POST
   - Body: form-data with your file

## Generate SSL Certificates for Backend

Generate SSL certificates for both localhost and production:

```bash
# Create the private key
openssl genpkey -algorithm RSA -out key.pem -pkeyopt rsa_keygen_bits:2048

# Create certificate signing request
openssl req -new -key key.pem -out cert.csr -subj "/CN=YourIPOrLocalhost"

# Create self-signed certificate
openssl x509 -req -days 365 -in cert.csr -signkey key.pem -out cert.pem

# Create PKCS12 keystore
openssl pkcs12 -export -in cert.pem -inkey key.pem -out keystore.p12 -name yourAliasForExample
```

## Generate SSL Certificates for Frontend

Navigate to `frontend\sec\ssl\localhost` and run:

```bash
openssl pkcs12 -in ./../../../../backend-ms1/sec/ssl/localhost/keystore.p12 -out keyStore.pem -nodes
openssl rsa -in keyStore.pem -out key.pem
openssl x509 -in keyStore.pem -out cert.pem -days 365
```

## SonarQube Configuration

1. Open SonarQube at http://localhost:9000
2. Login credentials:
   - Username: admin
   - Password: admin (change after first login)
3. Create a local project:
   - Go to http://localhost:9000/projects/create
   - Click on "Create a local project"
   - Select "Use the global setting" and click Create
   - Choose "Create locally" and generate a token
   - Choose Maven and copy the generated command

## Install SSL Using Let's Encrypt

1. Install Certbot:
```bash
sudo apt install certbot python3-certbot-nginx
```

2. Create a subdomain (e.g., api.app.com)

3. Generate and install SSL:
```bash
sudo certbot --nginx -d api.app.com
```

4. For configuration issues, modify the NGINX configuration (`/etc/nginx/sites-enabled/default.conf`):

```nginx
server {
    server_name api.app.com;

    location / {
        rewrite ^/(.*) $1 break;
        proxy_pass "http://localhost:8036";
    }

    location /backend {
        rewrite ^/backend(.*) $1 break;
        proxy_pass "http://localhost:8090";
    }

    location /superadmin {
        rewrite ^/superadmin(.*) $1 break;
        proxy_pass "http://localhost:8030";
    }

    listen 443 ssl;
    ssl_certificate /etc/letsencrypt/live/api.app.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/api.app.com/privkey.pem;
    include /etc/letsencrypt/options-ssl-nginx.conf;
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;
}
```

5. Test SSL certificate renewal:
```bash
sudo certbot renew --dry-run
```

## Angular Project Setup

### Development Server

Start the Angular development server:

```bash
ng serve
```

Navigate to http://localhost:4200/

### Code Scaffolding

Generate new components or services:

```bash
ng generate component component-name
```

### Build the Angular Project

Build for production:

```bash
ng build --configuration production
```

### Running Tests

Run unit tests:

```bash
ng test
```

Run end-to-end tests:

```bash
ng e2e
```
