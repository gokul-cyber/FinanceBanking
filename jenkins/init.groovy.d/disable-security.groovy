import jenkins.model.Jenkins
import jenkins.install.InstallState
import hudson.security.AuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm

Jenkins jenkins = Jenkins.get()
jenkins.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))
jenkins.setAuthorizationStrategy(new AuthorizationStrategy.Unsecured())
jenkins.save()

import jenkins.model.Jenkins
def j = Jenkins.get()
j.setCrumbIssuer(null)
def loc = jenkins.model.JenkinsLocationConfiguration.get()
loc.setUrl("http://localhost:8080/")
loc.save()
j.save()