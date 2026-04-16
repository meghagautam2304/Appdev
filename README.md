S# 📘 SonarQube Installation on Ubuntu

---

## 🔧 Install PostgreSQL

# Step 1: Install PostgreSQL
```bash
sudo apt install -y postgresql-common postgresql
```
# Step 2: Enable PostgreSQL at boot
```bash
sudo systemctl enable postgresql
```
# Step 3: Start PostgreSQL
```bash
sudo systemctl start postgresql
```
# Step 4: Login as postgres user
```bash
sudo -u postgres psql
```
# Step 5: Create user
```bash
CREATE ROLE sonaruser WITH LOGIN ENCRYPTED PASSWORD 'your_password';
```
# Step 6: Create database
```bash
CREATE DATABASE sonarqube;
```
# Step 7: Grant privileges
```bash
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonaruser;
```
# Step 8: Switch database
```bash
\c sonarqube
GRANT ALL PRIVILEGES ON SCHEMA public TO sonaruser;
```
# Step 9: Exit PostgreSQL
```bash
\q
```
