import jenkins.model.Jenkins
import jenkins.install.InstallState
import hudson.security.AuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm

Jenkins jenkins = Jenkins.get()
jenkins.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))
jenkins.setAuthorizationStrategy(new AuthorizationStrategy.Unsecured())
jenkins.save()
