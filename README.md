SonarQube Installation on Ubuntu
🔧 Install PostgreSQL
Step 1: Install PostgreSQL
sudo apt install -y postgresql-common postgresql
Step 2: Enable PostgreSQL at boot
sudo systemctl enable postgresql
Step 3: Start PostgreSQL
sudo systemctl start postgresql
Step 4: Login as postgres user
sudo -u postgres psql
Step 5: Create user
CREATE ROLE sonaruser WITH LOGIN ENCRYPTED PASSWORD 'your_password';
Step 6: Create database
CREATE DATABASE sonarqube;
Step 7: Grant privileges
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonaruser;
Step 8: Switch database
\c sonarqube
GRANT ALL PRIVILEGES ON SCHEMA public TO sonaruser;
Step 9: Exit PostgreSQL
\q
