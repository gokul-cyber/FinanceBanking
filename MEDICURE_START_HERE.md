# Medicure Healthcare Project

This folder is the Medicure working project, initialized from the tested FinanceMe Spring Boot Maven installation so the existing Java 17 and Maven setup can be reused immediately.

## Source brief

- `Medicure-Brief.pdf` contains the supplied healthcare-domain requirements.
- Existing Spring Boot source is under `src/`.
- Maven project file: `pom.xml`.

## First run

```powershell
cd C:\Users\raksh\Downloads\code\Medicure-Healthcare-Project
mvn clean test
mvn spring-boot:run
```

The inherited baseline starts on `http://localhost:8080`. Medicure-specific entities, endpoints, and UI should be added from the source brief after confirming its detailed requirements.

## Existing delivery assets reused

- `Dockerfile`
- `docker-compose.yml`
- `Jenkinsfile`
- `terraform/`
- `ansible/`
- `monitoring/`

Do not commit credentials, private keys, or deployment secrets.

## DevOps assets

- `kubernetes/namespace.yaml`, `deployment.yaml`, and `service.yaml` define the Medicure namespace, two application replicas, health probes, CPU autoscaling, and an internal service.
- `.github/workflows/ci.yml` runs on pushes and pull requests to `main`/`master`, then tests, packages, and builds the Docker image.
- `Jenkinsfile` contains the Jenkins test, package, Docker, test deployment, approval, and production stages.

## Kubernetes run

Build and publish `medicure:local` to the target cluster registry, then apply:

```bash
kubectl apply -f kubernetes/namespace.yaml
kubectl apply -f kubernetes/deployment.yaml
kubectl apply -f kubernetes/service.yaml
kubectl -n medicure rollout status deployment/medicure
kubectl -n medicure get pods,service,hpa
```

The deployment is configured for two pods. A real two-node cluster and registry are infrastructure prerequisites and are not created by local manifest validation.
