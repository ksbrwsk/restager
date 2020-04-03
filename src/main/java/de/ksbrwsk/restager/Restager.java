package de.ksbrwsk.restager;

import lombok.extern.log4j.Log4j2;
import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.applications.SummaryApplicationRequest;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.applications.RestageApplicationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Log4j2
class Restager {

    private final String buildpack = "java";

    private boolean isValidBuildpack(String buildpack, String detectedBuildpack) {
        return (StringUtils.hasText(buildpack) ? "buildpack" : StringUtils.hasText(detectedBuildpack) ? detectedBuildpack : "").contains(this.buildpack);
    }

    Restager(CloudFoundryOperations ops, CloudFoundryClient client) {
        ops
                .applications()
                .list()
                .filter(as -> as.getRunningInstances() > 0)
                .flatMap(as -> client.applicationsV2()
                        .summary(SummaryApplicationRequest
                                .builder()
                                .applicationId(as.getId())
                                .build()))
                .filter(as -> isValidBuildpack(as.getBuildpack(), as.getDetectedBuildpack()))
                .doOnNext(as -> log.info("restaging app " + as.getName() + '.'))
                .flatMap(as -> ops.applications()
                        .restage(RestageApplicationRequest
                                .builder()
                                .name(as.getName())
                                .build()))
                .doOnComplete(() -> System.exit(0))
                .subscribe();
    }
}