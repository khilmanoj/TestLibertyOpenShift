FROM websphere-liberty:19.0.0.9-kernel-java11

COPY --chown=1001:0  db2jcc4.jar /config/
COPY --chown=1001:0  db2jcc_license_cu.jar /config/

COPY --chown=1001:0  TestServletWebProject.war /config/apps/TestServletWebProject.war
COPY --chown=1001:0  server.env /config/
COPY --chown=1001:0  myConfigDir/myds.xml /config/myConfigDir/
COPY --chown=1001:0  properties/testconfig.properties /config/properties/
COPY --chown=1001:0  jvm.options /config/
COPY --chown=1001:0  server.xml /config/


ARG MP_MONITORING=true

RUN configure.sh