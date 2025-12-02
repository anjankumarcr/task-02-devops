This task demonstrates a simple Jenkins Pipeline created directly inside the Jenkins UI.  
The pipeline performs:

1. Cloning a GitHub repository  
2. Building a Docker image  
3. Running a Docker container  
4. Listing Docker images  

This Level-01 focuses on understanding the Jenkins pipeline flow.

---

## 📂 Folder Structure
Task-02/
└── Level-01_JenkinsBasic/
├── JenkinsPipeline_Simple.groovy
├── README.md
└── screenshots/
├── job_config.png
├── pipeline_run.png
├── console_output.png
└── docker_images.png

yaml
Copy code

---

## 🚀 Steps to Execute the Pipeline

### 1️⃣ Create a New Jenkins Pipeline Job
- Open Jenkins → **New Item**
- Enter name: task02-level01
- Select **Pipeline**
- Click OK

---

### 2️⃣ Add Pipeline Script
Paste this pipeline:

pipeline {
    agent any

    stages {

        stage('Clone Repo') {
            steps {
                git "https://github.com/Become-DevOps/proj.git"
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t simple-image:latest .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f simple-container || true
                docker run -d --name simple-container -p 9090:80 simple-image:latest
                '''
            }
        }

        stage('Show Docker Images') {
            steps {
                sh 'docker images'
            }
        }
    }
}
3️⃣ Run the Pipeline
Click Build Now

View output in Console Output

4️⃣ Required Screenshots (Save inside screenshots/)
job_config.png

pipeline_run.png

console_output.png

docker_images.png
