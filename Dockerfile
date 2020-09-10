FROM websphere-liberty:19.0.0.9-kernel-java11

COPY --chown=1001:0  TestLibertyApp/db2jcc4.jar /config/
COPY --chown=1001:0  TestLibertyApp/db2jcc_license_cu.jar /config/

COPY --chown=1001:0  TestLibertyApp/TestServletWebProject.war /config/apps/TestServletWebProject.war
COPY --chown=1001:0  TestLibertyApp/server.env /config/
COPY --chown=1001:0  TestLibertyApp/myConfigDir/myds.xml /config/myConfigDir/
COPY --chown=1001:0  TestLibertyApp/properties/testconfig.properties /config/properties/
COPY --chown=1001:0  TestLibertyApp/jvm.options /config/
COPY --chown=1001:0  TestLibertyApp/server.xml /config/


ARG MP_MONITORING=true

RUN configure.sh