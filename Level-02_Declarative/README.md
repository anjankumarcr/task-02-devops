# Task-02 — Level-02: Declarative Jenkins Pipeline

## Overview
This folder contains the **Declarative Jenkinsfile** for Level-02.  
The pipeline is stored in source control and executed by Jenkins using **Pipeline script from SCM**.

## Files
- `Jenkinsfile` — Declarative pipeline (located in this folder)
- `screenshots/` — required screenshots after running the pipeline:
  - `job_config1.png`  — Jenkins job (Pipeline from SCM) configuration
  - `pipeline_run.png` — Pipeline stage view after run
  - `docker_image1s.png` — Output of `docker images` showing the built image
  - `browser_output.png` — (optional) browser showing app if container is run

## Jenkins job setup (Pipeline script from SCM)
1. Jenkins → **New Item** → name: `task02-level02` → **Pipeline** → OK  
2. In **Pipeline** section:
   - Definition: **Pipeline script from SCM**
   - SCM: **Git**
   - Repository URL: `https://github.com/anjankumarcr/task-02-devops.git`
   - Credentials: (use your GitHub credential or Personal Access Token)
   - Branch Specifier: `*/main`
   - Script Path: `Task-02/Level-02_Declarative/Jenkinsfile`
   - Check **Lightweight checkout** (optional)
3. Save and **Build Now**

## How pipeline works (what to expect)
- Stage `Checkout` — clones the repo `main` branch.
- Stage `Build` — simple build echo (placeholder).
- Stage `Test` — placeholder for tests.
- Stage `Docker Build` — attempts `docker build -t level2-image:v1 .`
  - If there is no Dockerfile, build is skipped (the Jenkinsfile includes a safe fallback).
- `post` section prints success/failure.

## Commands to verify on the Jenkins host

- Check Docker images and find the Level-02 image:
  ```bash
  docker images | grep level2-image || true
Show all images:

bash
Copy code
docker images
Show running containers:

bash
Copy code
docker ps
If you want to run the image locally (on Jenkins server):

bash
Copy code
docker run -d --name level2-container -p 8081:80 level2-image:v1
# then open http://<JENKINS_HOST_IP>:8081 in browser
If docker run fails because port in use, change 8081 to another free port.

Screenshots
Save the required screenshots into this folder:
~/Task-02/Level-02_Declarative/screenshots/

Suggested filenames:

job_config1.png

pipeline_run.png

docker_image1s.png

browser_output.png (optional)

Notes
Make sure Jenkins has Docker installed and that user jenkins is in the docker group, or use a node/agent that has Docker.

If the pipeline cannot find the Jenkinsfile, verify Script Path exactly:
Task-02/Level-02_Declarative/Jenkinsfile

bash
Copy code

---

# 3) Commands to copy your screenshots into the Level-02 screenshots folder

If your screenshots are in Windows (WSL style path `/mnt/c/...`) — use one of the following.

**Using quoted paths (recommended when username or folder has spaces):**
```bash
cp "/mnt/c/Users/ANJAN KUMAR CR/Downloads/job_config1.png" ~/Task-02/Level-02_Declarative/screenshots/ || true
cp "/mnt/c/Users/ANJAN KUMAR CR/Downloads/pipeline_run.png" ~/Task-02/Level-02_Declarative/screenshots/ || true
cp "/mnt/c/Users/ANJAN KUMAR CR/Downloads/docker_image1s.png" ~/Task-02/Level-02_Declarative/screenshots/ || true
cp "/mnt/c/Users/ANJAN KUMAR CR/Downloads/browser_output.png" ~/Task-02/Level-02_Declarative/screenshots/ || true
Or if files are on Desktop:

bash
Copy code
cp "/mnt/c/Users/ANJAN KUMAR CR/Desktop/job_config1.png" ~/Task-02/Level-02_Declarative/screenshots/ || true
...
Verify:

bash
Copy code
ls -la ~/Task-02/Level-02_Declarative/screenshots/
4) Useful verification commands (run on your Jenkins host)
Confirm Jenkinsfile path exists locally (in your repo clone):

bash
Copy code
ls -la ~/Task-02/Level-02_Declarative/Jenkinsfile
Confirm the repo on GitHub contains the Jenkinsfile (quick check from server):

git -C ~/Task-02 remote -v || true
# or view via curl raw file (optional)
curl -s https://raw.githubusercontent.com/anjankumarcr/task-02-devops/main/Task-02/Level-02_Declarative/Jenkinsfile | sed -n '1,120p'
Check the built image (if pipeline ran docker build successfully):
docker images | grep level2-image || echo "level2-image not found"
Check container (if you ran it):

docker ps --filter "ancestor=level2-image:v1"
