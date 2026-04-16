S# 📘 SonarQube Installation on Ubuntu

---
###  Install PostgreSQL
Step 1: Install PostgreSQL
```bash
sudo apt install -y postgresql-common postgresql
```
Step 2: Enable PostgreSQL at boot
```bash
sudo systemctl enable postgresql
```
Step 3: Start PostgreSQL
```bash
sudo systemctl start postgresql
```
Step 4: Login as postgres user
```bash
sudo -u postgres psql
```
Step 5: Create user
```bash
CREATE ROLE sonaruser WITH LOGIN ENCRYPTED PASSWORD 'your_password';
```
Step 6: Create database
```bash
CREATE DATABASE sonarqube;
```
Step 7: Grant privileges
```bash
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonaruser;
```
Step 8: Switch database
```bash
\c sonarqube
GRANT ALL PRIVILEGES ON SCHEMA public TO sonaruser;
```
Step 9: Exit PostgreSQL
```bash
\q
```
### Install Sonarqube
Step 1: Update packages
```bash
sudo apt update
```
Step 2: Install Java
```bash
sudo apt install openjdk-17-jdk -y
```
Step 3: Install unzip
```bash
sudo apt install unzip
```
Step 4: Check Java version
```bash
java -version
```
Step 5: Download SonarQube
```bash
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-25.2.0.102705.zip
```
Step 6: Extract files
```bash
unzip sonarqube-25.2.0.102705.zip
```
Step 7: Move to /opt
```bash
sudo mv sonarqube-25.2.0.102705/ /opt/sonarqube
```
Step 8: Create user
```bash
sudo adduser --system --no-create-home --group --disabled-login sonarqube
```
Step 9: Set permissions
```bash
sudo chown -R sonarqube:sonarqube /opt/sonarqube
```
### Install SonarScanner CLI
Step 1: Download SonarScanner
```bash
wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-7.0.1.4817-linux-x64.zip
```
Step 2: Extract
```bash
unzip sonar-scanner-cli-7.0.1.4817-linux-x64.zip
```
Step 3: Move directory
```bash
sudo mv sonar-scanner-7.0.1.4817-linux-x64/ /opt/sonarscanner
```
Step 4: Edit configuration
```bash
sudo nano /opt/sonarscanner/conf/sonar-scanner.properties
```
Update:
```bash
sonar.host.url=127.0.0.1
```
Step 5: Make executable
```bash
sudo chmod +x /opt/sonarscanner/bin/sonar-scanner
```
Step 6: Create global command
```bash
sudo ln -s /opt/sonarscanner/bin/sonar-scanner /usr/local/bin/sonar-scanner
```
Step 7: Verify installation
```bash
sonar-scanner -v
```
### Configure SonarQube
Step 1: Edit config file
```bash
sudo nano /opt/sonarqube/conf/sonar.properties
```
Add:
```bash
sonar.jdbc.username=sonaruser
sonar.jdbc.password=your_password
sonar.jdbc.url=jdbc:postgresql://localhost:5432/sonarqube
sonar.web.javaAdditionalOpts=-server
sonar.web.host=0.0.0.0
sonar.web.port=9000
```
Step 2: System configuration
```bash
sudo nano /etc/sysctl.conf
```
Add:
```bash
vm.max_map_count=524288
fs.file-max=131072
```
Step 3: Limits configuration
```bash
sudo nano /etc/security/limits.d/99-so
```
Add:
```bash
sonarqube - nofile 131072
sonarqube - nproc 8192
```
Step 4: Firewall setup
```bash
sudo ufw allow 9000/tcp
```
If UFW not installed:
```bash
sudo apt install ufw -y
sudo ufw allow 22/tcp
```
Reload:
```bash
sudo ufw reload
```
Check:
```bash
sudo ufw status
```

### Setup SonarQube as Service
Step 1: Create service file
```bash
sudo nano /etc/systemd/system/sonarqube.service
```
Add:
```bash
[Unit]
Description=SonarQube service
After=syslog.target network.target

[Service]
Type=forking
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop
User=sonarqube
Group=sonarqube
PermissionsStartOnly=true
Restart=always
StandardOutput=syslog
LimitNOFILE=131072
LimitNPROC=8192
TimeoutStartSec=5
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```
Step 2: Reload systemd
```bash
sudo systemctl daemon-reload
```
Step 3: Enable service
```bash
sudo systemctl enable sonarqube
```
Step 4: Start service
```bash
sudo systemctl start sonarqube
```
Step 5: Check status
```bash
sudo systemctl status sonarqube
```
Step 6: Reboot system
```bash
sudo reboot now
```






