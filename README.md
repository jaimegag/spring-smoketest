# Spring Smoketest
This is a  simple Spring Boot Application designed and pre-built to be used for smoke testing purposes on the Tanzu Platform, to validate that an environment/space is ready to be used for Application Developers
- It prints information about the request made to it.
- It prints information about the Outbound IP of the environment where the application runs. This needs egress connectivity to api.ipify.org
- It prints the name of any Service bound to the applicaiton using Cloud Foundry or Kubernetes Service Bindings
- It can also the metadata server endpoint in Public Cloud environments (currently disabled by default)

## Deploy on Tanzu Platform for K8s
Select the project & space you want to target, and run this command from the root folder of this repository:
```bash
tanzu deploy --from-build ./pre-built
```
This will leverage the `tanzu.yaml` manifest and `pre-built` configuration in this repository and a container image already built and stored in GHCR.
> Note: You can also `tanzu build` this application and provide your own build and registry configuration with `tanzu build config`. It should just work.

## Deploy on Tanzu Platform for Cloud Foundry
Target the org & space you need to use, and run these commands from the root folder of this repository:
```bash
./gradlew clean assemble
cf push
```
This will leverage the `manifest.yaml` in this repository.
